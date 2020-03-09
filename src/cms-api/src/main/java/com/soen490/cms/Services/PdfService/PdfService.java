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
import com.itextpdf.text.pdf.*;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.SupportingDocumentRepository;
import com.soen490.cms.Services.PdfService.PdfSections.PdfSection;
import com.soen490.cms.Services.PdfService.PdfSections.PdfSection7071;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static com.soen490.cms.Services.PdfService.PdfUtil.*;

@Service
@Log4j2
public class PdfService {

    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SupportingDocumentRepository supportingDocumentRepository;
    @Autowired
    private PdfCourse pdfCourse;
    @Autowired
    private PdfProgram pdfProgram;
    @Autowired
    private PdfSection pdfSection;
    @Autowired
    private PdfSection7071 pdfSection7071;


    public byte[] getPDF(int package_id) { return requestPackageRepository.findPdfById(package_id); }


    /**
     * Generate the pdf file for a given package and saves it to the package database table.
     *
     * @param user_id Used to store audit information
     * @param package_id Used to retrieve request_package object.
     * @return true if a pdf has been generated and saved as pdf_file inside request_package.
     */
    public boolean generatePDF(int package_id, int user_id) throws IOException, DocumentException {

        ByteArrayOutputStream course_outline_stream;
        ByteArrayOutputStream support_stream = null;
        ArrayList<ByteArrayOutputStream> request_streams = new ArrayList<>();
        ByteArrayOutputStream program_stream = null;
        ByteArrayOutputStream final_stream;

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        if(requestPackage == null){ return false;}

        requestPackage.setUserId(user_id);

        requestPackageRepository.save(requestPackage);

        List<SupportingDocument> dossier_files = supportingDocumentRepository.findByTarget("dossier", requestPackage.getId());

        if(dossier_files != null && !dossier_files.isEmpty()) {

            try {
                log.info("Dossier supporting docs found. Merging them together...");
                support_stream = mergeSupportingDocs(dossier_files);

            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }

        // Program pages
        ArrayList<ByteArrayOutputStream> program_streams = pdfProgram.addProgramPage(requestPackage);

        if(program_streams != null && !program_streams.isEmpty())
            program_stream = mergeStreams(program_streams);

        try {
            // for each page
            for(Request request : requestPackage.getRequests()){

                log.info("Generating pdf page for " + request.getTitle());

                if(request.getTargetType() == 2) {

                    // course requests
                    Course original_course = courseRepository.findById(request.getOriginalId());
                    Course changed_course = courseRepository.findById(request.getTargetId());

                    if (isCoreRequest(original_course, changed_course)) continue;
                }

                if(request.getTargetType() == 1) {

                    String section_id = request.getTitle();

                    if(section_id.contains("71.70"))
                        if (pdfSection7071.isCoreRequest(request, section_id)) continue;
                }

                Document doc = new Document();
                // course page specifications
                doc.setPageSize(PageSize.A4.rotate());
                doc.setMargins(25, 25, 10, 25);

                ByteArrayOutputStream request_stream = new ByteArrayOutputStream();
                PdfWriter.getInstance(doc, request_stream);

                doc.open();

                if(request.getTargetType() == 2) {

                    // course requests
                    Course original_course = courseRepository.findById(request.getOriginalId());
                    Course changed_course = courseRepository.findById(request.getTargetId());

                    pdfCourse.addCoursePage(doc, request, original_course, changed_course);

                    // append course supporting documents
                    List<SupportingDocument> course_files = null;

                    if(changed_course != null){
                        course_files = supportingDocumentRepository.findByTarget("course", changed_course.getId());
                    }
                    if(course_files != null && !course_files.isEmpty()){

                        log.info("Appending course outline for " + changed_course.getName() + changed_course.getNumber());

                        course_outline_stream = mergeSupportingDocs(course_files);

                        doc.close();

                        ArrayList<ByteArrayOutputStream> streams = new ArrayList<>();
                        streams.add(request_stream);
                        streams.add(course_outline_stream);
                        request_stream = mergeStreams(streams);

                        request_streams.add(request_stream);

                        continue;

                    }

                }
                else if(request.getTargetType() == 1){

                    // calendar request
                    pdfSection.addSectionPage(doc, request);
                }

                doc.close();
                request_streams.add(request_stream);
            }

        } catch (DocumentException | FileNotFoundException e){
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }


        final_stream = mergeStreams(request_streams);


        if(support_stream != null || program_stream != null) {

            ArrayList<ByteArrayOutputStream> streams = new ArrayList<>();

            if (support_stream != null)
                streams.add(support_stream);

            if (program_stream != null)
                streams.add(program_stream);

            streams.add(final_stream);

            final_stream = mergeStreams(streams);
        }

        final_stream = stampPageXofY(final_stream);

        requestPackage.setPdfFile(final_stream.toByteArray());

        // this might not be required in active transaction
        requestPackageRepository.save(requestPackage);

        return true;
    }


    // Verifies if there is legitimate reasons to add a course diff page
    private boolean isCoreRequest(Course original_course, Course changed_course) {

        if(original_course == null || changed_course == null)
            return false;

        if(original_course.getRequisites().size() != changed_course.getRequisites().size())
            return false;

        for(int i = 0; i < original_course.getRequisites().size(); i++){

            Requisite r1 = original_course.getRequisites().get(i);
            Requisite r2 = changed_course.getRequisites().get(i);

            if(!r1.getName().equals(r2.getName()) || r1.getNumber() != r2.getNumber() || !r1.getType().equals(r2.getType()))
                return false;
        }

        return
                original_course.getName().equals(changed_course.getName()) &&
                original_course.getNumber() == changed_course.getNumber() &&
                original_course.getTitle().equals(changed_course.getTitle()) &&
                original_course.getDescription().equals(changed_course.getDescription()) &&
                original_course.getCredits() == changed_course.getCredits();
    }


    /**
     * Combines the request package's supporting documents into one pdf stream.
     * @param files to be merged.
     * @return The aggregated stream of the combined supporting documents.
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeSupportingDocs(List<SupportingDocument> files) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        // append supporting docs
        PdfCopy copy = new PdfCopy(doc, byte_stream);
        copy.setMergeFields();

        doc.open();

        for(SupportingDocument supportingDocument : files){

            copy.addDocument(new PdfReader(supportingDocument.getFile()));

        }

        doc.close();

        return byte_stream;
    }


    /**
     * Aggregate all request docs into one pdf file.
     * @param streams List of all request documents generated.
     * @return The combined pdf file of all requests.
     * @throws DocumentException
     * @throws IOException
     */
    private ByteArrayOutputStream mergeStreams(ArrayList<ByteArrayOutputStream> streams) throws DocumentException, IOException {

        Document doc = new Document();
        ByteArrayOutputStream byte_stream = new ByteArrayOutputStream();

        // append supporting docs
        PdfCopy copy = new PdfCopy(doc, byte_stream);
        copy.setMergeFields();

        doc.open();

        for(ByteArrayOutputStream stream : streams){

            copy.addDocument(new PdfReader(stream.toByteArray()));

        }

        doc.close();

        return byte_stream;
    }


    /**
     * Numbers the pages for the document.
     * @param final_stream The final document without page numbering.
     * @return The final document stream with numbered pages.
     * @throws IOException
     * @throws DocumentException
     */
    private ByteArrayOutputStream stampPageXofY(ByteArrayOutputStream final_stream) throws IOException, DocumentException {

        log.info("Numbering final document pages...");

        PdfReader reader = new PdfReader(final_stream.toByteArray());

        int n = reader.getNumberOfPages();

        PdfStamper stamper = new PdfStamper(reader, final_stream);

        PdfContentByte pagecontent;

        for (int i = 0; i < n; ) {

            pagecontent = stamper.getOverContent(++i);

            if(reader.getPageRotation(i) == 0)
                ColumnText.showTextAligned(
                        pagecontent, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n), arial_10),
                        reader.getPageSize(i).getRight() - 7, reader.getPageSize(i).getBottom() + 8, 0);
            else
                ColumnText.showTextAligned(
                        pagecontent, Element.ALIGN_RIGHT,
                        new Phrase(String.format("page %s of %s", i, n), arial_10),
                        reader.getPageSize(i).getRight() + 241, reader.getPageSize(i).getBottom() + 8, 0);
        }
        stamper.close();
        reader.close();

        return final_stream;
    }

}
