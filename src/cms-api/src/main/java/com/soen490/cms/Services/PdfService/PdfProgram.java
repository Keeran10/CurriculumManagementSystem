package com.soen490.cms.Services.PdfService;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.DegreeRequirementRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;
import static com.soen490.cms.Services.PdfService.PdfUtil.arial_10;
import static com.soen490.cms.Services.PdfService.PdfUtil.column_font;


@Service
@Log4j2
public class PdfProgram {

    @Autowired
    private PdfPreface pdfPreface;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DegreeRequirementRepository degreeRequirementRepository;


    public ArrayList<ByteArrayOutputStream> addProgramPage(RequestPackage requestPackage){

        List<String> affected_cores = new ArrayList<>();
        boolean exists;

        for(Request request : requestPackage.getRequests()){

            exists = false;
            List<DegreeRequirement> degreeRequirements = new ArrayList<>();

            if(request.getRequestType() == 1){
                degreeRequirements = courseRepository.findById(request.getTargetId()).getDegreeRequirements();
            }
            else if(request.getRequestType() == 3){
                degreeRequirements = courseRepository.findById(request.getOriginalId()).getDegreeRequirements();
            }
            else {

                List<DegreeRequirement> originals = courseRepository.findById(request.getOriginalId()).getDegreeRequirements();
                List<DegreeRequirement> changes = courseRepository.findById(request.getTargetId()).getDegreeRequirements();

                if(originals.size() > changes.size()){

                    for(DegreeRequirement dro : originals){

                        boolean exist = false;

                        for(DegreeRequirement drc : changes){

                            if(dro.getCore().equals(drc.getCore()))
                                exist = true;
                        }

                        if(!exist)
                            degreeRequirements.add(dro);
                    }
                }
                else if(originals.size() < changes.size()){

                    for(DegreeRequirement drc : changes){

                        boolean exist = false;

                        for(DegreeRequirement dro : originals){

                            if(dro.getCore().equals(drc.getCore()))
                                exist = true;
                        }

                        if(!exist)
                            degreeRequirements.add(drc);
                    }
                }
                else{

                    if (originals.get(0).getDegree().getId() != changes.get(0).getDegree().getId() &&
                            originals.get(0).getCore().equals(changes.get(0).getCore())) {

                        // outlier case for "electives" section in different degrees
                        String o_elective = originals.get(0).getCore() + "; " + originals.get(0).getDegree().getId();
                        String c_elective = changes.get(0).getCore() + "; " + changes.get(0).getDegree().getId();
                        boolean o_exist = false;
                        boolean c_exist = false;

                        for(String core : affected_cores){
                            if(o_elective.equals(core))
                                o_exist = true;
                            if(c_elective.equals(core))
                                c_exist = true;
                        }

                        if(!o_exist)
                            affected_cores.add(o_elective);
                        if(!c_exist)
                            affected_cores.add(c_elective);
                    }
                    else if(originals.get(0).getDegree().getId() == changes.get(0).getDegree().getId() &&
                            !originals.get(0).getCore().equals(changes.get(0).getCore())){

                        degreeRequirements.add(originals.get(0));
                        degreeRequirements.add(changes.get(0));
                    }
                }
            }

            for(DegreeRequirement degreeRequirement : degreeRequirements){

                for(String core : affected_cores){
                    if(degreeRequirement.getCore().equals(core))
                        exists = true;
                }

                if(!exists)
                    affected_cores.add(degreeRequirement.getCore());
            }
        }

        if(affected_cores.isEmpty())
            return null;

        ArrayList<ByteArrayOutputStream> program_streams = new ArrayList<>();

        try {
            for(String core : affected_cores){

                log.info("Generating pdf page for " + core + ".");

                Document doc = new Document();
                doc.setPageSize(PageSize.A4.rotate());
                doc.setMargins(25, 25, 10, 25);

                ByteArrayOutputStream program_stream = new ByteArrayOutputStream();

                PdfWriter.getInstance(doc, program_stream);

                doc.open();

                // generate program page for affected core
                doc = pdfPreface.addProgramPreface(doc, requestPackage, core);
                addProgramDiffTable(doc, requestPackage, core);

                doc.close();

                program_streams.add(program_stream);

            }

        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
            return null;
        }

        return program_streams;
    }


    private void addProgramDiffTable(Document doc, RequestPackage requestPackage, String core)
            throws FileNotFoundException, DocumentException {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        float CELL_PADDING = 7f;
        float LINE_SPACING = 18f;
        int size = 0;
        String remainder;

        List<Integer> courses_id = degreeRequirementRepository.findCoursesByCore(core);
        List<Course> present_courses = new ArrayList<>();

        for(int id : courses_id){

            Course course = courseRepository.findById(id);

            if(course != null && course.getIsActive() != 0)
                present_courses.add(course);
        }

        present_courses.sort(Comparator.comparing(Course::getName));
        present_courses.sort(Comparator.comparing(Course::getNumber));

        // static headers
        table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold))).setPadding(3f);
        table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold))).setPadding(3f);
        table.completeRow();

        // program paragraph
        Paragraph original_paragraph = new Paragraph();
        Paragraph changed_paragraph = new Paragraph();

        // paragraph header
        // glue used to separate the following two chunks to left and right
        Phrase header = new Phrase();
        Chunk glue = new Chunk(new VerticalPositionMark());
        header.add(new Chunk(core, arial_10_bold));
        header.add(glue);
        header.add(new Chunk("Credits", arial_10_italic));
        header.add(Chunk.NEWLINE);

        original_paragraph.add(header);
        changed_paragraph.add(header);

        for(Course course : present_courses) {

            // name and number
            Phrase original_name_phrase = new Phrase();
            Phrase changed_name_phrase = new Phrase();

            // title
            Phrase original_title_phrase = new Phrase();
            Phrase changed_title_phrase = new Phrase();

            // credits
            Phrase original_credits_phrase = new Phrase();
            Phrase changed_credits_phrase = new Phrase();

        /*
        if(request.getRequestType() == 1){

            changed_name_phrase.add(new Chunk(c_name, arial_10_bold));
            changed_title_phrase.add(new Chunk(c_title, arial_10_bold_italic));
            changed_credits_phrase.add(new Chunk(c_credits, arial_10));


        }
        else if(request.getRequestType() == 2) {

            processDifferences(original_name_phrase, changed_name_phrase, o_name, c_name, 1);
            processDifferences(original_title_phrase, changed_title_phrase, o_title, c_title, 2);
            processDifferences(original_credits_phrase, changed_credits_phrase, o_credits, c_credits, 3);


        }
        else if(request.getRequestType() == 3){
        */
            original_name_phrase.add(new Chunk(course.getName() + " " + course.getNumber(), arial_10));
            original_title_phrase.add(new Chunk(course.getTitle(), arial_10).setLineHeight(LINE_SPACING));
            original_credits_phrase.add(new Chunk(String.format("%.2f", course.getCredits()), arial_10));
        //}


            // add all course phrases to paragraph
            original_paragraph.add(original_name_phrase);
            original_paragraph.add(Chunk.TABBING);
            original_paragraph.add(original_title_phrase);
            original_paragraph.add(glue);
            original_paragraph.add(original_credits_phrase);
            original_paragraph.add(Chunk.NEWLINE);
            /*
            changed_paragraph.add(changed_name_phrase);
            changed_paragraph.add(changed_title_phrase);
            changed_paragraph.add(changed_credits_phrase);
            changed_paragraph.add(Chunk.NEWLINE);
            */
        }

        // once all course details are done
        table.addCell(new PdfPCell(original_paragraph)).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(changed_paragraph)).setPadding(CELL_PADDING);
        table.completeRow();

        try {
            doc.add(table);
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }
}
