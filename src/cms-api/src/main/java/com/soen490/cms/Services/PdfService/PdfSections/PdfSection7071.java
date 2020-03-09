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
import com.soen490.cms.Models.Sections.*;
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.SectionsRepositories.*;
import com.soen490.cms.Services.PdfService.PdfUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;
import static com.soen490.cms.Services.PdfService.PdfUtil.arial_10;
import static com.soen490.cms.Services.PdfService.PdfUtil.column_font;

@Service
@Log4j2
public class PdfSection7071{

    @Autowired
    private Section71701Repository section71701Repository;
    @Autowired
    private Section71702Repository section71702Repository;
    @Autowired
    private Section71703Repository section71703Repository;
    @Autowired
    private Section71704Repository section71704Repository;
    @Autowired
    private Section71705Repository section71705Repository;
    @Autowired
    private Section71706Repository section71706Repository;
    @Autowired
    private Section71707Repository section71707Repository;
    @Autowired
    private Section71708Repository section71708Repository;
    @Autowired
    private Section71709Repository section71709Repository;
    @Autowired
    private Section717010Repository section717010Repository;
    @Autowired
    private DegreeRepository degreeRepository;

    private static int SECTION70719_DEGREE_ID = 1;

    public boolean isCoreRequest(Request request, String section_id){

        if(section_id.contains("71.70.1")) {

            Section71701 present = section71701Repository.findById(request.getOriginalId());
            Section71701 proposed = section71701Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.2")) {

            Section71702 present = section71702Repository.findById(request.getOriginalId());
            Section71702 proposed = section71702Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph()) &&
                    present.getSecondParagraph().equals(proposed.getSecondParagraph()) &&
                    present.getThirdParagraph().equals(proposed.getThirdParagraph()) &&
                    present.getFourthParagraph().equals(proposed.getFourthParagraph()) &&
                    present.getFifthParagraph().equals(proposed.getFifthParagraph()) &&
                    present.getSixthParagraph().equals(proposed.getSixthParagraph()) &&
                    present.getSeventhParagraph().equals(proposed.getSeventhParagraph()) &&
                    present.getEightParagraph().equals(proposed.getEightParagraph()) &&
                    present.getNinthParagraph().equals(proposed.getNinthParagraph()) &&
                    present.getTenthParagraph().equals(proposed.getTenthParagraph()) &&
                    present.getEleventhParagraph().equals(proposed.getEleventhParagraph()) &&
                    present.getTwelfthParagraph().equals(proposed.getTwelfthParagraph()) &&
                    present.getThirteenthParagraph().equals(proposed.getThirteenthParagraph()) &&
                    present.getFourteenthParagraph().equals(proposed.getFourteenthParagraph()) &&
                    present.getFifteenthParagraph().equals(proposed.getFifteenthParagraph()) &&
                    present.getSixteenthParagraph().equals(proposed.getSixteenthParagraph()) &&
                    present.getSeventeenthParagraph().equals(proposed.getSeventeenthParagraph());
        }
        if(section_id.contains("71.70.3")) {

            Section71703 present = section71703Repository.findById(request.getOriginalId());
            Section71703 proposed = section71703Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.4")) {

            Section71704 present = section71704Repository.findById(request.getOriginalId());
            Section71704 proposed = section71704Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph()) &&
                    present.getSecondParagraph().equals(proposed.getSecondParagraph());
        }
        if(section_id.contains("71.70.5")) {

            Section71705 present = section71705Repository.findById(request.getOriginalId());
            Section71705 proposed = section71705Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.6")) {

            Section71706 present = section71706Repository.findById(request.getOriginalId());
            Section71706 proposed = section71706Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.7")) {

            Section71707 present = section71707Repository.findById(request.getOriginalId());
            Section71707 proposed = section71707Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.8")) {

            Section71708 present = section71708Repository.findById(request.getOriginalId());
            Section71708 proposed = section71708Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        if(section_id.contains("71.70.9")) {

            Section71709 present = section71709Repository.findById(request.getOriginalId());
            Section71709 proposed = section71709Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph()) &&
                    present.getSecondParagraph().equals(proposed.getSecondParagraph()) &&
                    present.getThirdParagraph().equals(proposed.getThirdParagraph());
        }
        if(section_id.contains("71.70.10")) {

            Section717010 present = section717010Repository.findById(request.getOriginalId());
            Section717010 proposed = section717010Repository.findById(request.getTargetId());

            return present.getSectionTitle().equals(proposed.getSectionTitle()) &&
                    present.getFirstParagraph().equals(proposed.getFirstParagraph());
        }
        return false;
    }

    public void addSectionPage(Document doc, Request request, String section_id) {

        if(section_id.contains("71.70.1")) {
            Section71701 section71701_present = null;
            Section71701 section71701_proposed = null;

            if (request.getRequestType() == 2) {
                section71701_present = section71701Repository.findById(request.getOriginalId());
                section71701_proposed = section71701Repository.findById(request.getTargetId());
            }

            doc = addSection1Preface(doc, request, section71701_proposed);
            addSection1DiffTable(doc, request, section71701_present, section71701_proposed);
        }
        if(section_id.contains("71.70.2")) {
            Section71702 section71702_present = null;
            Section71702 section71702_proposed = null;

            if (request.getRequestType() == 2) {
                section71702_present = section71702Repository.findById(request.getOriginalId());
                section71702_proposed = section71702Repository.findById(request.getTargetId());
            }

            doc = addSection2Preface(doc, request, section71702_proposed);
            addSection2DiffTable(doc, request, section71702_present, section71702_proposed);
        }
        if(section_id.contains("71.70.3")) {
            Section71703 section71703_present = null;
            Section71703 section71703_proposed = null;

            if (request.getRequestType() == 2) {
                section71703_present = section71703Repository.findById(request.getOriginalId());
                section71703_proposed = section71703Repository.findById(request.getTargetId());
            }

            doc = addSection3Preface(doc, request, section71703_proposed);
            addSection3DiffTable(doc, request, section71703_present, section71703_proposed);
        }
        if(section_id.contains("71.70.4")) {
            Section71704 section71704_present = null;
            Section71704 section71704_proposed = null;

            if (request.getRequestType() == 2) {
                section71704_present = section71704Repository.findById(request.getOriginalId());
                section71704_proposed = section71704Repository.findById(request.getTargetId());
            }

            doc = addSection4Preface(doc, request, section71704_proposed);
            addSection4DiffTable(doc, request, section71704_present, section71704_proposed);
        }
        if(section_id.contains("71.70.5")) {
            Section71705 section71705_present = null;
            Section71705 section71705_proposed = null;

            if (request.getRequestType() == 2) {
                section71705_present = section71705Repository.findById(request.getOriginalId());
                section71705_proposed = section71705Repository.findById(request.getTargetId());
            }

            doc = addSection5Preface(doc, request, section71705_proposed);
            addSection5DiffTable(doc, request, section71705_present, section71705_proposed);
        }
        if(section_id.contains("71.70.6")) {
            Section71706 section71706_present = null;
            Section71706 section71706_proposed = null;

            if (request.getRequestType() == 2) {
                section71706_present = section71706Repository.findById(request.getOriginalId());
                section71706_proposed = section71706Repository.findById(request.getTargetId());
            }

            doc = addSection6Preface(doc, request, section71706_proposed);
            addSection6DiffTable(doc, request, section71706_present, section71706_proposed);
        }
        if(section_id.contains("71.70.7")) {
            Section71707 section71707_present = null;
            Section71707 section71707_proposed = null;

            if (request.getRequestType() == 2) {
                section71707_present = section71707Repository.findById(request.getOriginalId());
                section71707_proposed = section71707Repository.findById(request.getTargetId());
            }

            doc = addSection7Preface(doc, request, section71707_proposed);
            addSection7DiffTable(doc, request, section71707_present, section71707_proposed);
        }
        if(section_id.contains("71.70.8")) {
            Section71708 section71708_present = null;
            Section71708 section71708_proposed = null;

            if (request.getRequestType() == 2) {
                section71708_present = section71708Repository.findById(request.getOriginalId());
                section71708_proposed = section71708Repository.findById(request.getTargetId());
            }

            doc = addSection8Preface(doc, request, section71708_proposed);
            addSection8DiffTable(doc, request, section71708_present, section71708_proposed);
        }
        if(section_id.contains("71.70.9")) {
            Section71709 section70719_present = null;
            Section71709 section70719_proposed = null;

            if (request.getRequestType() == 2) {
                section70719_present = section71709Repository.findById(request.getOriginalId());
                section70719_proposed = section71709Repository.findById(request.getTargetId());
            }

            doc = addSection9Preface(doc, request, section70719_proposed);
            addSection9DiffTable(doc, request, section70719_present, section70719_proposed);
        }
        if(section_id.contains("71.70.10")) {
            Section717010 section717010_present = null;
            Section717010 section717010_proposed = null;

            if (request.getRequestType() == 2) {
                section717010_present = section717010Repository.findById(request.getOriginalId());
                section717010_proposed = section717010Repository.findById(request.getTargetId());
            }

            doc = addSection10Preface(doc, request, section717010_proposed);
            addSection10DiffTable(doc, request, section717010_present, section717010_proposed);
        }
    }

    private Document addSection1Preface(Document doc, Request request, Section71701 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection2Preface(Document doc, Request request, Section71702 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection3Preface(Document doc, Request request, Section71703 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection4Preface(Document doc, Request request, Section71704 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection5Preface(Document doc, Request request, Section71705 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection6Preface(Document doc, Request request, Section71706 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection7Preface(Document doc, Request request, Section71707 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection8Preface(Document doc, Request request, Section71708 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection9Preface(Document doc, Request request, Section71709 section7170){

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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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
    private Document addSection10Preface(Document doc, Request request, Section717010 section7170) {
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
        request_type_phrase.add(new Chunk(section7170.getSectionTitle(), times_10));


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

        calendar.add(new Chunk("§" + section7170.getSectionId(), times_10));

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

    private void addSection1DiffTable(Document doc, Request request, Section71701 section71701_present, Section71701 section71701_proposed) {

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
                section71701_present.getSectionId() + "\t" + section71701_present.getSectionTitle(),
                section71701_proposed.getSectionId() + "\t" + section71701_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71701_present.getFirstParagraph(), section71701_proposed.getFirstParagraph(),
                6);

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
    private void addSection2DiffTable(Document doc, Request request, Section71702 section71702_present, Section71702 section71702_proposed) {

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
                section71702_present.getSectionId() + "\t" + section71702_present.getSectionTitle(),
                section71702_proposed.getSectionId() + "\t" + section71702_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71702_present.getFirstParagraph(), section71702_proposed.getFirstParagraph(),
                6);

        Phrase first_core_present = new Phrase();
        Phrase first_core_proposed = new Phrase();

        PdfUtil.processDifferences(first_core_present, first_core_proposed,
                section71702_present.getFirstCore(), section71702_proposed.getFirstCore(),
                1);

        Phrase second_core_present = new Phrase();
        Phrase second_core_proposed = new Phrase();

        PdfUtil.processDifferences(second_core_present, second_core_proposed,
                section71702_present.getSecondCore(), section71702_proposed.getSecondCore(),
                1);

        Phrase third_core_present = new Phrase();
        Phrase third_core_proposed = new Phrase();

        PdfUtil.processDifferences(third_core_present, third_core_proposed,
                section71702_present.getThirdCore(), section71702_proposed.getThirdCore(),
                1);

        Phrase second_paragraph_present = new Phrase();
        Phrase second_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(second_paragraph_present, second_paragraph_proposed,
                section71702_present.getSecondParagraph(), section71702_proposed.getSecondParagraph(),
                6);

        Phrase fourth_core_present = new Phrase();
        Phrase fourth_core_proposed = new Phrase();

        PdfUtil.processDifferences(fourth_core_present, fourth_core_proposed,
                section71702_present.getFourthCore(), section71702_proposed.getFourthCore(),
                1);

        Phrase third_paragraph_present = new Phrase();
        Phrase third_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(third_paragraph_present, third_paragraph_proposed,
                section71702_present.getThirdParagraph(), section71702_proposed.getThirdParagraph(),
                6);

        Phrase fifth_core_present = new Phrase();
        Phrase fifth_core_proposed = new Phrase();

        PdfUtil.processDifferences(fifth_core_present, fifth_core_proposed,
                section71702_present.getFifthCore(), section71702_proposed.getFifthCore(),
                1);

        Phrase fourth_paragraph_present = new Phrase();
        Phrase fourth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(fourth_paragraph_present, fourth_paragraph_proposed,
                section71702_present.getFourthParagraph(), section71702_proposed.getFourthParagraph(),
                6);

        Phrase sixth_core_present = new Phrase();
        Phrase sixth_core_proposed = new Phrase();

        PdfUtil.processDifferences(sixth_core_present, sixth_core_proposed,
                section71702_present.getSixthCore(), section71702_proposed.getSixthCore(),
                1);

        Phrase fifth_paragraph_present = new Phrase();
        Phrase fifth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(fifth_paragraph_present, fifth_paragraph_proposed,
                section71702_present.getFifthParagraph(), section71702_proposed.getFifthParagraph(),
                6);

        Phrase seventh_core_present = new Phrase();
        Phrase seventh_core_proposed = new Phrase();

        PdfUtil.processDifferences(seventh_core_present, seventh_core_proposed,
                section71702_present.getSeventhCore(), section71702_proposed.getSeventhCore(),
                1);

        Phrase sixth_paragraph_present = new Phrase();
        Phrase sixth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(sixth_paragraph_present, sixth_paragraph_proposed,
                section71702_present.getSixthParagraph(), section71702_proposed.getSixthParagraph(),
                6);

        Phrase eight_core_present = new Phrase();
        Phrase eight_core_proposed = new Phrase();

        PdfUtil.processDifferences(eight_core_present, eight_core_proposed,
                section71702_present.getEightCore(), section71702_proposed.getEightCore(),
                1);

        Phrase seventh_paragraph_present = new Phrase();
        Phrase seventh_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(seventh_paragraph_present, seventh_paragraph_proposed,
                section71702_present.getSeventhParagraph(), section71702_proposed.getSeventhParagraph(),
                6);

        Phrase ninth_core_present = new Phrase();
        Phrase ninth_core_proposed = new Phrase();

        PdfUtil.processDifferences(ninth_core_present, ninth_core_proposed,
                section71702_present.getNinthCore(), section71702_proposed.getNinthCore(),
                1);

        Phrase eight_paragraph_present = new Phrase();
        Phrase eight_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(eight_paragraph_present, eight_paragraph_proposed,
                section71702_present.getEightParagraph(), section71702_proposed.getEightParagraph(),
                6);

        Phrase tenth_core_present = new Phrase();
        Phrase tenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(tenth_core_present, tenth_core_proposed,
                section71702_present.getTenthCore(), section71702_proposed.getTenthCore(),
                1);

        Phrase ninth_paragraph_present = new Phrase();
        Phrase ninth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(ninth_paragraph_present, ninth_paragraph_proposed,
                section71702_present.getNinthParagraph(), section71702_proposed.getNinthParagraph(),
                6);

        Phrase eleventh_core_present = new Phrase();
        Phrase eleventh_core_proposed = new Phrase();

        PdfUtil.processDifferences(eleventh_core_present, eleventh_core_proposed,
                section71702_present.getEleventhCore(), section71702_proposed.getEleventhCore(),
                1);

        Phrase tenth_paragraph_present = new Phrase();
        Phrase tenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(tenth_paragraph_present, tenth_paragraph_proposed,
                section71702_present.getTenthParagraph(), section71702_proposed.getTenthParagraph(),
                6);

        Phrase twelfth_core_present = new Phrase();
        Phrase twelfth_core_proposed = new Phrase();

        PdfUtil.processDifferences(twelfth_core_present, twelfth_core_proposed,
                section71702_present.getTwelfthCore(), section71702_proposed.getTwelfthCore(),
                1);

        Phrase eleventh_paragraph_present = new Phrase();
        Phrase eleventh_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(eleventh_paragraph_present, eleventh_paragraph_proposed,
                section71702_present.getEleventhParagraph(), section71702_proposed.getEleventhParagraph(),
                6);

        Phrase thirteenth_core_present = new Phrase();
        Phrase thirteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(thirteenth_core_present, thirteenth_core_proposed,
                section71702_present.getThirteenthCore(), section71702_proposed.getThirteenthCore(),
                1);

        Phrase twelfth_paragraph_present = new Phrase();
        Phrase twelfth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(twelfth_paragraph_present, twelfth_paragraph_proposed,
                section71702_present.getTwelfthParagraph(), section71702_proposed.getTwelfthParagraph(),
                6);

        Phrase fourteenth_core_present = new Phrase();
        Phrase fourteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(fourteenth_core_present, fourteenth_core_proposed,
                section71702_present.getFourteenthCore(), section71702_proposed.getFourteenthCore(),
                1);

        Phrase fifteenth_core_present = new Phrase();
        Phrase fifteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(fifteenth_core_present, fifteenth_core_proposed,
                section71702_present.getFifteenthCore(), section71702_proposed.getFifteenthCore(),
                1);

        Phrase thirteenth_paragraph_present = new Phrase();
        Phrase thirteenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(thirteenth_paragraph_present, thirteenth_paragraph_proposed,
                section71702_present.getThirteenthParagraph(), section71702_proposed.getThirteenthParagraph(),
                6);

        Phrase sixteenth_core_present = new Phrase();
        Phrase sixteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(sixteenth_core_present, sixteenth_core_proposed,
                section71702_present.getSixteenthCore(), section71702_proposed.getSixteenthCore(),
                1);

        Phrase fourteenth_paragraph_present = new Phrase();
        Phrase fourteenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(fourteenth_paragraph_present, fourteenth_paragraph_proposed,
                section71702_present.getFourteenthParagraph(), section71702_proposed.getFourteenthParagraph(),
                6);

        Phrase seventeenth_core_present = new Phrase();
        Phrase seventeenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(seventeenth_core_present, seventeenth_core_proposed,
                section71702_present.getSeventeenthCore(), section71702_proposed.getSeventeenthCore(),
                1);

        Phrase fifteenth_paragraph_present = new Phrase();
        Phrase fifteenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(fifteenth_paragraph_present, fifteenth_paragraph_proposed,
                section71702_present.getFifteenthParagraph(), section71702_proposed.getFifteenthParagraph(),
                6);

        Phrase eighteenth_core_present = new Phrase();
        Phrase eighteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(eighteenth_core_present, eighteenth_core_proposed,
                section71702_present.getEighteenthCore(), section71702_proposed.getEighteenthCore(),
                1);

        Phrase sixteenth_paragraph_present = new Phrase();
        Phrase sixteenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(sixteenth_paragraph_present, sixteenth_paragraph_proposed,
                section71702_present.getSixteenthParagraph(), section71702_proposed.getSixteenthParagraph(),
                6);

        Phrase nineteenth_core_present = new Phrase();
        Phrase nineteenth_core_proposed = new Phrase();

        PdfUtil.processDifferences(nineteenth_core_present, nineteenth_core_proposed,
                section71702_present.getNineteenthCore(), section71702_proposed.getNineteenthCore(),
                1);

        Phrase seventeenth_paragraph_present = new Phrase();
        Phrase seventeenth_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(seventeenth_paragraph_present, seventeenth_paragraph_proposed,
                section71702_present.getSeventeenthParagraph(), section71702_proposed.getSeventeenthParagraph(),
                6);

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

        present.add(first_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(first_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(second_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(second_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(third_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(third_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(second_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(second_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(fourth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(fourth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(third_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(third_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(fifth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(fifth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(fourth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(fourth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(sixth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(sixth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(fifth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(fifth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(seventh_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(seventh_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(sixth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(sixth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(eight_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(eight_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(seventh_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(seventh_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(ninth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(ninth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(eight_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(eight_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(tenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(tenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(ninth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(ninth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(eleventh_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(eleventh_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(tenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(tenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(twelfth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(twelfth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(eleventh_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(eleventh_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(thirteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(thirteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(twelfth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(twelfth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(fourteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(fourteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(fifteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(fifteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(thirteenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(thirteenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(sixteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(sixteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(fourteenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(fourteenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(seventeenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(seventeenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(fifteenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(fifteenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(eighteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(eighteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(sixteenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(sixteenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(nineteenth_core_present);
        present.add(Chunk.NEWLINE);
        proposed.add(nineteenth_core_proposed);
        proposed.add(Chunk.NEWLINE);

        present.add(seventeenth_paragraph_present);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        present.add(Chunk.NEWLINE);
        proposed.add(seventeenth_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);


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
    private void addSection3DiffTable(Document doc, Request request, Section71703 section71703_present, Section71703 section71703_proposed) {

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
                section71703_present.getSectionId() + "\t" + section71703_present.getSectionTitle(),
                section71703_proposed.getSectionId() + "\t" + section71703_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71703_present.getFirstParagraph(), section71703_proposed.getFirstParagraph(),
                6);

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
    private void addSection4DiffTable(Document doc, Request request, Section71704 section71704_present, Section71704 section71704_proposed) {

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
                section71704_present.getSectionId() + "\t" + section71704_present.getSectionTitle(),
                section71704_proposed.getSectionId() + "\t" + section71704_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71704_present.getFirstParagraph(), section71704_proposed.getFirstParagraph(),
                6);

        Phrase first_core_present = new Phrase();
        Phrase first_core_proposed = new Phrase();

        PdfUtil.processDifferences(first_core_present, first_core_proposed,
                section71704_present.getFirstCore(), section71704_proposed.getFirstCore(),
                1);

        Phrase second_paragraph_present = new Phrase();
        Phrase second_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(second_paragraph_present, second_paragraph_proposed,
                section71704_present.getSecondParagraph(), section71704_proposed.getSecondParagraph(),
                6);

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
        present.add(Chunk.NEWLINE);
        proposed.add(first_core_proposed);
        proposed.add(Chunk.NEWLINE);
        proposed.add(Chunk.NEWLINE);

        present.add(second_paragraph_present);
        present.add(Chunk.NEWLINE);
        proposed.add(second_paragraph_proposed);
        proposed.add(Chunk.NEWLINE);

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
    private void addSection5DiffTable(Document doc, Request request, Section71705 section71705_present, Section71705 section71705_proposed) {

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
                section71705_present.getSectionId() + "\t" + section71705_present.getSectionTitle(),
                section71705_proposed.getSectionId() + "\t" + section71705_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71705_present.getFirstParagraph(), section71705_proposed.getFirstParagraph(),
                6);

        Phrase first_core_present = new Phrase();
        Phrase first_core_proposed = new Phrase();

        PdfUtil.processDifferences(first_core_present, first_core_proposed,
                section71705_present.getFirstCore(), section71705_proposed.getFirstCore(),
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

        proposed.add(first_core_proposed);
        proposed.add(Chunk.NEWLINE);

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
    private void addSection6DiffTable(Document doc, Request request, Section71706 section71706_present, Section71706 section71706_proposed) {

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
                section71706_present.getSectionId() + "\t" + section71706_present.getSectionTitle(),
                section71706_proposed.getSectionId() + "\t" + section71706_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71706_present.getFirstParagraph(), section71706_proposed.getFirstParagraph(),
                6);

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
    private void addSection7DiffTable(Document doc, Request request, Section71707 section71707_present, Section71707 section71707_proposed) {

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
                section71707_present.getSectionId() + "\t" + section71707_present.getSectionTitle(),
                section71707_proposed.getSectionId() + "\t" + section71707_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71707_present.getFirstParagraph(), section71707_proposed.getFirstParagraph(),
                6);

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
    private void addSection8DiffTable(Document doc, Request request, Section71708 section71708_present, Section71708 section71708_proposed) {

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
                section71708_present.getSectionId() + "\t" + section71708_present.getSectionTitle(),
                section71708_proposed.getSectionId() + "\t" + section71708_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section71708_present.getFirstParagraph(), section71708_proposed.getFirstParagraph(),
                6);

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
    private void addSection9DiffTable(Document doc, Request request, Section71709 section70719_present, Section71709 section70719_proposed) {

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
    private void addSection10DiffTable(Document doc, Request request, Section717010 section717010_present, Section717010 section717010_proposed) {

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
                section717010_present.getSectionId() + "\t" + section717010_present.getSectionTitle(),
                section717010_proposed.getSectionId() + "\t" + section717010_proposed.getSectionTitle(),
                1);


        Phrase first_paragraph_present = new Phrase();
        Phrase first_paragraph_proposed = new Phrase();

        PdfUtil.processDifferences(first_paragraph_present, first_paragraph_proposed,
                section717010_present.getFirstParagraph(), section717010_proposed.getFirstParagraph(),
                6);

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
