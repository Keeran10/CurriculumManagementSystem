package com.soen490.cms.Repositories;

import com.soen490.cms.Models.RequestPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestPackageRepository extends JpaRepository<RequestPackage, Integer>{

    @Query(value = "SELECT * FROM request_package WHERE id=?", nativeQuery = true)
    RequestPackage findById(Long id);

    @Query(value = "SELECT * FROM request_package WHERE id=?", nativeQuery = true)
    RequestPackage findById(int id);

    @Query(value = "SELECT pdf_file FROM request_package WHERE id=?", nativeQuery = true)
    byte[] findPdfById(int package_id);
}