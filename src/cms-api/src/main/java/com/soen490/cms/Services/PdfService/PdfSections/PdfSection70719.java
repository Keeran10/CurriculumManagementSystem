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
package com.soen490.cms.Services.PdfService.PdfSections;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.soen490.cms.Models.Degree;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Sections.Section71709;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.SectionsRepositories.Section71709Repository;
import com.soen490.cms.Services.PdfService.PdfUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;
import static com.soen490.cms.Services.PdfService.PdfUtil.arial_10;
import static com.soen490.cms.Services.PdfService.PdfUtil.column_font;

@Service
@Log4j2
public class PdfSection70719 {


    @Autowired
    private Section71709Repository section71709Repository;
    @Autowired
    private DegreeRepository degreeRepository;

    private static int SECTION70719_DEGREE_ID = 1;

    public void addSectionPage(Document doc, Request request) {

        Section71709 section70719_present = null;
        Section71709 section70719_proposed = null;

        if(request.getRequestType() == 2) {
            section70719_present = section71709Repository.findById(request.getOriginalId());
            section70719_proposed = section71709Repository.findById(request.getTargetId());
        }

        doc = addSectionPreface(doc, request, section70719_proposed);
        addSectionDiffTable(doc, request, section70719_present, section70719_proposed);
    }


    public Document addSectionPreface(Document doc, Request request, Section71709 section71709){

        // preface paragraph
        Paragraph preface1 = new Paragraph();

        Phrase page_header = new Phrase();
        page_header.add(new Chunk(request.getRequestPackage().getDepartment().getName() + ", dossier_" +
                request.getRequestPackage().getId(), times_10));

        preface1.add(page_header);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        Phrase request_type_phrase = new Phrase();
        request_type_phrase.add(new Chunk("CALENDAR CHANGE: ", times_10_bold));
        request_type_phrase.add(new Chunk(section71709.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section71709.getSectionId(), times_10));

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


    private void addSectionDiffTable(Document doc, Request request, Section71709 section70719_present, Section71709 section70719_proposed) {

        // Creates a table with 2 column.
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        float CELL_PADDING = 7f;
        float LINE_SPACING = 17f;

        // static headers
        table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold))).setPadding(3f);
        table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold))).setPadding(3f);
        table.completeRow();

        Paragraph present = new Paragraph();
        Paragraph proposed = new Paragraph();

        Phrase header_present = new Phrase();
        Phrase header_proposed = new Phrase();

        PdfUtil.processDifferences(header_present, header_proposed,
                section70719_present.getSectionId() + "\t" + section70719_present.getSectionTitle(),
                section70719_proposed.getSectionId() + "\t" + section70719_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section70719_present.getFirstParagraph(), section70719_proposed.getFirstParagraph(),
                6);

        Phrase first_core_present = new Phrase();
        Phrase first_core_proposed = new Phrase();

        PdfUtil.processDifferences(first_core_present, first_core_proposed,
                section70719_present.getFirstCore(), section70719_proposed.getFirstCore(),
                1);

        Phrase second_core_present = new Phrase();
        Phrase second_core_proposed = new Phrase();

        PdfUtil.processDifferences(second_core_present, second_core_proposed,
                section70719_present.getSecondCore(), section70719_proposed.getSecondCore(),
                1);

        present.add(header_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(header_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(first_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(first_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        Chunk engineering_section = new Chunk("§71.20.5", arial_10);
        engineering_section.setGenericTag("http://pages.itextpdf.com/ebook-stackoverflow-questions.html");
        //engineering_section.setAction("");

        present.add(first_core_present);
        present.add(Chunk.NEWLINE);
        present.add(new Chunk("See ", arial_10).setLineHeight(LINE_SPACING));
        present.add(engineering_section);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);

        proposed.add(first_core_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(new Chunk("See ", arial_10).setLineHeight(LINE_SPACING));
        proposed.add(engineering_section);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(second_core_present);
        proposed.add(second_core_proposed);

        // once all program details are done
        table.addCell(new PdfPCell(present)).setPadding(CELL_PADDING);
        table.addCell(new PdfPCell(proposed)).setPadding(CELL_PADDING);
        table.completeRow();

        // add rationale cell which spans 2 columns
        String rationale = request.getRationale();
        Phrase rationale_phrase = new Phrase();

        rationale_phrase.add(new Chunk("Rationale:", column_font).setUnderline(0.1f, -1f));
        rationale_phrase.add(Chunk.NEWLINE);

        if(rationale != null && !rationale.equals(""))
            rationale_phrase.add(new Chunk(rationale, arial_10));
        else
            rationale_phrase.add(new Chunk("None.", arial_10));

        PdfPCell rationale_cell = new PdfPCell(rationale_phrase);
        rationale_cell.setColspan(2);
        table.addCell(rationale_cell).setPadding(CELL_PADDING);
        table.completeRow();

        // add resource implications cell which spans 2 columns
        String resource_implications = request.getResourceImplications();
        Phrase resource_phrase = new Phrase();

        resource_phrase.add(new Chunk("Resource Implications:", column_font).setUnderline(0.1f, -1f));
        resource_phrase.add(Chunk.NEWLINE);

        if(resource_implications != null && !resource_implications.equals(""))
            resource_phrase.add(new Chunk(resource_implications, arial_10));
        else
            resource_phrase.add(new Chunk("None.", arial_10));

        PdfPCell resource_cell = new PdfPCell(resource_phrase);
        resource_cell.setColspan(2);
        table.addCell(resource_cell).setPadding(CELL_PADDING);
        table.completeRow();

        try {
            doc.add(table);
        }catch(DocumentException e){
            e.printStackTrace();
        }
    }
}
