package com.soen490.cms.Services;

import com.soen490.cms.Models.ApprovalPipeline;
import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Repositories.ApprovalPipelineRepository;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Log4j2
@Service
public class ApprovalPipelineService {

    //Add pipeline service which holds a datastructure with order of approving bodies.
    private ArrayList<String> pipeline;

    @Autowired
    private ApprovalPipelineRepository approvalPipelineRepository;

    @Autowired
    private ApprovalPipelineRequestPackageRepository approvalPipelineRequestPackageRepository;

    public ApprovalPipeline findApprovalPipeline(int id) {
        log.info("find approval pipeline with id " + id);
        return approvalPipelineRepository.findApprovalPipeline(id);
    }

    public ApprovalPipelineRequestPackage findApprovalPipelineRequestPackage(int approvalPipelineId, int requestPackageId) {
        log.info("find request package pipeline with package id " + requestPackageId + " and pipeline id " + approvalPipelineId);
        return approvalPipelineRequestPackageRepository.findApprovalPipelineRequestPackage(approvalPipelineId, requestPackageId);
    }

    public ApprovalPipeline addUpdateApprovalPipeline(ApprovalPipeline approvalPipeline) {
        log.info("add approval pipeline " + approvalPipeline.getId());
        return approvalPipelineRepository.save(approvalPipeline);
    }

    public ApprovalPipelineRequestPackage addUpdateApprovalPipelineRequestPackage(ApprovalPipelineRequestPackage approvalPipelineRequestPackage) {
        log.info("add approval pipeline request package, package id: " + approvalPipelineRequestPackage.getRequestPackage().getId() + ", pipeline id: " + approvalPipelineRequestPackage.getApprovalPipeline().getId());
        return approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    public boolean executeUpdate(int id) {
        // TODO
        return true;
    }


    public ArrayList<String> getPipeline(int id) {
        ArrayList<String> pipeline = new ArrayList<>();
        ApprovalPipeline approvalPipeline = approvalPipelineRepository.findApprovalPipeline(id);

        // adds an academic body to an index in desired order of the pipeline
        if(approvalPipeline.getAPC() != 0) {
            pipeline.add(approvalPipeline.getAPC(), "APC");
        }
        if(approvalPipeline.getSenate() != 0) {
            pipeline.add(approvalPipeline.getSenate(), "Senate");
        }
        if(approvalPipeline.getDepartmentCurriculumCommittee() != 0) {
            pipeline.add(approvalPipeline.getDepartmentCurriculumCommittee(), "Department Curriculum Committee");
        }
        if(approvalPipeline.getDepartmentCouncil() != 0) {
            pipeline.add(approvalPipeline.getDepartmentCouncil(), "Department Council");
        }
        if(approvalPipeline.getUndergraduateStudiesCommittee() != 0) {
            pipeline.add(approvalPipeline.getUndergraduateStudiesCommittee(), "Associate Dean Academic Programs Under Graduate Studies Committee");
        }
        if(approvalPipeline.getFacultyCouncil() != 0) {
            pipeline.add(approvalPipeline.getFacultyCouncil(), "Faculty Council");
        }

        if(approvalPipeline.getSchoolOfGraduateStudies() != 0) {
            pipeline.add(approvalPipeline.getSchoolOfGraduateStudies(), "School of Graduate Studies");
        }

        if(approvalPipeline.getGraduateStudiesCommittee() != 0) {
            pipeline.add(approvalPipeline.getGraduateStudiesCommittee(), "Associate Dean Research Graduate Studies Committee");
        }

        return pipeline;
    }
}
