package com.soen490.cms.Controllers;

import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Models.SupportingDocument;
import com.soen490.cms.Services.RequestPackageService;
import com.soen490.cms.Services.SupportingDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SupportingDocumentsController {

    @Autowired
    SupportingDocumentService supportingDocsService;

    @Autowired
    RequestPackageService requestPackageService;

    /**
     *
     * @return
     */
    @GetMapping(value = "/supportingDocuments")
    public Collection<SupportingDocument> getSupportingDocuments() {
        return supportingDocsService.findAll();
    }

    /**
     *
     *
     * @param supportingDocumentId
     * @return
     */
    @GetMapping(value = "/supportingDocument")
    public SupportingDocument getSupportingDocument(@RequestParam("documentId") Long supportingDocumentId) {
        return supportingDocsService.find(supportingDocumentId);
    }

    /**
     * Add a new supporting document to a request
     * path format: /supportingDocuments/add?documentId={id}
     *
     * @param document
     * @param packageId
     * @return
     */
    @PostMapping(value = "/addSupportingDocument")
    public void add(@RequestParam("document") byte[] document, @RequestParam("package_id") Long packageId){
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SupportingDocument supportingDocument = new SupportingDocument();
        supportingDocument.setDocument(document);
        supportingDocument.setRequestPackage(requestPackageService.find(packageId));
        supportingDocument.setTimestamp(timestamp);

        supportingDocsService.addSupportingDocument(supportingDocument);
    }

    @GetMapping(value = "/addSupportDoc")
    public boolean supportTest(@RequestParam long package_id){

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SupportingDocument supportingDocument = new SupportingDocument();
        RequestPackage requestPackage = requestPackageService.find(package_id);
        supportingDocument.setRequestPackage(requestPackage);
        supportingDocument.setDocument(requestPackage.getPdfFile());
        supportingDocument.setTimestamp(timestamp);

        supportingDocsService.addSupportingDocument(supportingDocument);
        return true;
    }
}
