// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package com.soen490.cms.Repositories;

import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface ApprovalPipelineRequestPackageRepository extends JpaRepository<ApprovalPipelineRequestPackage, Integer>,
        RevisionRepository<ApprovalPipelineRequestPackage, Integer, Integer> {

    @Query(value = "SELECT * FROM approval_pipeline_request_package WHERE pipeline_id=?1 AND package_id=?2", nativeQuery = true)
    ApprovalPipelineRequestPackage findApprovalPipelineRequestPackage(int pipelineId, int packageId);

    @Query(value = "SELECT * FROM approval_pipeline_request_package WHERE position=?", nativeQuery = true)
    List<ApprovalPipelineRequestPackage> findByPosition(String position);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM approval_pipeline_request_package WHERE pipeline_id=?1 AND package_id=?2", nativeQuery = true)
    void remove(int pipelineId, int packageId);


    @Query(value = "SELECT rpa.pipeline_id, rpa.rev, rpa.revtype, r.revtstmp, rpa.user_id, rpa.position FROM " +
            "approval_pipeline_request_package_aud rpa, revinfo r WHERE rpa.rev=r.rev AND rpa.pipeline_id=?", nativeQuery = true)
    List<Object[]> getRevisions(int id);
}
