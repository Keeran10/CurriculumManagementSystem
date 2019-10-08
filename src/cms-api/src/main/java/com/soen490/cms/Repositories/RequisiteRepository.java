package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Requisite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisiteRepository extends JpaRepository<Requisite, Integer>{
}
