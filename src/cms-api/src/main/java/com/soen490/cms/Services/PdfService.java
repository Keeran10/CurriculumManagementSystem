package com.soen490.cms.Services;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;


@Service
public class PdfService {

    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;

    // preface fonts
    private static Font times_10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private static Font times_10_bold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
    // table fonts
    private static Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    private static Font arial_10_bold = FontFactory.getFont("Arial", 10, Font.BOLD);
    private static Font arial_10_italic = FontFactory.getFont("Arial", 10, Font.ITALIC);
    private static Font arial_10_bold_italic = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);

    // diffs fonts
    // for credits & description removals
    private static Font arial_10_red_strikethrough = FontFactory.getFont
            ("Arial", 10, Font.STRIKETHRU, BaseColor.RED);

    // for credits & description add-ons
    private static Font arial_10_blue_underline = FontFactory.getFont
            ("Arial", 10, Font.UNDERLINE, BaseColor.BLUE);

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

    public boolean generatePDF(int package_id){

        Document doc = new Document(PageSize.A4.rotate());
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        if(requestPackage == null){ return false;}

        try {

            // package.pdf
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Keeran\\Desktop\\cms\\package_4.pdf"));
            //PdfWriter.getInstance(doc, byte_stream);

            doc.open();

            // for each page
            for(Request request : requestPackage.getRequests()){

                // o for original course
                Course o = courseRepository.findById(request.getOriginalId());
                // c for changed course
                Course c = courseRepository.findById(request.getTargetId());

                addCoursePreface(doc, request, o, c);
                addCourseDiffTable(doc, request, o, c);
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
            Phrase years = new Phrase();
            years.add(new Chunk("Calendar for Academic Year: ", times_10_bold));
            years.add(new Chunk("2020/2021", times_10));
            years.add(new Chunk(Chunk.NEWLINE));
            years.add(new Chunk("Implementation Month/Year: ", times_10_bold));
            years.add(new Chunk("May 2020", times_10));

            Paragraph preface2 = new Paragraph();
            preface2.add(years);
            preface2.setAlignment(Element.ALIGN_RIGHT);

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
            calendar.add(new Chunk(c.getProgram().getDepartment().getCalendar().getSectionId(), times_10));

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

    private void addCourseDiffTable(Document doc, Request request, Course o, Course c) throws FileNotFoundException, DocumentException {

        try {
            // Creates a table with 2 column.
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

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
            table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold)));
            table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold)));
            table.completeRow();

            // course paragraph
            Paragraph original_paragraph = new Paragraph();
            Paragraph changed_paragraph = new Paragraph();

            // course phrases
            // name and number
            Phrase original_name_phrase = new Phrase();
            Phrase changed_name_phrase = new Phrase();

            if(o_name.length() < c_name.length())
                size = o_name.length();
            else
                size = c_name.length();

            for (int i = 0; i < size; i++) {
                processNameDifferences(original_name_phrase, changed_name_phrase, o_name.charAt(i), c_name.charAt(i));
            }

            // original was longer in length, all of which should be red and strikethrough
            if(size == c_name.length()) {
                remainder = o_name.substring(size);
                original_name_phrase.add(new Chunk(remainder, arial_10_red_bold).setUnderline(0.1f, 3f));
            }
            else {
                remainder = c_name.substring(size);
                changed_name_phrase.add(new Chunk(remainder, arial_10_blue_bold).setUnderline(0.1f, -1f));
            }

            // title
            Phrase original_title_phrase = new Phrase();
            Phrase changed_title_phrase = new Phrase();

            if(o_title.length() < c_title.length())
                size = o_title.length();
            else
                size = c_title.length();

            for (int i = 0; i < size; i++) {
                processTitleDifferences(original_title_phrase, changed_title_phrase, o_title.charAt(i), c_title.charAt(i));
            }

            // original was longer in length, all of which should be red and strikethrough
            if(size == c_title.length()) {
                remainder = o_title.substring(size);
                original_title_phrase.add(new Chunk(remainder, arial_10_red_bold_italic).setUnderline(0.1f, 3f));
            }
            else {
                remainder = c_title.substring(size);
                changed_title_phrase.add(new Chunk(remainder, arial_10_blue_bold_italic).setUnderline(0.1f, -1f));
            }

            // credits
            Phrase original_credits_phrase = new Phrase();
            Phrase changed_credits_phrase = new Phrase();

            if(o_credits.length() < c_credits.length())
                size = o_credits.length();
            else
                size = c_credits.length();

            for (int i = 0; i < size; i++) {
                processCreditAndBodyDifferences(original_credits_phrase, changed_credits_phrase, o_credits.charAt(i), c_credits.charAt(i));
            }

            // original was longer in length, all of which should be red and strikethrough
            if(size == c_credits.length()) {
                remainder = o_credits.substring(size);
                original_credits_phrase.add(new Chunk(remainder, arial_10_red_strikethrough));
            }
            else {
                remainder = c_credits.substring(size);
                changed_credits_phrase.add(new Chunk(remainder, arial_10_blue_underline));
            }

            // body = chunks of requisites & descriptions
            Phrase original_body_phrase = new Phrase();
            Phrase changed_body_phrase = new Phrase();

            if(o_body.length() < c_body.length())
                size = o_body.length();
            else
                size = c_body.length();

            for (int i = 0; i < size; i++) {
                processCreditAndBodyDifferences(original_body_phrase, changed_body_phrase, o_body.charAt(i), c_body.charAt(i));
            }

            // original was longer in length, all of which should be red and strikethrough
            if(size == c_body.length()) {
                remainder = o_body.substring(size);
                original_body_phrase.add(new Chunk(remainder, arial_10_red_strikethrough));
            }
            else {
                remainder = c_body.substring(size);
                changed_body_phrase.add(new Chunk(remainder, arial_10_blue_underline));
            }

            // notes
            Phrase original_note_phrase = new Phrase();
            Phrase changed_note_phrase = new Phrase();

            if(o_note.length() < c_note.length())
                size = o_note.length();
            else
                size = c_note.length();

            for (int i = 0; i < size; i++) {
                processNoteDifferences(original_note_phrase, changed_note_phrase, o_note.charAt(i), c_note.charAt(i));
            }

            // original was longer in length, all of which should be red and strikethrough
            if(size == c_note.length()) {
                remainder = o_note.substring(size);
                original_note_phrase.add(new Chunk(remainder, arial_10_red_italic).setUnderline(0.1f, 3f));
            }
            else {
                remainder = c_note.substring(size);
                changed_note_phrase.add(new Chunk(remainder, arial_10_blue_italic).setUnderline(0.1f, -1f));
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
            table.addCell(new PdfPCell(original_paragraph));
            table.addCell(new PdfPCell(changed_paragraph));
            table.completeRow();

            // add rationale cell which spans 2 columns
            Phrase rationale_phrase = new Phrase();

            rationale_phrase.add(new Chunk("Rationale:", arial_10));
            rationale_phrase.add(Chunk.NEWLINE);
            rationale_phrase.add(new Chunk(rationale, arial_10));

            PdfPCell rationale_cell = new PdfPCell(rationale_phrase);
            rationale_cell.setColspan(2);
            table.addCell(rationale_cell);
            table.completeRow();

            // add resource implications cell which spans 2 columns
            Phrase resource_phrase = new Phrase();

            resource_phrase.add(new Chunk("Resource Implications:", arial_10));
            resource_phrase.add(Chunk.NEWLINE);
            resource_phrase.add(new Chunk(resource_implications, arial_10));

            PdfPCell resource_cell = new PdfPCell(resource_phrase);
            resource_cell.setColspan(2);
            table.addCell(resource_cell);
            table.completeRow();

            doc.add(table);

        }catch(DocumentException e){
            e.printStackTrace();
        }
    }

    private String stringifyRequisites(Collection<Requisite> requisites) {

        StringBuilder r = new StringBuilder("Prerequisite: ");

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
            else
                r.append(". ");
        }

        return r.toString();
    }

    private void processNameDifferences(Phrase original, Phrase changed, char o, char c){

        if(o != c){
            original.add(new Chunk(o, arial_10_red_bold).setUnderline(0.1f, 3f));
            changed.add(new Chunk(c, arial_10_blue_bold).setUnderline(0.1f, -1f));
        }
        else{
            original.add(new Chunk(o, arial_10_bold));
            changed.add(new Chunk(c, arial_10_bold));
        }
    }

    private void processTitleDifferences(Phrase original, Phrase changed, char o, char c){

        if(o != c){
            original.add(new Chunk(o, arial_10_red_bold_italic).setUnderline(0.1f, 3f));
            changed.add(new Chunk(c, arial_10_blue_bold_italic).setUnderline(0.1f, -1f));
        }
        else{
            original.add(new Chunk(o, arial_10_bold_italic));
            changed.add(new Chunk(c, arial_10_bold_italic));
        }
    }

    private void processCreditAndBodyDifferences(Phrase original, Phrase changed, char o, char c){

        if(o != c){
            original.add(new Chunk(o, arial_10_red_strikethrough));
            changed.add(new Chunk(c, arial_10_blue_underline));
        }
        else{
            original.add(new Chunk(o, arial_10));
            changed.add(new Chunk(c, arial_10));
        }
    }

    private void processNoteDifferences(Phrase original, Phrase changed, char o, char c){

        if(o != c){
            original.add(new Chunk(o, arial_10_red_italic).setUnderline(0.1f, 3f));
            changed.add(new Chunk(c, arial_10_blue_italic).setUnderline(0.1f, -1f));
        }
        else{
            original.add(new Chunk(o, arial_10_italic));
            changed.add(new Chunk(c, arial_10_italic));
        }
    }

}
