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
import com.soen490.cms.Models.Sections.Section71402;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.SectionsRepositories.Section71402Repository;
import com.soen490.cms.Services.PdfService.PdfUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;
import static com.soen490.cms.Services.PdfService.PdfUtil.arial_10;

@Service
@Log4j2
public class PdfSection71402 {

    @Autowired
    private Section71402Repository section71402Repository;

    @Autowired
    private DegreeRepository degreeRepository;

    private final int INDU_ENG_DEGREE_ID = 7;

    public void addSectionPage(Document document, Request request) {
        Section71402 section71402 = null;
        Section71402 proposedSection71402 = null;

        if(request.getRequestType() == 2) {
            section71402 = section71402Repository.findById(request.getOriginalId());
            proposedSection71402 = section71402Repository.findById(request.getTargetId());
        }

        document = addSectionPreface(document, request, proposedSection71402);
        addSectionDiffTable(document, request, section71402, proposedSection71402);
    }

    public Document addSectionPreface(Document doc, Request request, Section71402 section71402){

        // preface paragraph
        Paragraph preface1 = new Paragraph();

        Phrase pageHeader = new Phrase();
        pageHeader.add(new Chunk(request.getRequestPackage().getDepartment().getName() + ", dossier_" +
                request.getRequestPackage().getId(), times_10));

        preface1.add(pageHeader);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        Phrase requestTypePhrase = new Phrase();
        requestTypePhrase.add(new Chunk("CALENDAR CHANGE: ", times_10_bold));
        requestTypePhrase.add(new Chunk(section71402.getSectionTitle(), times_10));


        preface1.add(requestTypePhrase);
        preface1.add(Chunk.NEWLINE);
        preface1.add(Chunk.NEWLINE);

        // request academic level
        Phrase proposed = new Phrase();
        proposed.add(new Chunk("Proposed: ", times_10_bold));

        Degree degree = degreeRepository.findById(INDU_ENG_DEGREE_ID);

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

        calendar.add(new Chunk("§" + section71402.getSectionId(), times_10));

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

    private void addSectionDiffTable(Document document, Request request, Section71402 section71402, Section71402 proposedSection71402) {

        PdfPTable table = new PdfPTable(2);
        Paragraph present = new Paragraph();
        Paragraph proposed = new Paragraph();
        // header - section title (Section 71.40.2)
        Phrase headerPresent = new Phrase();
        Phrase headerProposed = new Phrase();
        // intro paragraph
        Phrase introParagraphPresent = new Phrase();
        Phrase introParagraphProposed = new Phrase();
        // engineering core
        Phrase firstCorePresent = new Phrase();
        Phrase firstCoreProposed = new Phrase();
        // indu eng core
        Phrase secondCorePresent = new Phrase();
        Phrase secondCoreProposed = new Phrase();
        // science core
        Phrase scienceHeaderPresent = new Phrase();
        Phrase scienceHeaderProposed = new Phrase();
        // science core body
        Phrase scienceDescriptionPresent = new Phrase();
        Phrase scienceDescriptionProposed = new Phrase();
        // electives header
        Phrase electivesHeaderPresent = new Phrase();
        Phrase electivesHeaderProposed = new Phrase();
        // electives paraghraph
        Phrase electivesDescriptionPresent = new Phrase();
        Phrase electivesDescriptionProposed = new Phrase();

        float cellPadding = 7f;
        float lineSpacing = 17f;

        // static headers
        table.addCell(new PdfPCell(new Phrase("Present Text", times_10_bold))).setPadding(3f);
        table.addCell(new PdfPCell(new Phrase("Proposed Text", times_10_bold))).setPadding(3f);

        // process differences
        // header
        PdfUtil.processDifferences(headerPresent, headerProposed,
                section71402.getSectionId() + "\t" + section71402.getSectionTitle(),
                proposedSection71402.getSectionId() + "\t" + proposedSection71402.getSectionTitle(), 1);
        // intro paragraph
        PdfUtil.processDifferences(introParagraphPresent, introParagraphProposed,
                section71402.getIntroParagraph(), proposedSection71402.getIntroParagraph(), 6);
        // engineering core
        PdfUtil.processDifferences(firstCorePresent, firstCoreProposed,
                section71402.getFirstCore(), proposedSection71402.getFirstCore(), 1);
        // indu engineering core
        PdfUtil.processDifferences(secondCorePresent, secondCoreProposed,
                section71402.getSecondCore(), proposedSection71402.getSecondCore(), 1);
        // science core header
        PdfUtil.processDifferences(scienceHeaderPresent, scienceHeaderProposed,
                section71402.getScienceHeader(), proposedSection71402.getScienceHeader(), 1);
        // science core description
        PdfUtil.processDifferences(scienceDescriptionPresent, scienceDescriptionProposed,
                section71402.getScienceDescription(), proposedSection71402.getScienceDescription(), 1);
        // electives header
        PdfUtil.processDifferences(electivesHeaderPresent, electivesHeaderProposed,
                section71402.getElectivesHeader(), proposedSection71402.getElectivesHeader(), 1);
        // electives/options description intro
        PdfUtil.processDifferences(electivesDescriptionPresent, electivesDescriptionProposed,
                section71402.getElectivesDescription(), proposedSection71402.getElectivesDescription(), 6);

        // add sections
        // header
        present.add(headerPresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(headerProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // eng core
        Chunk see = new Chunk("See ", arial_10).setLineHeight(lineSpacing);
        Chunk engSection = new Chunk("§71.20.5", arial_10)
                .setGenericTag("http://pages.itextpdf.com/ebook-stackoverflow-questions.html");

        present.add(firstCorePresent);
        present.add(Chunk.NEWLINE);
        present.add(see);
        present.add(engSection);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(firstCoreProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(see);
        proposed.add(engSection);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // indu core
        present.add(secondCorePresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(secondCoreProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // science core header
        present.add(scienceHeaderPresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(scienceHeaderProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // science core description
        present.add(scienceDescriptionPresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(scienceDescriptionProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // electives header
        present.add(electivesHeaderPresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(electivesHeaderProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        // electives description paragraph
        present.add(electivesDescriptionPresent);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(electivesDescriptionProposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        // add proposed changes row to pdf table
        table.addCell(new PdfPCell(present)).setPadding(cellPadding);
        table.addCell(new PdfPCell(proposed)).setPadding(cellPadding);
        table.completeRow();

        // add rationale cell
        String rationale = request.getRationale();
        Phrase rationalePhrase = new Phrase();
        PdfPCell rationaleCell = new PdfPCell();

        rationalePhrase.add(new Chunk("Rationale:", column_font).setUnderline(0.1f, -1f));
        rationalePhrase.add(Chunk.NEWLINE);

        if(rationale != null && !rationale.equals("")) {
            rationalePhrase.add(new Chunk(rationale, arial_10));
        } else {
            rationalePhrase.add(new Chunk("None.", arial_10));
        }

        rationaleCell.setPhrase(rationalePhrase);
        rationaleCell.setColspan(2);
        table.addCell(rationaleCell).setPadding(cellPadding);
        table.completeRow();

        // add resource implications cell
        String resourceImplications = request.getResourceImplications();
        Phrase resourcePhrase = new Phrase();
        PdfPCell resourceCell = new PdfPCell();

        resourcePhrase.add(new Chunk("Resource Implications:", column_font).setUnderline(0.1f, -1f));
        resourcePhrase.add(Chunk.NEWLINE);

        if(resourceImplications != null && !resourceImplications.equals("")) {
            resourcePhrase.add(new Chunk(resourceImplications, arial_10));
        } else {
            resourcePhrase.add(new Chunk("None.", arial_10));
        }

        resourceCell.setPhrase(resourcePhrase);
        resourceCell.setColspan(2);
        table.addCell(resourceCell).setPadding(cellPadding);
        table.completeRow();

        try {
            document.add(table);
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }
}
