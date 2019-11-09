package com.soen490.cms.Controllers;

import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Services.PdfService;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        ResponseEntity<byte[]> response = new ResponseEntity<>(pdf_bytes, headers, HttpStatus.OK);

        return response;
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

        JSONObject json = new JSONObject(requestForm);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        System.out.println(requestForm);

        return requestPackageService.saveCourseRequest(course, courseExtras);

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

        JSONObject json = new JSONObject(requestPackageForm);

        System.out.println(requestPackageForm);

        return requestPackageService.saveRequestPackage(json);
    }

}
