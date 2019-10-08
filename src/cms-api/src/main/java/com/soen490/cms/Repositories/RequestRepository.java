package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{
}
