package com.soen490.cms.Repositories;

import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface ApprovalPipelineRequestPackageRepository extends JpaRepository<ApprovalPipelineRequestPackage, Integer> {

    @Query(value = "SELECT * FROM approval_pipeline_request_package WHERE pipeline_id=?1 AND package_id=?2", nativeQuery = true)
    ApprovalPipelineRequestPackage findApprovalPipelineRequestPackage(int pipelineId, int packageId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM approval_pipeline_request_package WHERE pipeline_id=?1 AND package_id=?2", nativeQuery = true)
    void remove(int pipelineId, int packageId);
}
