package com.soen490.cms.Repositories;

import com.soen490.cms.Models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Integer>{
}
