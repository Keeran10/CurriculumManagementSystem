package com.soen490.cms.Controllers;

import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Models.SupportingDocument;
import com.soen490.cms.Services.PdfService;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RequestPackageController {

    @Autowired
    PdfService pdfService;
    @Autowired
    RequestPackageService requestPackageService;

    /**
     * Generates a pdf file out of the entire data set for the given package.
     * @param package_id
     * @return A boolean detailing if the pdf generation was successful or a failure.
     */
    @GetMapping(value="/generate_pdf")
    public boolean generatePdf(@RequestParam int package_id) { return pdfService.generatePDF(package_id); }


    /**
     * Converts a stored byte array into a pdf file and displays it on the browser.
     * @param package_id
     * @return The pdf file to browser.
     */
    @GetMapping(value="/get_pdf")
    public ResponseEntity<byte[]> getPdf(@RequestParam int package_id){

        byte[] pdf_bytes = pdfService.getPDF(package_id);

        if(pdf_bytes == null) return null;

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "package_" +  package_id + ".pdf";

        headers.add("content-disposition", "inline;filename=" + filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);
    }


    /**
     * Receives data from client and populates the database for course and its dependencies.
     * @param requestForm Combined stringified JSON received from front-end.
     * @param bindingResult Validates requestForm.
     * @return True if course was successfully added to database.
     * @throws JSONException
     */
    @PostMapping(value="/save_request", consumes = "application/json")
    public boolean saveRequest(@Valid @RequestBody String requestForm, BindingResult bindingResult) throws JSONException {

        return requestPackageService.saveCourseRequest(requestForm);
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
    @PostMapping(value = "/addSupportingDocument")
    public SupportingDocument add(@RequestParam File document, @RequestParam int packageId) throws IOException {

        return requestPackageService.addSupportingDocument(document, packageId);
    }

}
