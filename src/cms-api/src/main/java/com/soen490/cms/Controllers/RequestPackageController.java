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
import com.soen490.cms.Services.PdfService;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
    public byte[] getPdf(@RequestParam int package_id){

        byte[] pdf_bytes = pdfService.getPDF(package_id);

        if(pdf_bytes == null) return null;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "package_" +  package_id + ".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return pdf_bytes;
    }


    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param requestForm Combined stringified JSON received from front-end.
     * @param bindingResult Validates requestForm.
     * @return True if course was successfully added to database.
     * @throws JSONException
     */
    @PostMapping(value="/save_request", consumes = "application/json")
    public int saveCreateAndEditRequest(@Valid @RequestBody String requestForm, BindingResult bindingResult) throws JSONException {

        return requestPackageService.saveCourseRequest(requestForm);
    }

    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param requestForm Combined stringified JSON received from front-end.
     * @param bindingResult Validates requestForm.
     * @return True if course was successfully added to database.
     * @throws JSONException
     */
    @PostMapping(value="/save_removal_request", consumes = "application/json")
    public int saveRemovalRequest(@Valid @RequestBody String requestForm, BindingResult bindingResult) throws JSONException {

        return requestPackageService.saveRemovalRequest(requestForm);
    }

    // delete course request
    @GetMapping(value="/delete_request")
    public boolean deleteCourseRequest(@RequestParam int requestId) {

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


    // Save package to database
    @PostMapping(value="/save_package", consumes = "application/json")
    public boolean saveRequestPackage(@Valid @RequestBody String requestPackageForm, BindingResult bindingResult) throws JSONException{

        return requestPackageService.saveRequestPackage(requestPackageForm);
    }

    /**
     * Add a new supporting document to a request
     * path format: /addSupportingDocument?documentId={id}
     * @param document
     * @param packageId
     * @return
     */
    @PostMapping(value = "/upload")
    public SupportingDocument add(@RequestParam File document, @RequestParam int packageId) throws IOException {

        return requestPackageService.addSupportingDocument(document, packageId);
    }


    @PostMapping(value = "/get_impact")
    public Map<String, Object> sendRequestId(@RequestBody String requestForm) throws JSONException {

        int request_id = requestPackageService.saveCourseRequest(requestForm);

        return impactAssessmentController.getImpactAssessment(request_id);
    }

    @PostMapping("/addSupportingDocument")
    public boolean uploadData(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) throws Exception {

        if (file == null) {
            throw new RuntimeException("You must select the a file for uploading");
        }

        byte[] doc = file.getBytes();

        return requestPackageService.saveRequestFile(doc, id);
    }


    @GetMapping("/dossier_revisions")
    public List getDossierRevisions(@RequestParam int id){
        return requestPackageService.getDossierRevisions(id);
    }

}
