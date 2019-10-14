package com.soen490.cms.Repositories;

import com.soen490.cms.Models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Integer>{

    @Query(value = "SELECT * FROM supporting_documents WHERE id=?", nativeQuery = true)
    SupportingDocument findBySupportingDocumentId(Long id);
}
