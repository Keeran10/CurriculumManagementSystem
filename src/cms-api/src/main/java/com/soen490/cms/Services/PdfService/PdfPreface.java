package com.soen490.cms.Services.PdfService;

import com.itextpdf.text.*;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.DegreeRequirement;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Repositories.SectionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

import static com.soen490.cms.Services.PdfService.PdfUtil.times_10;
import static com.soen490.cms.Services.PdfService.PdfUtil.times_10_bold;

@Service
@Log4j2
public class PdfPreface {

    @Autowired
    private SectionRepository sectionRepository;

    /**
     * Adds the course metadata to the document page.
     * @param doc The document used to write the course preface.
     * @param request The request object containing the course metadata.
     * @param o The original course information.
     * @param c The changed course information.
     */
    public Document addCoursePreface(Document doc, Request request, Course o, Course c){

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
        else {
            log.error("Non-existent request type: " + request_type);
            return null;
        }

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
        years.add(new Chunk("2021/2022\n", times_10));
        years.add(new Chunk("Implementation Month/Year: ", times_10_bold));
        years.add(new Chunk("May 2021", times_10));

        preface2.add(years);
        preface2.setAlignment(Element.ALIGN_RIGHT);

        // last preface segment aligned left
        Paragraph preface3 = new Paragraph();
        preface3.setTabSettings(new TabSettings(200f));

        // faculty phrase
        Phrase faculty = new Phrase();
        faculty.add(new Chunk("Faculty/School:", times_10_bold));
        faculty.add(Chunk.TABBING);

        if(request_type == 3 && o.getProgram() != null)
            faculty.add(new Chunk(o.getProgram().getDepartment().getFaculty().getName(), times_10));
        else if(c.getProgram() != null)
            faculty.add(new Chunk(c.getProgram().getDepartment().getFaculty().getName(), times_10));

        preface3.add(faculty);
        preface3.add(Chunk.NEWLINE);

        // department phrase
        Phrase department = new Phrase();
        department.add(new Chunk("Department:", times_10_bold));
        department.add(Chunk.TABBING);

        if(request_type == 3 && o.getProgram() != null)
            department.add(new Chunk(o.getProgram().getDepartment().getName(), times_10));
        else if(c.getProgram() != null)
            department.add(new Chunk(c.getProgram().getDepartment().getName(), times_10));

        preface3.add(department);
        preface3.add(Chunk.NEWLINE);

        // program phrase
        Phrase program = new Phrase();
        program.add(new Chunk("Program:", times_10_bold));
        program.add(Chunk.TABBING);

        if(request_type == 3 && o.getProgram() != null)
            program.add(new Chunk(o.getProgram().getName(), times_10));
        else if(c.getProgram() != null)
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
        if(c.getLevel() <= 1)
            calendar.add(new Chunk("Undergraduate Calendar Section:", times_10_bold));
        else
            calendar.add(new Chunk("Graduate Page Number:", times_10_bold));

        // Todo: calendar needs a better design
        calendar.add(Chunk.TABBING);

        List<String> sections = null;

        sections = sectionRepository.findByTarget("course", o.getId());

        String section = "";

        if(sections.isEmpty() && o.getId() != 0){
            sections = sectionRepository.findByTarget("degree", o.getDegreeRequirements().get(0).getDegree().getId());
        }
        if(sections.isEmpty() && o.getId() != 0){
            sections = sectionRepository.findByTarget("department", o.getProgram().getDepartment().getId());
        }
        if(sections.isEmpty()){
            section = "N/A";
        }
        if(!sections.isEmpty()){

            for(String s : sections)
                section += "§" + s + " ";
        }

        calendar.add(new Chunk(section, times_10));

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

        return doc;
        /*
        try {
            addCourseDiffTable(doc, request, o, c);
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        */

    }
}
