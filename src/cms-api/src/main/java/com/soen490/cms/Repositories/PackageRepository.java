package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer>{
}