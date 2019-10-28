package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer>{
    Degree findByName(String name);
}
