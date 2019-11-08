package com.soen490.cms.Repositories;

import com.soen490.cms.Models.DegreeRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRequirementRepository extends JpaRepository<DegreeRequirement, Integer> {
}
