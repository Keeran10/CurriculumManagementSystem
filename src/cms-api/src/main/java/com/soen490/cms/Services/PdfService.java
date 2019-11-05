package com.soen490.cms.Services;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.DegreeRequirement;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import javafx.scene.text.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PdfService {

    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;

    // fonts
    private static Font arial_10 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
    private static Font arial_10_bold = FontFactory.getFont("Arial", 10, Font.BOLD);
    private static Font arial_10_italic = FontFactory.getFont("Arial", 10, Font.ITALIC);
    private static Font arial_10_bold_italic = FontFactory.getFont("Arial", 10, Font.BOLDITALIC);
    private static Font times_10 = new Font(Font.FontFamily.TIMES_ROMAN, 10);
    private static Font times_10_bold = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);

    // colors

    public Document generatePDF(int package_id){

        Document doc = new Document(PageSize.A4.rotate());
        //doc.setMargins((float)0.05, (float)0.05, (float)1, (float)1);

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        if(requestPackage == null){ return null;}

        try {
            // package.pdf
            PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Keeran\\Desktop\\cms\\package_2.pdf"));

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
        }

        return doc;
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

            String o_name = o.getName() + " " + o.getNumber();
            String o_title = " " + o.getTitle();
            String o_credits = " (" + o.getCredits() + " credits)";
            String c_name = c.getName() + " " + c.getNumber();
            String c_title = " " + c.getTitle();
            String c_credits = " (" + c.getCredits() + " credits)";

            String original_note = o.getNote();
            String changed_note = c.getNote();

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
            // headers
            Phrase original_header_phrase = new Phrase();
            Phrase changed_header_phrase = new Phrase();

            original_header_phrase.add(new Chunk(o_name, arial_10_bold));
            original_header_phrase.add(new Chunk(o_title, arial_10_bold_italic));
            original_header_phrase.add(new Chunk(o_credits, arial_10));

            changed_header_phrase.add(new Chunk(c_name, arial_10_bold));
            changed_header_phrase.add(new Chunk(c_title, arial_10_bold_italic));
            changed_header_phrase.add(new Chunk(c_credits, arial_10));

            // body = chunks of requisites & descriptions
            Phrase original_body_phrase = new Phrase();
            Phrase changed_body_phrase = new Phrase();

            //original_body_phrase.add(new Chunk(o.getRequisites().toString(), arial_10));
            original_body_phrase.add(new Chunk(o.getDescription(), arial_10));
            //changed_body_phrase.add(new Chunk(c.getRequisites().toString(), arial_10));
            changed_body_phrase.add(new Chunk(c.getDescription(), arial_10));

            // notes
            Phrase original_note_phrase = new Phrase();
            Phrase changed_note_phrase = new Phrase();

            original_note_phrase.add(new Chunk(original_note, arial_10_italic));
            changed_note_phrase.add(new Chunk(changed_note, arial_10_italic));

            // ...

            // add all course phrases to paragraph
            original_paragraph.add(original_header_phrase);
            original_paragraph.add(Chunk.NEWLINE);
            original_paragraph.add(original_body_phrase);
            original_paragraph.add(Chunk.NEWLINE);
            original_paragraph.add(original_note_phrase);

            changed_paragraph.add(changed_header_phrase);
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

}
