package com.soen490.cms.Services.PdfService.PdfSections;


import com.itextpdf.text.*;
import com.soen490.cms.Models.Degree;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.SubSection70719;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.SubSection70719Repository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.soen490.cms.Services.PdfService.PdfUtil.times_10;
import static com.soen490.cms.Services.PdfService.PdfUtil.times_10_bold;

@Service
@Log4j2
public class PdfSection70719 {


    @Autowired
    private SubSection70719Repository subSection70719Repository;
    @Autowired
    private DegreeRepository degreeRepository;

    static int SECTION70719_DEGREE_ID = 1;

    public void addSectionPage(Document doc, Request request) {

        SubSection70719 subSection70719_present = null;
        SubSection70719 subSection70719_proposed = null;

        if(request.getRequestType() == 2) {
            subSection70719_present = subSection70719Repository.findById(request.getOriginalId());
            subSection70719_proposed = subSection70719Repository.findById(request.getTargetId());
        }

        doc = addSectionPreface(doc, request, subSection70719_proposed);
        addSectionDiffTable(doc, request, subSection70719_present, subSection70719_proposed);
    }


    public Document addSectionPreface(Document doc, Request request, SubSection70719 subSection70719){

        // preface paragraph
        Paragraph preface1 = new Paragraph();

        Phrase page_header = new Phrase();
        page_header.add(new Chunk("PACKAGE_" + request.getRequestPackage().getId(), times_10));

        preface1.add(page_header);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        Phrase request_type_phrase = new Phrase();
        request_type_phrase.add(new Chunk("CALENDAR CHANGE: ", times_10_bold));
        request_type_phrase.add(new Chunk(subSection70719.getSectionTitle(), times_10));


        preface1.add(request_type_phrase);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        // request academic level
        Phrase proposed = new Phrase();
        proposed.add(new Chunk("Proposed: ", times_10_bold));

        Degree degree = degreeRepository.findById(SECTION70719_DEGREE_ID);

        if(degree.getLevel() < 2)
            proposed.add(new Chunk("Undergraduate Curriculum Changes", times_10));
        else
            proposed.add(new Chunk("Graduate Curriculum Changes", times_10));

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
        faculty.add(new Chunk(degree.getProgram().getDepartment().getFaculty().getName(), times_10));
        preface3.add(faculty);
        preface3.add(Chunk.NEWLINE);

        // department phrase
        Phrase department = new Phrase();
        department.add(new Chunk("Department:", times_10_bold));
        department.add(Chunk.TABBING);
        department.add(new Chunk(degree.getProgram().getDepartment().getName(), times_10));
        preface3.add(department);
        preface3.add(Chunk.NEWLINE);

        // program phrase
        Phrase program = new Phrase();
        program.add(new Chunk("Program:", times_10_bold));
        program.add(Chunk.TABBING);
        program.add(new Chunk(degree.getProgram().getName(), times_10));
        preface3.add(program);
        preface3.add(Chunk.NEWLINE);

        // degree phrase
        Phrase degrees = new Phrase();
        degrees.add(new Chunk("Degree:", times_10_bold));
        degrees.add(Chunk.TABBING);
        degrees.add(new Chunk(degree.getName(), times_10));
        preface3.add(degrees);
        preface3.add(Chunk.NEWLINE);

        // calendar phrase
        Phrase calendar = new Phrase();
        if(degree.getLevel() < 2)
            calendar.add(new Chunk("Undergraduate Calendar Section:", times_10_bold));
        else
            calendar.add(new Chunk("Graduate Page Number:", times_10_bold));

        calendar.add(Chunk.TABBING);

        calendar.add(new Chunk("§" + subSection70719.getSectionId(), times_10));

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
    }


    private void addSectionDiffTable(Document doc, Request request, SubSection70719 subSection70719_present, SubSection70719 subSection70719_proposed) {


    }
}
