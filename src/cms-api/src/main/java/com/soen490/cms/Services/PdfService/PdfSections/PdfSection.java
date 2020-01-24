package com.soen490.cms.Services.PdfService.PdfSections;


import com.itextpdf.text.Document;
import com.soen490.cms.Models.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PdfSection {

    @Autowired
    private PdfSection70719 pdfSection70719;

    public void addSectionPage(Document doc, Request request){

        // replace with valid section identifier
        if(true){

            pdfSection70719.addSectionPage(doc, request);
        }
    }
}
