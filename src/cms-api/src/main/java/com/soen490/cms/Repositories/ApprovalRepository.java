package com.soen490.cms.Repositories;

import com.soen490.cms.Models.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<Approval, Integer>{
}
