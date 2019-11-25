// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package com.soen490.cms.Services;

import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
@Log4j2
public class PdfService {

    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ImpactAssessmentService impactAssessmentService;

    // preface fonts
    private static Font times_10 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
    private static Font times_10_bold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
    // table fonts
    private static Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    private static Font arial_10_bold = FontFactory.getFont("Arial", 10, Font.BOLD);
    private static Font arial_10_italic = FontFactory.getFont("Arial", 10, Font.ITALIC);
    private static Font arial_10_bold_italic = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);
    // table column names share same font
    private static Font column_font = arial_10;
    // diffs fonts
    // for credits & description removals
    private static Font arial_10_red = FontFactory.getFont
            ("Arial", 10, BaseColor.RED);

    // for credits & description add-ons
    private static Font arial_10_blue = FontFactory.getFont
            ("Arial", 10, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for name & number removal (strikethrough)
    private static Font arial_10_red_bold = FontFactory.getFont
            ("Arial", 10, Font.BOLD, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for name & number add-ons (underline)
    private static Font arial_10_blue_bold = FontFactory.getFont
            ("Arial", 10, Font.BOLD, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for title removal (strikethrough)
    private static Font arial_10_red_bold_italic = FontFactory.getFont
            ("Arial", 10, Font.BOLDITALIC, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for title add-ons (underline)
    private static Font arial_10_blue_bold_italic = FontFactory.getFont
            ("Arial", 10, Font.BOLDITALIC, BaseColor.BLUE);

    // this + chunk.setUnderline(0.1f, 3f) for note removal (strikethrough)
    private static Font arial_10_red_italic = FontFactory.getFont
            ("Arial", 10, Font.ITALIC, BaseColor.RED);

    // this + chunk.setUnderline(0.1f, -1f) for note add-ons (underline)
    private static Font arial_10_blue_italic = FontFactory.getFont
            ("Arial", 10, Font.ITALIC, BaseColor.BLUE);


    public byte[] getPDF(int package_id) { return requestPackageRepository.findPdfById(package_id); }


    /**
     * Generate the pdf file for a given package and saves it to the package database table.
     * @param package_id Used to retrieve request_package object.
     * @return true if a pdf has been generated and saved as pdf_file inside request_package.
     */
    public boolean generatePDF(int package_id) throws IOException, DocumentException {

        ByteArrayOutputStream course_outline_stream;
        ByteArrayOutputStream support_stream = null;
        ArrayList<ByteArrayOutputStream> request_streams = new ArrayList<>();
        ByteArrayOutputStream final_stream = new ByteArrayOutputStream();

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        if(requestPackage == null){ return false;}


        if(!requestPackage.getSupportingDocuments().isEmpty()) {

            try {
                log.info("Supporting docs found. Merging them together...");
                support_stream = mergeSupportingDocs(requestPackage);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // for each page
            for(Request request : requestPackage.getRequests()){

                log.info("Generating pdf page for " + request.getTitle());

                Document doc = new Document();
                // course page specifications
                doc.setPageSize(PageSize.A4.rotate());
                doc.setMargins(25, 25, 10, 25);

                ByteArrayOutputStream request_stream = new ByteArrayOutputStream();

                PdfWriter.getInstance(doc, request_stream);

                doc.open();


                if(request.getTargetType() == 2) {

                    // course requests
                    Course original_course = courseRepository.findById(request.getOriginalId());
                    Course changed_course = courseRepository.findById(request.getTargetId());

                    addCoursePreface(doc, request, original_course, changed_course);

                    // append course outline
                    if(changed_course != null && changed_course.getOutline() != null){

                        log.info("Appending course outline for " + changed_course.getName() + changed_course.getNumber());

                        course_outline_stream = mergeOutline(changed_course);

                        doc.close();

                        request_stream = mergeDocs(request_stream, course_outline_stream);

                        request_streams.add(request_stream);

                        continue;

                    }
                }
                else if(request.getTargetType() == 1){

                    // program requests
                }

                doc.close();

                request_streams.add(request_stream);

            }

        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }


        final_stream = mergeRequestDocs(request_streams);


        if(support_stream != null)
            final_stream = mergeDocs(support_stream, final_stream);

        final_stream = stampPageXofY(final_stream);

        requestPackage.setPdfFile(final_stream.toByteArray());

        // this might not be required in active transaction
        requestPackageRepository.save(requestPackage);

        return true;
    }


    /**
     * Numbers the pages for the document.
     * @param final_stream The final document without page numbering.
     * @return The final document stream with numbered pages.
     * @throws IOException
     * @throws DocumentException
     */
    private ByteArrayOutputStream stampPageXofY(ByteArrayOutputStream final_stream) throws IOException, DocumentException {

        log.info("Numbering final document pages...");

        PdfReader reader = new PdfReader(final_stream.toByteArray());

        int n = reader.getNumberOfPages();

        PdfStamper stamper = new PdfStamper(reader, final_stream);

        PdfContentByte pagecontent;

        for (int i = 0; i < n; ) {

            pagecontent = stamper.getOverContent(++i);

            if(reader.getPageRotation(i) == 0)
                ColumnText.showTextAligned(
                        pagecontent, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n), arial_10),
                        reader.getPageSize(i).getRight() - 7, reader.getPageSize(i).getBottom() + 8, 0);
            else
                ColumnText.showTextAligned(
                        pagecontent, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n), arial_10),
                        reader.getPageSize(i).getRight() + 241, reader.getPageSize(i).getBottom() + 8, 0);
        }
        stamper.close();
        reader.close();

        return final_stream;
    }


    /**
     * Combines the request package's supporting documents into one pdf stream.
     * @param requestPackage The package for which pdf generation has been invoked.
     * @return The aggregated stream of the combined supporting documents.
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeSupportingDocs(RequestPackage requestPackage) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        // append supporting docs
        PdfCopy copy = new PdfCopy(doc, byte_stream);
        copy.setMergeFields();

        doc.open();

        for(SupportingDocument supportingDocument : requestPackage.getSupportingDocuments()){

            copy.addDocument(new PdfReader(supportingDocument.getDocument()));

        }

        doc.close();

        return byte_stream;
    }


    /**
     * Aggregate all request docs into one pdf file.
     * @param request_docs List of all request documents generated.
     * @return The combined pdf file of all requests.
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeRequestDocs(ArrayList<ByteArrayOutputStream> request_docs) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        // append supporting docs
        PdfCopy copy = new PdfCopy(doc, byte_stream);
        copy.setMergeFields();

        doc.open();

        for(ByteArrayOutputStream request_doc : request_docs){

            copy.addDocument(new PdfReader(request_doc.toByteArray()));

        }

        doc.close();

        return byte_stream;
    }


    /**
     * Combines a request document with the course outline
     * @param course Course that contains a proposed outline
     * @return A combined document of the request followed by its proposed outline
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeOutline(Course course) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        // append supporting docs
        PdfCopy copy = new PdfCopy(doc, byte_stream);
        copy.setMergeFields();

        doc.open();

        copy.addDocument(new PdfReader(course.getOutline()));

        doc.close();

        return byte_stream;
    }


    /**
     * This function merges the supporting documents with the generated course pages into a single pdf stream
     * @param support_stream The merged supporting documents pdf file in stream format.
     * @param course_stream The generated course pdf file in stream format.
     * @return The merged stream
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeDocs(ByteArrayOutputStream support_stream, ByteArrayOutputStream course_stream) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream final_stream = new ByteArrayOutputStream();

        PdfCopy copy = new PdfCopy(doc, final_stream);

        doc.open();

        copy.addDocument(new PdfReader(support_stream.toByteArray()));
        copy.addDocument(new PdfReader(course_stream.toByteArray()));

        doc.close();

        return final_stream;
    }


    /**
     * Adds the course metadata to the document page.
     * @param doc The document used to write the course preface.
     * @param request The request object containing the course metadata.
     * @param o The original course information.
     * @param c The changed course information.
     */
    private void addCoursePreface(Document doc, Request request, Course o, Course c){

        // preface paragraph
        Paragraph preface1 = new Paragraph();

        Phrase page_header = new Phrase();
        page_header.add(new Chunk("PACKAGE_" + request.getRequestPackage().getId() + ": " +
                "REQUEST_" + request.getId(), times_10));

        preface1.add(page_header);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        // request type and course name_number
        String type;
        int request_type = request.getRequestType();
        // if this is a course creation, original course should have fields set to null to avoid exceptions
        if(request_type == 1) {
            type = "NEW COURSE";
            o = new Course();
            o.setName("");
            o.setNumber(0);
            o.setCredits(0);
            o.setDescription("");
            o.setLevel(0);
            o.setTitle("");
            o.setNote("");
        }
        else if(request_type == 2) {
            type = "COURSE CHANGE";
        }
        // if this is a course deletion, changed course should have fields set to null to avoid exceptions
        else if(request_type == 3) {
            type = "COURSE DELETION";
            c = new Course();
            c.setName("");
            c.setNumber(0);
            c.setCredits(0);
            c.setDescription("");
            c.setLevel(0);
            c.setTitle("");
            c.setNote("");
        }
        else
            return;

        Phrase request_type_phrase = new Phrase();
        request_type_phrase.add(new Chunk(type + ": ", times_10_bold));

        if(request_type == 3)
            request_type_phrase.add(new Chunk(o.getName() + " " + o.getNumber(), times_10));
        else
            request_type_phrase.add(new Chunk(c.getName() + " " + c.getNumber(), times_10));

        preface1.add(request_type_phrase);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        // request academic level
        Phrase proposed = new Phrase();
        proposed.add(new Chunk("Proposed: ", times_10_bold));

        if(request_type == 3) {

            if (o.getLevel() == 1)
                proposed.add(new Chunk("Undergraduate Curriculum Changes", times_10));
            else if (o.getLevel() > 1)
                proposed.add(new Chunk("Graduate Curriculum Changes", times_10));
            else if ((o.getLevel() == 1 && c.getLevel() > 1) || (c.getLevel() > 1 && o.getLevel() == 1))
                proposed.add(new Chunk("Undergraduate And Graduate Curriculum Changes", times_10));
        }
        else{

            if (c.getLevel() == 1)
                proposed.add(new Chunk("Undergraduate Curriculum Changes", times_10));
            else if (c.getLevel() > 1 && o.getLevel() > 1)
                proposed.add(new Chunk("Graduate Curriculum Changes", times_10));
            else if ((c.getLevel() == 1 && o.getLevel() > 1) || (c.getLevel() > 1 && o.getLevel() == 1))
                proposed.add(new Chunk("Undergraduate And Graduate Curriculum Changes", times_10));
        }

        preface1.add(proposed);
        preface1.add(new Chunk(Chunk.NEWLINE));

        // academic year phrase - align right requires a new paragraph
        Paragraph preface2 = new Paragraph();

        Phrase years = new Phrase();
        years.add(new Chunk("Calendar for Academic Year: ", times_10_bold));
        years.add(new Chunk("2020/2021\n", times_10));
        years.add(new Chunk("Implementation Month/Year: ", times_10_bold));
        years.add(new Chunk("May 2020", times_10));

        preface2.add(years);
        preface2.setAlignment(Element.ALIGN_RIGHT);

        // last preface segment aligned left
        Paragraph preface3 = new Paragraph();
        preface3.setTabSettings(new TabSettings(200f));

        // faculty phrase
        Phrase faculty = new Phrase();
        faculty.add(new Chunk("Faculty/School:", times_10_bold));
        faculty.add(Chunk.TABBING);

        if(request_type == 3)
            faculty.add(new Chunk(o.getProgram().getDepartment().getFaculty().getName(), times_10));
        else
            faculty.add(new Chunk(c.getProgram().getDepartment().getFaculty().getName(), times_10));

        preface3.add(faculty);
        preface3.add(Chunk.NEWLINE);

        // department phrase
        Phrase department = new Phrase();
        department.add(new Chunk("Department:", times_10_bold));
        department.add(Chunk.TABBING);

        if(request_type == 3)
            department.add(new Chunk(o.getProgram().getDepartment().getName(), times_10));
        else
            department.add(new Chunk(c.getProgram().getDepartment().getName(), times_10));

        preface3.add(department);
        preface3.add(Chunk.NEWLINE);

        // program phrase
        Phrase program = new Phrase();
        program.add(new Chunk("Program:", times_10_bold));
        program.add(Chunk.TABBING);

        if(request_type == 3)
            program.add(new Chunk(o.getProgram().getName(), times_10));
        else
            program.add(new Chunk(c.getProgram().getName(), times_10));

        preface3.add(program);
        preface3.add(Chunk.NEWLINE);

        // degree phrase
        Phrase degree = new Phrase();
        degree.add(new Chunk("Degree:", times_10_bold));
        degree.add(Chunk.TABBING);

        int ctr = 0;

        if(request_type != 3) {

            int size = c.getDegreeRequirements().size();

            for (DegreeRequirement degreeRequirement : c.getDegreeRequirements()) {

                if(size == 1 && !degreeRequirement.getCore().equals(""))
                    degree.add(new Chunk(degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));

                else if(size == 1 && degreeRequirement.getCore().equals(""))
                    degree.add(new Chunk(degreeRequirement.getDegree().getName(), times_10));

                else if(size > 1 && !degreeRequirement.getCore().equals(""))
                    degree.add(new Chunk("- " + degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));
                else
                    degree.add(new Chunk("- " + degreeRequirement.getDegree().getName(), times_10));

                if (ctr != size - 1) {
                    degree.add(Chunk.NEWLINE);
                    degree.add(Chunk.TABBING);
                }

                ctr++;
            }

        }
        else{

            int size = o.getDegreeRequirements().size();

            for (DegreeRequirement degreeRequirement : o.getDegreeRequirements()) {

                if(size == 1)
                    degree.add(new Chunk(degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));
                else
                    degree.add(new Chunk("- " + degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));

                if (ctr != size - 1) {
                    degree.add(Chunk.NEWLINE);
                    degree.add(Chunk.TABBING);
                }

                ctr++;
            }

        }

        preface3.add(degree);
        preface3.add(Chunk.NEWLINE);

        // calendar phrase
        Phrase calendar = new Phrase();
        if(c.getLevel() == 1)
            calendar.add(new Chunk("Undergraduate Calendar Section:", times_10_bold));
        else
            calendar.add(new Chunk("Graduate Page Number:", times_10_bold));

        // Todo: calendar needs a better design
        calendar.add(Chunk.TABBING);

        if(request_type == 3)
            calendar.add(new Chunk("§" + o.getProgram().getDepartment().getCalendar().getSectionId(), times_10));
        else
            calendar.add(new Chunk("§" + c.getProgram().getDepartment().getCalendar().getSectionId(), times_10));

        preface3.add(calendar);
        preface3.add(Chunk.NEWLINE);
        preface3.add(Chunk.NEWLINE);

        try{
            // add preface paragraph to document
            doc.add(preface1);
            doc.add(preface2);
            doc.add(preface3);

        }catch(DocumentException e){
            e.printStackTrace();
        }

        try {
            addCourseDiffTable(doc, request, o, c);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

    }

    private String o_anti_note = "";
    private String c_anti_note = "";

    /**
     * Adds the course table where the differences between the original and changed course are highlighted.
     * @param doc The document used to write the course preface.
     * @param request The request object containing the course metadata.
     * @param o The original course information.
     * @param c The changed course information.
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void addCourseDiffTable(Document doc, Request request, Course o, Course c) throws FileNotFoundException, DocumentException {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        float CELL_PADDING = 5f;
        int size = 0;
        String remainder;

        String o_name = o.getName() + " " + o.getNumber();
        String o_title = " " + o.getTitle();
        String o_credits = " (" + o.getCredits() + " credits)";
        String c_name = c.getName() + " " + c.getNumber();
        String c_title = " " + c.getTitle();
        String c_credits = " (" + c.getCredits() + " credits)";

        String o_body = stringifyRequisites(o.getRequisites(), true) + o.getDescription();
        String c_body = stringifyRequisites(c.getRequisites(), false) + c.getDescription();

        String o_note = o.getNote();
        String c_note = c.getNote();

        if(!o_anti_note.equals("")){

            if(!o_note.equals(""))
                o_note = o_note.substring(6);

            o_note = o_anti_note + o_note;
        }
        if(!c_anti_note.equals("")){

            if(!c_note.equals(""))
                c_note = c_note.substring(6);

            c_note = c_anti_note + c_note;
        }

        String rationale = request.getRationale();
        String resource_implications = request.getResourceImplications();

        // static headers
        table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold))).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold))).setPadding(CELL_PADDING);
        table.completeRow();

        // course paragraph
        Paragraph original_paragraph = new Paragraph();
        Paragraph changed_paragraph = new Paragraph();

        // course phrases
        // name and number
        Phrase original_name_phrase = new Phrase();
        Phrase changed_name_phrase = new Phrase();

        // title
        Phrase original_title_phrase = new Phrase();
        Phrase changed_title_phrase = new Phrase();

        // credits
        Phrase original_credits_phrase = new Phrase();
        Phrase changed_credits_phrase = new Phrase();

        // body = chunks of requisites & descriptions
        Phrase original_body_phrase = new Phrase();
        Phrase changed_body_phrase = new Phrase();

        // notes
        Phrase original_note_phrase = new Phrase();
        Phrase changed_note_phrase = new Phrase();

        if(request.getRequestType() == 1){

            changed_name_phrase.add(new Chunk(c_name, arial_10_bold));
            changed_title_phrase.add(new Chunk(c_title, arial_10_bold_italic));
            changed_credits_phrase.add(new Chunk(c_credits, arial_10));
            changed_body_phrase.add(new Chunk(c_body, arial_10));
            changed_note_phrase.add(new Chunk(c_note, arial_10_italic));

        }
        else if(request.getRequestType() == 2) {

            processDifferences(original_name_phrase, changed_name_phrase, o_name, c_name, 1);
            processDifferences(original_title_phrase, changed_title_phrase, o_title, c_title, 2);
            processDifferences(original_credits_phrase, changed_credits_phrase, o_credits, c_credits, 3);
            processDifferences(original_body_phrase, changed_body_phrase, o_body, c_body, 4);
            processDifferences(original_note_phrase, changed_note_phrase, o_note, c_note, 5);

        }
        else if(request.getRequestType() == 3){

            original_name_phrase.add(new Chunk(o_name, arial_10_bold));
            original_title_phrase.add(new Chunk(o_title, arial_10_bold_italic));
            original_credits_phrase.add(new Chunk(o_credits, arial_10));
            original_body_phrase.add(new Chunk(o_body, arial_10));
            original_note_phrase.add(new Chunk(o_note, arial_10_italic));

        }
        // ...

        // add all course phrases to paragraph
        original_paragraph.add(original_name_phrase);
        original_paragraph.add(original_title_phrase);
        original_paragraph.add(original_credits_phrase);
        original_paragraph.add(Chunk.NEWLINE);
        original_paragraph.add(original_body_phrase);
        original_paragraph.add(Chunk.NEWLINE);
        original_paragraph.add(original_note_phrase);

        changed_paragraph.add(changed_name_phrase);
        changed_paragraph.add(changed_title_phrase);
        changed_paragraph.add(changed_credits_phrase);
        changed_paragraph.add(Chunk.NEWLINE);
        changed_paragraph.add(changed_body_phrase);
        changed_paragraph.add(Chunk.NEWLINE);
        changed_paragraph.add(changed_note_phrase);

        // once all course details are done
        table.addCell(new PdfPCell(original_paragraph)).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(changed_paragraph)).setPadding(CELL_PADDING);
        table.completeRow();

        // add rationale cell which spans 2 columns
        Phrase rationale_phrase = new Phrase();

        rationale_phrase.add(new Chunk("Rationale:", column_font).setUnderline(0.1f, -1f));
        rationale_phrase.add(Chunk.NEWLINE);
        rationale_phrase.add(Chunk.NEWLINE);

        if(!rationale.equals(""))
            rationale_phrase.add(new Chunk(rationale, arial_10));
        else
            rationale_phrase.add(new Chunk("None.", arial_10));

        PdfPCell rationale_cell = new PdfPCell(rationale_phrase);
        rationale_cell.setColspan(2);
        table.addCell(rationale_cell).setPadding(CELL_PADDING);
        table.completeRow();

        // add resource implications cell which spans 2 columns
        Phrase resource_phrase = new Phrase();

        resource_phrase.add(new Chunk("Resource Implications:", column_font).setUnderline(0.1f, -1f));
        resource_phrase.add(Chunk.NEWLINE);
        resource_phrase.add(Chunk.NEWLINE);
        
        if(!resource_implications.equals(""))
            resource_phrase.add(new Chunk(resource_implications, arial_10));
        else
            resource_phrase.add(new Chunk("None.", arial_10));

        PdfPCell resource_cell = new PdfPCell(resource_phrase);
        resource_cell.setColspan(2);
        table.addCell(resource_cell).setPadding(CELL_PADDING);
        table.completeRow();

        // add impact report cell
        table.addCell(addCourseImpactReport(request)).setPadding(CELL_PADDING);

        try {
            doc.add(table);
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }


    /**
     * Creates a table cell with the impact statement report from the given request.
     * @param request The request object used to generate impact statements.
     * @return the last course diff table cell with the impact statements report.
     */
    private PdfPCell addCourseImpactReport(Request request) {

        Map<String, Object> impact_report = impactAssessmentService.getAssessment(request.getId());

        Iterator it = impact_report.entrySet().iterator();

        boolean isNone = true;

        Paragraph report_paragraph = new Paragraph();
        report_paragraph.setTabSettings(new TabSettings(25f));
        report_paragraph.add(new Chunk("Impact Statements Report:", column_font).setUnderline(0.1f, -1f));
        report_paragraph.add(Chunk.NEWLINE);

        String key;

        while(it.hasNext()){

            Map.Entry pair = (Map.Entry) it.next();

            key = pair.getKey().toString();

            if(key.equals("DegreeCourseRequiredImpact")) {

                Map<String, Object> original_removed_added_updated = (Map<String, Object>) pair.getValue();

                ArrayList<Map<String, Object>> original = (ArrayList) original_removed_added_updated.get("original");
                ArrayList<Map<String, Object>> removed = (ArrayList) original_removed_added_updated.get("removed");
                ArrayList<Map<String, Object>> added = (ArrayList) original_removed_added_updated.get("added");
                ArrayList<Map<String, Object>> updated = (ArrayList) original_removed_added_updated.get("updated");

                if(original != null) {

                    if (0 < original.size()) {

                        report_paragraph.add(Chunk.NEWLINE);

                        for (int i = 0; i < original.size(); i++) {

                            Iterator it_u = original.get(i).entrySet().iterator();

                            while (it_u.hasNext()) {

                                Map.Entry updated_pair = (Map.Entry) it_u.next();

                                String impact_line = ("Original degree to be modified: " +
                                        updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");


                                report_paragraph.add(new Chunk(impact_line, arial_10));
                                report_paragraph.add(Chunk.NEWLINE);
                                isNone = false;
                            }
                        }
                        if (updated != null) {
                            if (0 < updated.size()) {

                                for (int i = 0; i < updated.size(); i++) {

                                    Iterator it_u = updated.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be updated to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if (removed != null) {
                            if (0 < removed.size()) {

                                for (int i = 0; i < removed.size(); i++) {

                                    Iterator it_u = removed.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be removed to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if (added != null) {
                            if (0 < added.size()) {

                                for (int i = 0; i < added.size(); i++) {

                                    Iterator it_u = added.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        String impact_line = ("- Degree credits will be added to become: " +
                                                updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(key.equals("ProgramImpact")) {

                Map<String, Object> original_removed_added_updated = (Map<String, Object>) pair.getValue();

                ArrayList<Map<String, Object>> original = (ArrayList) original_removed_added_updated.get("original");
                ArrayList<Map<String, Object>> removed = (ArrayList) original_removed_added_updated.get("removed");
                ArrayList<Map<String, Object>> added = (ArrayList) original_removed_added_updated.get("added");
                ArrayList<Map<String, Object>> updated = (ArrayList) original_removed_added_updated.get("updated");

                if (original != null) {

                    double temp_credits = 0;

                    if (0 < original.size()) {

                        for (int i = 0; i < original.size(); i++) {

                            Iterator it_u = original.get(i).entrySet().iterator();

                            while (it_u.hasNext()) {

                                Map.Entry updated_pair = (Map.Entry) it_u.next();

                                if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                    continue;
                                }

                                String impact_line = ("Core section to be modified: " +
                                        updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");

                                temp_credits = (double) updated_pair.getValue();

                                report_paragraph.add(Chunk.NEWLINE);
                                report_paragraph.add(new Chunk(impact_line, arial_10));
                                report_paragraph.add(Chunk.NEWLINE);
                                isNone = false;
                            }
                        }
                        if(updated != null) {
                            if (0 < updated.size()) {

                                for (int i = 0; i < updated.size(); i++) {

                                    Iterator it_u = updated.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be migrated to: " + updated_pair.getKey() + ".");
                                        }
                                        else if(Double.valueOf(updated_pair.getValue().toString()) > temp_credits){
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }
                                        else{
                                            impact_line = ("- Core credits will be removed to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if(removed != null) {
                            if (0 < removed.size()) {

                                for (int i = 0; i < removed.size(); i++) {

                                    Iterator it_u = removed.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be removed from: " + updated_pair.getKey() + ".");
                                        }
                                        else {
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                        if(added != null) {
                            if (0 < added.size()) {

                                for (int i = 0; i < added.size(); i++) {

                                    Iterator it_u = added.get(i).entrySet().iterator();

                                    while (it_u.hasNext()) {

                                        Map.Entry updated_pair = (Map.Entry) it_u.next();

                                        if(updated_pair.getKey().equals("") && Double.valueOf(updated_pair.getValue().toString()) == 0){
                                            continue;
                                        }

                                        String impact_line;

                                        if(Double.valueOf(updated_pair.getValue().toString()) == 0) {
                                            impact_line = ("- Course will be migrated to: " + updated_pair.getKey() + ".");
                                        }
                                        else {
                                            impact_line = ("- Core credits will be added to become: " +
                                                    updated_pair.getKey() + " (" + updated_pair.getValue() + " credits).");
                                        }

                                        report_paragraph.add(Chunk.TABBING);
                                        report_paragraph.add(new Chunk(impact_line, arial_10));
                                        report_paragraph.add(Chunk.NEWLINE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if(isNone)
            report_paragraph.add(new Chunk("None.", arial_10));

        PdfPCell report = new PdfPCell(report_paragraph);
        report.setColspan(2);

        return report;
    }


    /**
     * Aggregates a collection of requisites into a string identical to those displayed on the academic calendar.
     * @param requisites The requisites of a given course.
     * @return A formatted string of requisites.
     */
    private String stringifyRequisites(Collection<Requisite> requisites, boolean original) {

        StringBuilder r;
        int ctr = 0;
        int ctr_anti = 0;
        int ctr2_anti = 0;
        int ctr_co = 0;
        int ctr2_co = 0;
        int ctr_pre = 0;
        String anti_store = "";
        StringBuilder co_store = new StringBuilder();

        if(!requisites.isEmpty())
            r = new StringBuilder("Prerequisite: ");
        else
            return "";

        boolean equivalent_next = true;

        for(Requisite requisite : requisites){

            if(requisite.getType().equals("antirequisite"))
                ctr_anti++;
            if(requisite.getType().equals("corequisite"))
                ctr_co++;
            if(requisite.getType().equals("prerequisite"))
                ctr_pre++;
        }

        for(Requisite requisite : requisites){

            String type = requisite.getType();
            String name_number;

            if(requisite.getNumber() == 0)
                name_number= requisite.getName() + " ";
            else
                name_number= requisite.getName() + " " + requisite.getNumber();


            if(type.equals("prerequisite")) {
                if(ctr == 0) {
                    r.append(name_number);
                    ctr++;
                }
                else
                    r.append("; ").append(name_number);
            }
            else if(type.equals("equivalent") && equivalent_next) {
                r.append("; ").append(name_number).append(" or ");
                equivalent_next = false;
            }
            else if(type.equals("equivalent") && !equivalent_next){
                r.append(name_number);
                equivalent_next = true;
            }
            else if(type.equals("corequisite")) {

                if(ctr_co == 1)
                    co_store = new StringBuilder(name_number + " previously or concurrently");

                else if(ctr_co == 2 && ctr2_co == 0)
                    co_store = new StringBuilder(name_number);

                else if(ctr_co == 2 && ctr2_co == 1)
                    co_store.append(" and ").append(name_number).append(" previously or concurrently");

                else if(ctr_co > 2 && ctr2_co == 0)
                    co_store = new StringBuilder(name_number);

                else if(ctr_co > 2 && ctr_co != ctr2_co + 1)
                    co_store.append(", ").append(name_number);

                else co_store.append(" and ").append(name_number).append("  previously or concurrently");

                ctr2_co++;

            }
            else if(type.equals("antirequisite")){

                if(ctr_anti == 1)
                    anti_store = name_number;

                else if(ctr_anti == 2 && ctr2_anti == 0)
                    anti_store = name_number;

                else if(ctr_anti == 2 && ctr2_anti == 1)
                    anti_store = anti_store + " or " + name_number;

                else if(ctr_anti > 2 && ctr2_anti == 0)
                    anti_store = name_number;

                else if(ctr_anti != ctr2_anti + 1)
                    anti_store = anti_store + ", " + name_number;

                else anti_store = anti_store + " or " + name_number;

                ctr2_anti++;
            }
        }

        if (original && ctr_anti > 0)
            o_anti_note = "NOTE: Students who have received credit for " + anti_store +
                    " may not take this course for credit.";
        else if (!original && ctr_anti > 0)
            c_anti_note = "NOTE: Students who have received credit for " + anti_store +
                    " may not take this course for credit.";

        if(ctr_co > 0 && ctr_pre > 0)
            r.append("; ").append(co_store);

        if(ctr_co > 0 && ctr_pre == 0)
            r.append(co_store);

        r.append(". ");

        return r.toString();
    }


    /**
     * Surrounds substrings with "~" to differentiate the original string from the changed string.
     * @param original The string of the original text.
     * @param changed The string of the changed text.
     * @return An array of strings with the appended "~".
     */
    public String[] generateDiffs(String original, String changed){

        String[] processed_strings = new String[2];

        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "~")
                .build();

        try {
            List<DiffRow> rows = generator.generateDiffRows(
                    Collections.singletonList(original),
                    Collections.singletonList(changed));

            processed_strings[0] = rows.get(0).getOldLine();
            processed_strings[1] = rows.get(0).getNewLine();

        } catch (DiffException e) {
            e.printStackTrace();
        }

        return processed_strings;
    }


    /**
     * Uses the strings from generateDiffs to highlight substrings with differentiating fonts and colors. This is
     * then written to the document course table.
     * @param original_phrase The original section to be written to the document.
     * @param changed_phrase The changed section to be written to the document.
     * @param o The original string to be differentiated, highlighted and added to the original phrase.
     * @param c The changed string to be differentiated, highlighted and added to the changed phrase.
     * @param type The section in question - 1: name&number, 2: title, 3: credits, 4: description and 5: note
     */
    private void processDifferences
            (Phrase original_phrase, Phrase changed_phrase, String o, String c, int type) {

        int ctr = 0;
        String[] processed = generateDiffs(o, c);
        String[] o_partitions = processed[0].split("~");
        String[] c_partitions = processed[1].split("~");

        // compose original phrase
        for(String partition : o_partitions){

            partition = partition.trim();

            if(ctr % 2 != 0 && !partition.equals("")){

                if(type == 1)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_bold).setUnderline(0.1f, 3f));

                if(type == 2)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_bold_italic).setUnderline(0.1f, 3f));

                if(type == 3) {
                    original_phrase.add(new Chunk(partition,
                            arial_10_red).setUnderline(0.1f, 3f));
                }

                if(type == 4) {

                    if(StringUtils.isNumeric(partition))
                        original_phrase.add(new Chunk(partition,
                                arial_10_red).setUnderline(0.1f, 3f));
                    else
                        original_phrase.add(new Chunk(partition + " ",
                                arial_10_red).setUnderline(0.1f, 3f));
                }

                if(type == 5)
                    original_phrase.add(new Chunk(partition + " ",
                            arial_10_red_italic).setUnderline(0.1f, 3f));

            }
            else if(!partition.equals("")){

                if(type == 1) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_bold));
                }

                if(type == 2) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_bold_italic));
                }

                if(type == 3) {

                    if(partition.equals("credits)"))
                        original_phrase.add(new Chunk(" " + partition, arial_10));

                    else
                        original_phrase.add(new Chunk(partition, arial_10));
                }

                if(type == 4) {
                    original_phrase.add(new Chunk(partition + " ", arial_10));
                }

                if(type == 5) {
                    original_phrase.add(new Chunk(partition + " ", arial_10_italic));
                }

            }

            ctr++;
        }

        // reset counter
        ctr = 0;

        // compose changed phrase
        for(String partition : c_partitions){

            partition = partition.trim();

            if(ctr % 2 != 0 && !partition.equals("")){

                if(type == 1)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_bold).setUnderline(0.1f, -1f));

                if(type == 2)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_bold_italic).setUnderline(0.1f, -1f));

                if(type == 3) {
                    changed_phrase.add(new Chunk(partition,
                            arial_10_blue).setUnderline(0.1f, -1f));
                }

                if(type == 4) {

                    if(StringUtils.isNumeric(partition))
                        changed_phrase.add(new Chunk(partition,
                                arial_10_blue).setUnderline(0.1f, -1f));
                    else
                        changed_phrase.add(new Chunk(partition + " ",
                                arial_10_blue).setUnderline(0.1f, -1f));
                }

                if(type == 5)
                    changed_phrase.add(new Chunk(partition + " ",
                            arial_10_blue_italic).setUnderline(0.1f, -1f));

            }
            else if(!partition.equals("")){

                if(type == 1) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_bold));
                }

                if(type == 2) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_bold_italic));
                }

                if(type == 3) {

                    if(partition.equals("credits)"))
                        changed_phrase.add(new Chunk(" " + partition, arial_10));

                    else
                        changed_phrase.add(new Chunk(partition, arial_10));
                }

                if(type == 4) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10));
                }

                if(type == 5) {
                    changed_phrase.add(new Chunk(partition + " ", arial_10_italic));
                }

            }

            ctr++;
        }

    }

}
