package com.soen490.cms.Services;


import com.github.difflib.algorithm.DiffException;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Utils.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PdfService {

    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;

    // preface fonts
    private static Font times_10 = new Font(Font.FontFamily.TIMES_ROMAN, 11);
    private static Font times_10_bold = new Font(Font.FontFamily.TIMES_ROMAN, 11, Font.BOLD);
    // table fonts
    private static Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    private static Font arial_10_bold = FontFactory.getFont("Arial", 10, Font.BOLD);
    private static Font arial_10_italic = FontFactory.getFont("Arial", 10, Font.ITALIC);
    private static Font arial_10_bold_italic = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);

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
     * Generate the pdf file for a given package.
     * @param package_id Used to retrieve request_package object.
     * @return true if a pdf has been generated and saved as pdf_file inside request_package.
     */
    public boolean generatePDF(int package_id){

        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        if(requestPackage == null){ return false;}

        try {

            // package.pdf
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Keeran\\Desktop\\cms\\package_7.pdf"));
            //PdfWriter.getInstance(doc, byte_stream);

            writer.setBoxSize("corner-box", doc.getPageSize());
            writer.setPageEvent(new PageEvent());

            doc.open();

            // for each page
            for(Request request : requestPackage.getRequests()){

                if(request.getTargetType() == 2) {

                    // course requests
                    Course original_course = courseRepository.findById(request.getOriginalId());
                    Course changed_course = courseRepository.findById(request.getTargetId());

                    addCoursePreface(doc, request, original_course, changed_course);
                    addCourseDiffTable(doc, request, original_course, changed_course);
                }
                else if(request.getTargetType() == 1){

                    // program requests
                }

                doc.newPage();
            }

            doc.close();

        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
            return false;
        }

        //requestPackage.setPdfFile(byte_stream.toByteArray());

        // this might not be required in active transaction
        //requestPackageRepository.save(requestPackage);

        return true;
    }


    /**
     * Adds the course metadata to the document page.
     * @param doc The document used to write the course preface.
     * @param request The request object containing the course metadata.
     * @param o The original course information.
     * @param c The changed course information.
     */
    private void addCoursePreface(Document doc, Request request, Course o, Course c){

        try{

            // preface paragraph
            Paragraph preface1 = new Paragraph();

            Phrase page_header = new Phrase();
            page_header.add(new Chunk("PACKAGE_" + request.getRequestPackage().getId() + ": " +
                    "REQUEST_" + request.getId(), times_10));

            preface1.add(page_header);
            preface1.add(new Chunk(Chunk.NEWLINE));
            preface1.add(new Chunk(Chunk.NEWLINE));

            // request type and course name_number
            String type;

            if(request.getRequestType() == 1)
                type = "COURSE CREATION";
            else if(request.getRequestType() == 2)
                type = "COURSE UPDATE";
            else if(request.getRequestType() == 3)
                type = "COURSE REMOVAL";
            else
                return;

            Phrase request_type = new Phrase();
            request_type.add(new Chunk(type + ": ", times_10_bold));
            request_type.add(new Chunk(c.getName() + " " + c.getNumber(), times_10));

            preface1.add(request_type);
            preface1.add(new Chunk(Chunk.NEWLINE));
            preface1.add(new Chunk(Chunk.NEWLINE));

            // request academic level
            Phrase proposed = new Phrase();
            proposed.add(new Chunk("Proposed: ", times_10_bold));

            if(c.getLevel() == 1 && o.getLevel() == 1)
                proposed.add(new Chunk("Undergraduate Curriculum Changes", times_10));
            else if(c.getLevel() > 1 && o.getLevel() > 1)
                proposed.add(new Chunk("Graduate Curriculum Changes", times_10));
            else if((c.getLevel() == 1 && o.getLevel() > 1) || (c.getLevel() > 1 && o.getLevel() == 1))
                proposed.add(new Chunk("Undergraduate And Graduate Curriculum Changes", times_10));

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
            faculty.add(new Chunk(Chunk.TABBING));
            faculty.add(new Chunk(c.getProgram().getDepartment().getFaculty().getName(), times_10));

            preface3.add(faculty);
            preface3.add(new Chunk(Chunk.NEWLINE));

            // department phrase
            Phrase department = new Phrase();
            department.add(new Chunk("Department:", times_10_bold));
            department.add(new Chunk(Chunk.TABBING));
            department.add(new Chunk(c.getProgram().getDepartment().getName(), times_10));

            preface3.add(department);
            preface3.add(new Chunk(Chunk.NEWLINE));

            // program phrase
            Phrase program = new Phrase();
            program.add(new Chunk("Program:", times_10_bold));
            program.add(new Chunk(Chunk.TABBING));
            program.add(new Chunk(c.getProgram().getName(), times_10));

            preface3.add(program);
            preface3.add(new Chunk(Chunk.NEWLINE));

            // degree phrase
            Phrase degree = new Phrase();
            degree.add(new Chunk("Degree:", times_10_bold));
            degree.add(new Chunk(Chunk.TABBING));

            int ctr = 0;

            // @TODO: make sure c also contains degree requirements !
            for(DegreeRequirement degreeRequirement : o.getDegreeRequirements()) {
                if(ctr > 0)
                    degree.add(new Chunk(", " + degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));
                else
                    degree.add(new Chunk(degreeRequirement.getDegree().getName() +
                            " - " + degreeRequirement.getCore(), times_10));
                ctr++;
            }

            preface3.add(degree);
            preface3.add(new Chunk(Chunk.NEWLINE));

            // calendar phrase
            Phrase calendar = new Phrase();
            if(c.getLevel() == 1)
                calendar.add(new Chunk("Undergraduate Calendar Section:", times_10_bold));
            else
                calendar.add(new Chunk("Graduate Page Number:", times_10_bold));

            calendar.add(new Chunk(Chunk.TABBING));
            calendar.add(new Chunk("§" + c.getProgram().getDepartment().getCalendar().getSectionId(), times_10));

            preface3.add(calendar);
            preface3.add(new Chunk(Chunk.NEWLINE));
            preface3.add(new Chunk(Chunk.NEWLINE));
            preface3.add(new Chunk(Chunk.NEWLINE));

            // add preface paragraph to document
            doc.add(preface1);
            doc.add(preface2);
            doc.add(preface3);

        }catch(DocumentException e){
            e.printStackTrace();
        }

    }


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

        try {
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

            String o_body = stringifyRequisites(o.getRequisites()) + o.getDescription();
            String c_body = stringifyRequisites(c.getRequisites()) + c.getDescription();

            String o_note = o.getNote();
            String c_note = c.getNote();

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

            processDifferences(original_name_phrase, changed_name_phrase, o_name, c_name, 1);

            // title
            Phrase original_title_phrase = new Phrase();
            Phrase changed_title_phrase = new Phrase();

            processDifferences(original_title_phrase, changed_title_phrase, o_title, c_title, 2);

            // credits (special case needs to be partitioned manually)
            Phrase original_credits_phrase = new Phrase();
            Phrase changed_credits_phrase = new Phrase();

            processDifferences(original_credits_phrase, changed_credits_phrase, o_credits, c_credits, 3);

            // body = chunks of requisites & descriptions
            Phrase original_body_phrase = new Phrase();
            Phrase changed_body_phrase = new Phrase();

            processDifferences(original_body_phrase, changed_body_phrase, o_body, c_body, 4);

            // notes
            Phrase original_note_phrase = new Phrase();
            Phrase changed_note_phrase = new Phrase();

            processDifferences(original_note_phrase, changed_note_phrase, o_note, c_note, 5);

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

            rationale_phrase.add(new Chunk("Rationale:", arial_10));
            rationale_phrase.add(Chunk.NEWLINE);
            rationale_phrase.add(new Chunk(rationale, arial_10));

            PdfPCell rationale_cell = new PdfPCell(rationale_phrase);
            rationale_cell.setColspan(2);
            table.addCell(rationale_cell).setPadding(CELL_PADDING);
            table.completeRow();

            // add resource implications cell which spans 2 columns
            Phrase resource_phrase = new Phrase();

            resource_phrase.add(new Chunk("Resource Implications:", arial_10));
            resource_phrase.add(Chunk.NEWLINE);
            resource_phrase.add(new Chunk(resource_implications, arial_10));

            PdfPCell resource_cell = new PdfPCell(resource_phrase);
            resource_cell.setColspan(2);
            table.addCell(resource_cell).setPadding(CELL_PADDING);
            table.completeRow();

            doc.add(table);

        }catch(DocumentException e){
            e.printStackTrace();
        }
    }


    /**
     * Aggregates a collection of requisites into a string identical to those displayed on the academic calendar.
     * @param requisites The requisites of a given course.
     * @return A formatted string of requisites.
     */
    private String stringifyRequisites(Collection<Requisite> requisites) {

        StringBuilder r;

        if(!requisites.isEmpty())
            r = new StringBuilder("Prerequisite: ");
        else
            return "";

        boolean equivalent_next = true;

        for(Requisite requisite : requisites){

            String type = requisite.getType();
            String name_number = requisite.getName() + " " + requisite.getNumber();

            if(type.equals("prerequisite"))
                r.append(name_number);

            else if(type.equals("corequisite"))
                r.append("; ").append(name_number).append(" previously or concurrently");

            else if(type.equals("equivalent") && equivalent_next) {
                r.append("; ").append(name_number).append(" or ");
                equivalent_next = false;
            }
            else if(type.equals("equivalent") && !equivalent_next){
                r.append(name_number);
                equivalent_next = true;
            }
        }

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
