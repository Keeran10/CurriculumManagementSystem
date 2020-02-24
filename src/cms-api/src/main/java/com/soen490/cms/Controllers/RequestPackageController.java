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
import org.json.JSONArray;
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
     * @param subSection71701 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70711 was successfully added to database.
     */
    @PostMapping(value= "/save_section71701")
    public int saveSubSection70711 (@RequestParam String subSection71701, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71701);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71701(subSection71701, sectionExtras, files, descriptions);

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
     * @param subSection71702 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70712 was successfully added to database.
     */
    @PostMapping(value= "/save_section71702")
    public int saveSubSection71702(@RequestParam String subSection71702, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71702);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71702(subSection71702, sectionExtras, files, descriptions);

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
     * @param subSection71703 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70713 was successfully added to database.
     */
    @PostMapping(value= "/save_section71703")
    public int saveSubSection71703(@RequestParam String subSection71703, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71703);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71703(subSection71703, sectionExtras, files, descriptions);

            JSONArray core_removals = sectionExtrasJson.getJSONArray("core_removals");
            JSONArray core_additions = sectionExtrasJson.getJSONArray("core_additions");

            String add_to_core = (String) sectionExtrasJson.get("add_to_core");
            String remove_from_core = (String) sectionExtrasJson.get("remove_from_core");

            requestPackageService.processCoreRequests(core_additions, core_removals,
                    add_to_core, remove_from_core,
                    user_id, package_id);

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
     * @param subSection71704 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70714 was successfully added to database.
     */
    @PostMapping(value= "/save_section71704")
    public int saveSubSection71704(@RequestParam String subSection71704, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71704);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71704(subSection71704, sectionExtras, files, descriptions);

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
     * @param subSection71705 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70715 was successfully added to database.
     */
    @PostMapping(value= "/save_section71705")
    public int saveSubSection71705(@RequestParam String subSection71705, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71705);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71705(subSection71705, sectionExtras, files, descriptions);

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
     * @param subSection71706 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70716 was successfully added to database.
     */
    @PostMapping(value= "/save_section71706")
    public int saveSubSection71706(@RequestParam String subSection71706, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71706);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71706(subSection71706, sectionExtras, files, descriptions);

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
     * @param subSection71707 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70717 was successfully added to database.
     */
    @PostMapping(value= "/save_section71707")
    public int saveSubSection70717 (@RequestParam String subSection71707, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71707);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71707(subSection71707, sectionExtras, files, descriptions);

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
     * @param subSection71708 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70718 was successfully added to database.
     */
    @PostMapping(value= "/save_section71708")
    public int saveSubSection70718 (@RequestParam String subSection71708, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71708);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection71708(subSection71708, sectionExtras, files, descriptions);

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
     * @param subSection71709 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section70719 was successfully added to database.
     */
    @PostMapping(value= "/save_section71709")
    public int saveSubSection71709(@RequestParam String subSection71709, @RequestParam String sectionExtras,
                                   @RequestParam(required = false) MultipartFile[] files,
                                   @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection71709);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id;

            JSONObject sectionJson = new JSONObject(subSection71709);
            String section_id = (String) sectionJson.get("sectionId");

            if(section_id.contains("71.70.9")){
                request_id = requestPackageService.saveSection71709(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.8")){
                request_id = requestPackageService.saveSection71708(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.7")){
                request_id = requestPackageService.saveSection71707(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.6")){
                request_id = requestPackageService.saveSection71706(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.5")){
                request_id = requestPackageService.saveSection71705(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.4")){
                request_id = requestPackageService.saveSection71704(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.3")){
                request_id = requestPackageService.saveSection71703(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.2")){
                request_id = requestPackageService.saveSection71702(subSection71709, sectionExtras, files, descriptions);
            }
            else if(section_id.contains("71.70.1")){
                request_id = requestPackageService.saveSection71701(subSection71709, sectionExtras, files, descriptions);
            }
            else{
                System.out.println("No sections found ...");
                return 0;
            }

            requestPackageService.generatePdf(package_id, user_id);

            return request_id;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param subSection717010 stringified JSON received from front-end.
     * @param sectionExtras stringified JSON received from front-end.
     * @param files supporting docs
     * @return True if section707110 was successfully added to database.
     */
    @PostMapping(value= "/save_section717010")
    public int saveSubSection717010(@RequestParam String subSection717010, @RequestParam String sectionExtras,
                                    @RequestParam(required = false) MultipartFile[] files,
                                    @RequestParam(required = false) String descriptions) {
        log.info("Saving Section: " + subSection717010);
        try {
            JSONObject sectionExtrasJson = new JSONObject(sectionExtras);
            int user_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("userId")));
            int package_id = Integer.parseInt(String.valueOf(sectionExtrasJson.get("packageId")));
            int request_id = requestPackageService.saveSection717010(subSection717010, sectionExtras, files, descriptions);

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
