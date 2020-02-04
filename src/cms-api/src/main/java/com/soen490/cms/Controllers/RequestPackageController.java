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

package com.soen490.cms.Controllers;

import com.itextpdf.text.DocumentException;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Models.SupportingDocument;
import com.soen490.cms.Services.PdfService.PdfService;
import com.soen490.cms.Services.RequestPackageService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Log4j2
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
public class RequestPackageController {

    @Autowired
    PdfService pdfService;
    @Autowired
    RequestPackageService requestPackageService;
    @Autowired
    ImpactAssessmentController impactAssessmentController;

    /**
     * Generates a pdf file out of the entire data set for the given package.
     * @param package_id
     * @return A boolean detailing if the pdf generation was successful or a failure.
     */
    @GetMapping(value="/generate_pdf")
    public boolean generatePdf(@RequestParam int package_id, @RequestParam int user_id) throws IOException, DocumentException {

        return pdfService.generatePDF(package_id, user_id);
    }


    /**
     * Converts a stored byte array into a pdf file and displays it on the browser.
     * @param package_id
     * @return The pdf file to browser.
     */
    @GetMapping(value="/get_pdf")
    public ResponseEntity<byte[]> getPdf(@RequestParam int package_id){

        byte[] pdf_bytes = pdfService.getPDF(package_id);

        if(pdf_bytes == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "package_" +  package_id + ".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);
    }


    @GetMapping("/get_rev_pdf")
    public ResponseEntity<byte[]> getRevPdf(@RequestParam int rev_id){

        byte[] pdf_bytes = requestPackageService.getRevPDF(rev_id);

        if(pdf_bytes == null) return null;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "package_revision_no" +  rev_id + ".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);
    }


    /**
     * Converts a stored byte array into a pdf file and displays it on the browser.
     * @param package_id
     * @return The pdf file to browser.
     */
    @GetMapping(value="/get_pdf_packagePage")
    public ResponseEntity<byte[]> getPdfPackagePage(@RequestParam int package_id, @RequestParam int user_id){

        byte[] pdf_bytes = pdfService.getPDF(package_id);

        if(pdf_bytes == null) {

            boolean success = false;

            try {
                success = generatePdf(package_id, user_id);
            } catch (IOException | DocumentException e) {
                e.printStackTrace();
            }


            if(success)
                pdf_bytes = pdfService.getPDF(package_id);
            else
                return null;
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "package_" +  package_id + ".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);
    }


    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70711 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70711 was successfully added to database.
     */
    @PostMapping(value="/save_section70711")
    public int saveSubSection70711 (@RequestParam String subSection70711, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70711);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70711(subSection70711, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70712 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70712 was successfully added to database.
     */
    @PostMapping(value="/save_section70712")
    public int saveSubSection70712 (@RequestParam String subSection70712, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70712);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70712(subSection70712, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70713 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70713 was successfully added to database.
     */
    @PostMapping(value="/save_section70713")
    public int saveSubSection70713 (@RequestParam String subSection70713, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70713);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70713(subSection70713, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70714 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70714 was successfully added to database.
     */
    @PostMapping(value="/save_section70714")
    public int saveSubSection70714 (@RequestParam String subSection70714, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70714);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70714(subSection70714, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70715 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70715 was successfully added to database.
     */
    @PostMapping(value="/save_section70715")
    public int saveSubSection70715 (@RequestParam String subSection70715, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70715);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70715(subSection70715, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70716 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70716 was successfully added to database.
     */
    @PostMapping(value="/save_section70716")
    public int saveSubSection70716 (@RequestParam String subSection70716, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70716);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70716(subSection70716, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70717 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70717 was successfully added to database.
     */
    @PostMapping(value="/save_section70717")
    public int saveSubSection70717 (@RequestParam String subSection70717, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70717);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70717(subSection70717, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70718 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70718 was successfully added to database.
     */
    @PostMapping(value="/save_section70718")
    public int saveSubSection70718 (@RequestParam String subSection70718, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70718);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70718(subSection70718, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection70719 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70719 was successfully added to database.
     */
    @PostMapping(value="/save_section70719")
    public int saveSubSection70719 (@RequestParam String subSection70719, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection70719);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection70719(subSection70719, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection707110 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section707110 was successfully added to database.
     */
    @PostMapping(value="/save_section707110")
    public int saveSubSection707110 (@RequestParam String subSection707110, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection707110);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection707110(subSection707110, sectionExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param course stringified JSON received from front-end.
     * @param courseExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if course was successfully added to database.
     */
    @PostMapping(value="/save_request")
    public int saveCreateAndEditRequest(@RequestParam String course, @RequestParam String courseExtras,
                                        @RequestParam(required = false) MultipartFile[] files,
                                        @RequestParam(required = false) String descriptions) {

        try {
            JSONObject courseExtrasJson = new JSONObject(courseExtras);
            int user_id = Integer.parseInt(String.valueOf(courseExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(courseExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveCourseRequest(course, courseExtras, files, descriptions);

            if(request_id != 0)
                requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }


    @PostMapping(value="/save_removal_request")
    public int saveRemovalRequest(@RequestParam String course, @RequestParam String courseExtras,
                                  @RequestParam(required = false) MultipartFile[] files,
                                  @RequestParam(required = false) String descriptions) {

        try {
            JSONObject courseExtrasJson = new JSONObject(courseExtras);
            int user_id = Integer.parseInt(String.valueOf(courseExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(courseExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveRemovalRequest(course, courseExtras, files, descriptions);

            if(request_id != 0) {
                requestPackageService.generatePdf(package_id, user_id);
            }
            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // delete course request
    @GetMapping(value="/delete_request")
    public boolean deleteCourseRequest(@RequestParam int requestId) {
        log.info("remove request " + requestId);
        if(requestId != 0)
            return requestPackageService.deleteCourseRequest(requestId);

        return true;
    }

    // returns list of packages from a given department
    @GetMapping(value = "/get_packages")
    public List<RequestPackage> getRequestPackages(@RequestParam int department_id){

        return requestPackageService.getRequestPackagesByDepartment(department_id);
    }


    // returns a package from a given id
    @GetMapping(value = "/get_package")
    public RequestPackage getRequestPackage(@RequestParam int package_id, @RequestParam int department_id){

        return requestPackageService.getRequestPackage(package_id, department_id);
    }


    // Add dossier to database
    @PostMapping(value="/add_dossier")
    public RequestPackage addDossier(@RequestParam int user_id) throws JSONException{

        return requestPackageService.addDossier(user_id);
    }


    // Remove dossier from database
    @PostMapping(value="/delete_dossier")
    public boolean deleteDossier(@RequestParam int dossier_id) throws JSONException{

        return requestPackageService.deleteDossier(dossier_id);
    }


    /**
     * Add a new supporting document to a request
     * path format: /addSupportingDocument?documentId={id}
     * @param files
     * @param package_id
     * @param user_id
     * @return
     */
    @PostMapping(value = "/upload_files")
    public String uploadFiles(@RequestParam MultipartFile[] files, @RequestParam String descriptions,
                              @RequestParam int package_id, @RequestParam int user_id) throws IOException, JSONException {

        if(files.length == 0)
            return "No files uploaded.";

        for(MultipartFile file : files)
            log.info("Uploaded file: " + file.getOriginalFilename());

        requestPackageService.saveSupportingDocument(files, descriptions,"dossier", package_id, user_id);

        try {
            generatePdf(package_id, user_id);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return "Files successfully uploaded.";
    }


    @GetMapping("/dossier_revisions")
    public List getDossierRevisions(@RequestParam int id){
        return requestPackageService.getDossierRevisions(id);
    }


    /**
     * Converts a stored byte array into a pdf file and displays it on the browser.
     * @param file_id id of the supporting document
     * @return The pdf file to browser.
     */
    @GetMapping(value="/get_supporting_document_pdf")
    public ResponseEntity<byte[]> getSupportingDocumentPdf(@RequestParam int file_id){

        log.info("Retrieve supporting document pdf for " + file_id);

        SupportingDocument supportingDocument = requestPackageService.getSupportingDocument(file_id);
        byte[] pdf_bytes = supportingDocument.getFile();

        if(pdf_bytes == null) {
            return null;
        }

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = supportingDocument.getFileName();

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);
    }


    @GetMapping("/get_supporting_documents")
    public List<SupportingDocument> getSupportingDocuments(@RequestParam int target_id, @RequestParam String target_type){

        log.info("Retrieve supporting docs for " + target_type + " " + target_id);
        List<SupportingDocument> supportingDocuments = requestPackageService.getSupportingDocuments(target_id, target_type);

        for(SupportingDocument s : supportingDocuments){
            s.setFile(null);
        }

        return supportingDocuments;
    }


    @GetMapping("/remove_supporting_document")
    public boolean removeSupportingDocument(@RequestParam int file_id){
        log.info("Removing supporting doc " + file_id);
        return requestPackageService.removeSupportingDocument(file_id);
    }

}
