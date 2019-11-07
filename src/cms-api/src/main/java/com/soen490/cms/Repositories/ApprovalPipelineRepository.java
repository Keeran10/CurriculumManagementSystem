package com.soen490.cms.Repositories;

import com.soen490.cms.Models.ApprovalPipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalPipelineRepository extends JpaRepository<ApprovalPipeline, Integer>{

    @Query(value = "SELECT * FROM approval_pipeline WHERE id=?", nativeQuery = true)
    ApprovalPipeline findApprovalPipeline(int id);
}
