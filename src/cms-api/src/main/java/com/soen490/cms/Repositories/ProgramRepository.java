package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{
}
