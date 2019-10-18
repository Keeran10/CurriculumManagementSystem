package com.soen490.cms.Repositories;

import com.soen490.cms.Models.ApprovalPipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalPipelineRepository extends JpaRepository<ApprovalPipeline, Integer>{
}
