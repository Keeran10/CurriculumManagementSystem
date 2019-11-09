package com.soen490.cms.Services;

import com.soen490.cms.Models.ApprovalPipeline;
import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Repositories.ApprovalPipelineRepository;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class ApprovalPipelineService {

    @Autowired
    ApprovalPipelineRepository approvalPipelineRepository;

    @Autowired
    ApprovalPipelineRequestPackageRepository approvalPipelineRequestPackageRepository;

    @Autowired
    RequestPackageRepository requestPackageRepository;

    @Autowired
    SenateService senateService;

    @Autowired
    DCCService dccService;

    @Autowired
    DepartmentCouncilService departmentCouncilService;

    @Autowired
    APCService apcService;

    @Autowired
    FacultyCouncilService facultyCouncilService;

    @Autowired
    UndergradStudiesCommitteeService undergradStudiesCommitteeService;

    public RequestPackage findRequestPackage(int id) {
        log.info("find request package with id " + id);
        return requestPackageRepository.findById(id);
    }

    public ApprovalPipeline findApprovalPipeline(int id) {
        log.info("find approval pipeline with id " + id);
        return approvalPipelineRepository.findApprovalPipeline(id);
    }

    public ApprovalPipelineRequestPackage findApprovalPipelineRequestPackage(int approvalPipelineId, int requestPackageId) {
        log.info("find request package pipeline with package id " + requestPackageId + " and pipeline id " + approvalPipelineId);
        return approvalPipelineRequestPackageRepository.findApprovalPipelineRequestPackage(approvalPipelineId, requestPackageId);
    }

    /**
     * Adds a new one-to-one relationship between an approval pipeline and a request package
     *
     * @param approvalPipelineRequestPackage
     * @return
     */
    public ApprovalPipelineRequestPackage addApprovalPipelineRequestPackage(ApprovalPipelineRequestPackage approvalPipelineRequestPackage) {
        log.info("add approval pipeline request package, package id: " + approvalPipelineRequestPackage.getRequestPackage().getId() + ", pipeline id: " + approvalPipelineRequestPackage.getApprovalPipeline().getId());
        return approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    /**
     * Pushes an approval package from one approving body to the next in the approval pipeline
     *
     * @param packageId
     * @param pipelineId
     * @param pipeline
     * @param currentPosition
     */
    public void pushToNext(int packageId, int pipelineId, List<String> pipeline, int currentPosition) {
        log.info("pushing package " + packageId + " to next position in pipeline");
        String position = pipeline.get(currentPosition);
        String nextPosition = pipeline.get(currentPosition + 1);
        RequestPackage requestPackage = null;
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineRequestPackageRepository.findApprovalPipelineRequestPackage(pipelineId, packageId);

        if(position.equals("Department Curriculum Committee")) {
            requestPackage = dccService.sendPackage(packageId);
        } else if(position.equals("Department Council")) {
            requestPackage = departmentCouncilService.sendPackage(packageId);
        } else if(position.equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
            requestPackage = undergradStudiesCommitteeService.sendPackage(packageId);
        } else if(position.equals("Faculty Council")) {
            requestPackage = facultyCouncilService.sendPackage(packageId);
        } else if(position.equals("APC")) {
            requestPackage = apcService.sendPackage(packageId);
        } else if(position.equals("Senate")) {
            requestPackage = senateService.sendPackage(packageId);
        }

        // push package to next service, update one-to-one relationship
        if(nextPosition.equals("Department Curriculum Committee")) {
            dccService.receivePackage(requestPackage);
        } else if(nextPosition.equals("Department Council")) {
            departmentCouncilService.receivePackage(requestPackage);
        } else if(nextPosition.equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
            undergradStudiesCommitteeService.receivePackage(requestPackage);
        } else if(nextPosition.equals("Faculty Council")) {
            facultyCouncilService.receivePackage(requestPackage);
        } else if(nextPosition.equals("APC")) {
            apcService.receivePackage(requestPackage);
        } else if(nextPosition.equals("Senate")) {
            senateService.receivePackage(requestPackage);
        }
        approvalPipelineRequestPackage.setPosition(pipeline.get(currentPosition + 1));
        approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    /**
     * Pushes an approval package from one approving body to the previous one in the approval pipeline
     *
     * @param packageId
     * @param pipelineId
     * @param pipeline
     * @param currentPosition
     */
    public void pushToPrevious(int packageId, int pipelineId, List<String> pipeline, int currentPosition) {
        log.info("push package " + packageId + " to previous position in pipeline");
        String position = pipeline.get(currentPosition);
        String previousPosition = pipeline.get(currentPosition + 1);
        RequestPackage requestPackage = null;
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineRequestPackageRepository.findApprovalPipelineRequestPackage(pipelineId, packageId);

        if(position.equals("Department Curriculum Committee")) {
            requestPackage = dccService.sendPackage(packageId);
        } else if(position.equals("Department Council")) {
            requestPackage = departmentCouncilService.sendPackage(packageId);
        } else if(position.equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
            requestPackage = undergradStudiesCommitteeService.sendPackage(packageId);
        } else if(position.equals("Faculty Council")) {
            requestPackage = facultyCouncilService.sendPackage(packageId);
        } else if(position.equals("APC")) {
            requestPackage = apcService.sendPackage(packageId);
        } else if(position.equals("Senate")) {
            requestPackage = senateService.sendPackage(packageId);
        }

        // push package to next service, update one-to-one relationship
        if(previousPosition.equals("Department Curriculum Committee")) {
            dccService.receivePackage(requestPackage);
        } else if(previousPosition.equals("Department Council")) {
            departmentCouncilService.receivePackage(requestPackage);
        } else if(previousPosition.equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
            undergradStudiesCommitteeService.receivePackage(requestPackage);
        } else if(previousPosition.equals("Faculty Council")) {
            facultyCouncilService.receivePackage(requestPackage);
        } else if(previousPosition.equals("APC")) {
            apcService.receivePackage(requestPackage);
        } else if(previousPosition.equals("Senate")) {
            senateService.receivePackage(requestPackage);
        }
        approvalPipelineRequestPackage.setPosition(pipeline.get(currentPosition + 1));
        approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    /**
     * TODO
     * Commits the changes of a request package
     *
     * @param id
     * @return
     */
    public boolean executeUpdate(int id) {
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
        List<ApprovalPipeline> pipelines = approvalPipelineRepository.findAll();
        ApprovalPipeline approvalPipeline = new ApprovalPipeline();

        for(int i = 0; i < pipeline.length; i++) {
            if(pipeline[i].equals("Department Curriculum Committee")) {
                approvalPipeline.setDepartmentCurriculumCommittee(i + 1);
            }
            if(pipeline[i].equals("Department Council")){
                approvalPipeline.setDepartmentCouncil(i + 1);
            }
            if(pipeline[i].equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
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
    public List<String> getPipeline(int id) {
        List<String> pipeline = new ArrayList<>();
        String[] pipelineArray = new String[6]; // max number of academic bodies == 6
        ApprovalPipeline approvalPipeline = approvalPipelineRepository.findApprovalPipeline(id);

        // adds an academic body to an index in desired order of the pipeline
        if(approvalPipeline.getAPC() != 0) {
            pipelineArray[approvalPipeline.getAPC() - 1] = "APC";
        }
        if(approvalPipeline.getSenate() != 0) {
            pipelineArray[approvalPipeline.getSenate() - 1] = "Senate";
        }
        if(approvalPipeline.getDepartmentCurriculumCommittee() != 0) {
            pipelineArray[approvalPipeline.getDepartmentCurriculumCommittee() - 1] = "Department Curriculum Committee";
        }
        if(approvalPipeline.getDepartmentCouncil() != 0) {
            pipelineArray[approvalPipeline.getDepartmentCouncil() - 1] = "Department Council";
        }
        if(approvalPipeline.getUndergraduateStudiesCommittee() != 0) {
            pipelineArray[approvalPipeline.getUndergraduateStudiesCommittee() - 1] = "Associate Dean Academic Programs Under Graduate Studies Committee";
        }
        if(approvalPipeline.getFacultyCouncil() != 0) {
            pipelineArray[approvalPipeline.getFacultyCouncil() - 1] = "Faculty Council";
        }

        pipeline = Arrays.asList(pipelineArray);

        return pipeline;
    }
}
