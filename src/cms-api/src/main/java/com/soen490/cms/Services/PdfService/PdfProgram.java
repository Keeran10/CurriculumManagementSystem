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


    /**
     * Adds a program page to the dossier pdf file.
     * @param requestPackage
     * @return
     */
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


    /**
     * Generates a program core table with a list of differentiated courses.
     * @param doc
     * @param requestPackage
     * @param core
     * @throws FileNotFoundException
     * @throws DocumentException
     */
    private void addProgramDiffTable(Document doc, RequestPackage requestPackage, String core)
            throws FileNotFoundException, DocumentException {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        float CELL_PADDING = 7f;
        float LINE_SPACING = 17f;

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
        Chunk glue = new Chunk(new VerticalPositionMark()).setLineHeight(LINE_SPACING);
        header.add(new Chunk(core, arial_10_bold));
        header.add(glue);
        header.add(new Chunk("Credits", arial_10_italic));
        header.add(Chunk.NEWLINE);

        original_paragraph.add(header);
        changed_paragraph.add(header);

        List<Course> changed_courses = getChangedCourses(requestPackage, courses_id);

        String present_name = "";
        String proposed_name = "";
        String present_title = "";
        String proposed_title = "";
        String present_credits = "";
        String proposed_credits = "";
        int index;
        int ctr;

        for(Course c : changed_courses){

            ctr = 0;
            index = 0;

            if(present_courses.isEmpty()){

                PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                        "", c.getName() + " " + c.getNumber());

                changed_paragraph.add(Chunk.TABBING);

                PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                        "", c.getTitle());

                changed_paragraph.add(glue);

                PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                        "", String.format("%.2f", c.getCredits()));

                changed_paragraph.add(Chunk.NEWLINE);
            }

            for(Course o : present_courses){

                present_name = o.getName() + " " + o.getNumber();
                proposed_name = c.getName() + " " + c.getNumber();
                present_title = o.getTitle();
                proposed_title = c.getTitle();
                present_credits = String.format("%.2f", o.getCredits());
                proposed_credits = String.format("%.2f", c.getCredits());


                if(o.getName().equals(c.getName()) && o.getNumber() == c.getNumber()){

                    for(int i = 0; i < ctr; i++){

                        PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                                present_courses.get(i).getName() + " " + present_courses.get(i).getNumber(), "");

                        original_paragraph.add(Chunk.TABBING);

                        PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                                present_courses.get(i).getTitle(), "");

                        original_paragraph.add(glue);

                        PdfUtil.processProgramDifference(original_paragraph, changed_paragraph,
                                String.format("%.2f", present_courses.get(i).getCredits()), "");

                        original_paragraph.add(Chunk.NEWLINE);
                    }



                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, present_name, proposed_name);

                    original_paragraph.add(Chunk.TABBING);
                    changed_paragraph.add(Chunk.TABBING);

                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, present_title, proposed_title);

                    original_paragraph.add(glue);
                    changed_paragraph.add(glue);

                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, present_credits, proposed_credits);

                    original_paragraph.add(Chunk.NEWLINE);
                    changed_paragraph.add(Chunk.NEWLINE);

                    index = ctr;
                    break;
                }
                else if(ctr == present_courses.size() - 1 || present_courses.isEmpty()){

                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, "", proposed_name);

                    changed_paragraph.add(Chunk.TABBING);

                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, "", proposed_title);

                    changed_paragraph.add(glue);

                    PdfUtil.processProgramDifference(original_paragraph, changed_paragraph, "", proposed_credits);

                    changed_paragraph.add(Chunk.NEWLINE);

                    index = -1;
                }

                ctr++;
            }

            if(present_courses.size() != 0 && !present_courses.isEmpty()){
                while(index != -1) {
                    present_courses.remove(0);
                    index--;
                }

            }

        }

        // once all program details are done
        table.addCell(new PdfPCell(original_paragraph)).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(changed_paragraph)).setPadding(CELL_PADDING);
        table.completeRow();

        try {
            doc.add(table);
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }


    /**
     * Retrieve courses from a specific core while overriding original data
     * @param requestPackage
     * @param ids
     * @return
     */
    private List<Course> getChangedCourses(RequestPackage requestPackage, List<Integer> ids) {

        List<Course> present_courses = new ArrayList<>();

        for(Request request : requestPackage.getRequests()){

            if(request.getRequestType() != 1) {

                int index = ids.indexOf(request.getOriginalId());

                if (index != -1) {
                    ids.remove(index);
                }
            }
        }

        for(int id : ids){
            Course course = courseRepository.findById(id);
            present_courses.add(course);
        }

        present_courses.sort(Comparator.comparing(Course::getName));
        present_courses.sort(Comparator.comparing(Course::getNumber));

        return present_courses;
    }
}
