package com.soen490.cms.Repositories;

import com.soen490.cms.Models.SupportingDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SupportingDocumentRepository extends JpaRepository<SupportingDocument, Integer>{

    @Query(value = "SELECT * FROM supporting_document WHERE id=?", nativeQuery = true)
    SupportingDocument findBySupportingDocumentId(int id);

    @Query(value = "SELECT * FROM supporting_document LEFT JOIN request_package ON " +
            "request_package.id = supporting_document.package_id WHERE supporting_document.package_id=?", nativeQuery = true)
    List<SupportingDocument> findByPackage(int package_id);
}
