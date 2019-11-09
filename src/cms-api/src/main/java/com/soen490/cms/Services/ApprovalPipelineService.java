package com.soen490.cms.Services;

import com.soen490.cms.Models.ApprovalPipeline;
import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Repositories.ApprovalPipelineRepository;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    /**
     * Adds a new ApprovalPipeline to the database
     * Updates one if one already exists
     *
     * @param approvalPipeline
     * @return
     */
    public ApprovalPipeline addUpdateApprovalPipeline(ApprovalPipeline approvalPipeline) {
        log.info("add approval pipeline " + approvalPipeline.getId());
        return approvalPipelineRepository.save(approvalPipeline);
    }

    /**
     * Adds a new ApprovalPipelineRequestPackage to the database
     * Updates one if one already exists
     *
     * @param approvalPipelineRequestPackage
     * @return
     */
    public ApprovalPipelineRequestPackage addUpdateApprovalPipelineRequestPackage(ApprovalPipelineRequestPackage approvalPipelineRequestPackage) {
        log.info("add approval pipeline request package, package id: " + approvalPipelineRequestPackage.getRequestPackage().getId() + ", pipeline id: " + approvalPipelineRequestPackage.getApprovalPipeline().getId());
        return approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    public boolean executeUpdate(int id) {
        // TODO
        return true;
    }

    /**
     * Receives a list of academic bodies in order for an approval pipeline
     * Will return an existing approval pipeline if one already exists in the database
     * Saves the new approval pipeline in the database if it does not exist and returns it
     *
     * @param pipeline
     * @return
     */
    public ApprovalPipeline createApprovalPipeline(String[] pipeline) {
        ArrayList<ApprovalPipeline> pipelines = approvalPipelineRepository.findAll();
        ApprovalPipeline approvalPipeline = new ApprovalPipeline();

        for(int i = 0; i < pipeline.length; i++) {
            if(pipeline[i].equals("Department Curriculum Committee")) {
                approvalPipeline.setDepartmentCurriculumCommittee(i + 1);
            }
            if(pipeline[i].equals("Department Council")){
                approvalPipeline.setDepartmentCouncil(i + 1);
            }
            if(pipeline[i].equals("Associate Dean Academic Programs Under Graduate Studies Committee") {
                approvalPipeline.setUndergraduateStudiesCommittee(i + 1);
            }
            if(pipeline[i].equals("Faculty Council")) {
                approvalPipeline.setFacultyCouncil(i + 1);
            }
            if(pipeline[i].equals("APC")) {
                approvalPipeline.setAPC(i + 1);
            }
            if(pipeline[i].equals("Senate")) {
                approvalPipeline.setSenate(i + 1);
            }
        }

        // check if the one of the pipelines currently in the database is the same as the new one
        if(pipelines.contains(approvalPipeline)) {
            return pipelines.get(pipelines.indexOf(approvalPipeline));
        } else {
            approvalPipelineRepository.save(approvalPipeline);
        }

        return approvalPipeline;
    }

    /**
     * Returns a list representation of an approval pipeline
     *
     * @param id
     * @return
     */
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
