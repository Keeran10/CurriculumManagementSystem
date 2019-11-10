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

package com.soen490.cms.Services;

import com.soen490.cms.Models.SupportingDocument;
import com.soen490.cms.Repositories.SupportingDocumentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@Log4j2
public class SupportingDocumentService {

    @Autowired
    private SupportingDocumentRepository supportingDocsRepo;

    public Collection<SupportingDocument> findAll() {
        log.info("find all supporting docs");
        return supportingDocsRepo.findAll();
    }

    public SupportingDocument find(Long documentId) {
        log.info("find supporting document with id " + documentId);
        return supportingDocsRepo.findBySupportingDocumentId(documentId);
    }

    public SupportingDocument addSupportingDocument(SupportingDocument supportingDocument) {
        log.info("add supporting document " + supportingDocument.getId());
        return supportingDocsRepo.save(supportingDocument);
    }
}
