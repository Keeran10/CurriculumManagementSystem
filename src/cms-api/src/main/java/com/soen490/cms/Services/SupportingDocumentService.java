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
