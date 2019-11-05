package com.soen490.cms.Controllers;

import com.itextpdf.text.Document;
import com.soen490.cms.Services.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RequestPackageController {

    @Autowired
    PdfService pdfService;
//ResponseEntity<byte[]>
    @GetMapping(value="/pdf")
    public int getPDF(@RequestParam int package_id) {

        // generate the file
        boolean success = pdfService.generatePDF(package_id);

        if(success) return 0;

        byte[] pdf_bytes;

        if(success)
            pdf_bytes = pdfService.getPDF(package_id);
        /*
        // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
        byte[] contents = null;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);

        String filename = "package_" + package_id + ".pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;
        */
        return 0;
    }



}
