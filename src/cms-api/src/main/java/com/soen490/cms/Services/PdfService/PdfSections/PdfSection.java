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


import com.itextpdf.text.Document;
import com.soen490.cms.Models.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PdfSection {

    @Autowired
    private PdfSection7071 pdfSection7071;
    @Autowired
    private PdfSection71401 pdfSection71401;
    @Autowired
    private PdfSection71402 pdfSection71402;

    public void addSectionPage(Document doc, Request request){

        String section_id = request.getTitle();

        if(section_id.contains("71.70")){
            pdfSection7071.addSectionPage(doc, request, section_id);
        }
        if(section_id.contains("71.40.1")) {
            pdfSection71401.addSectionPage(doc, request);
        }
        if(section_id.contains("71.40.2")) {
            pdfSection71402.addSectionPage(doc, request);
        }
    }
}
