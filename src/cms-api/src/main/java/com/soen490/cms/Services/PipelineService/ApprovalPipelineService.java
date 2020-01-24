package com.soen490.cms.Services.PipelineService;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.ApprovalPipelineRepository;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.UserRepository;
import com.soen490.cms.Services.MailService;
import com.soen490.cms.Services.RequestPackageService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
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
    UserRepository userRepository;

    @Autowired
    MailService mailService;

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

    @Autowired
    RequestPackageService requestPackageService;

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
     * Returns the current approval position of a dossier as a string
     * Returns "Approved" if the dossier received final approval
     * Returns "Not Submitted" if the dossier has not been submitted for approval
     *
     * @param approvalPipelineId
     * @param requestPackageId
     * @return
     */
    public String getPipelinePosition(int approvalPipelineId, int requestPackageId) {
        log.info("get position for request package: " + requestPackageId);
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = findApprovalPipelineRequestPackage(approvalPipelineId, requestPackageId);
        if(approvalPipelineRequestPackage == null) {
            RequestPackage dossier = findRequestPackage(requestPackageId);
            if(dossier == null) {
                return "Approved";
            } else {
                return "Not Submitted";
            }
        }
        return approvalPipelineRequestPackage.getPosition();
    }

    /**
     * Adds a new one-to-one relationship between an approval pipeline and a request package
     *
     * @param approvalPipelineRequestPackage
     * @return
     */
    public ApprovalPipelineRequestPackage saveApprovalPipelineRequestPackage(ApprovalPipelineRequestPackage approvalPipelineRequestPackage) {
        log.info("save approval pipeline request package, package id: " + approvalPipelineRequestPackage.getRequestPackage().getId() + ", pipeline id: " + approvalPipelineRequestPackage.getApprovalPipeline().getId());
        return approvalPipelineRequestPackageRepository.save(approvalPipelineRequestPackage);
    }

    public ApprovalPipeline saveApprovalPipeline(ApprovalPipeline approvalPipeline) {
        log.info("save approval pipeline " + approvalPipeline.getId());
        return approvalPipelineRepository.save(approvalPipeline);
    }

    /**
     * Pushes an approval package from one approving body to the next in the approval pipeline
     *
     * @param packageId
     * @param pipelineId
     * @param pipeline
     * @param currentPosition
     */
    public String pushToNext(int packageId, int pipelineId, List<String> pipeline, int currentPosition, User user) {
        log.info("pushing package " + packageId + " to next position in pipeline");
        String position = pipeline.get(currentPosition);
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineRequestPackageRepository.findApprovalPipelineRequestPackage(pipelineId, packageId);
        String nextPosition = "";

        try{
            nextPosition = pipeline.get(currentPosition + 1); // get next position if it exists
        } catch(NullPointerException | IndexOutOfBoundsException e) {
            return finalizeDossierRequests(requestPackageRepository.findById(packageId), approvalPipelineRequestPackage, user);
        }

        if(nextPosition == null) { // at the last position in the pipeline, approve the dossier
            return finalizeDossierRequests(requestPackageRepository.findById(packageId), approvalPipelineRequestPackage, user);
        }

        RequestPackage requestPackage = null;
        List<User> users = userRepository.findUserByType(nextPosition);

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

        sendMail(users, requestPackage); // send an email notification to all users in the next position
        approvalPipelineRequestPackage.setUser(user);
        approvalPipelineRequestPackage.setPosition(pipeline.get(currentPosition + 1));
        saveApprovalPipelineRequestPackage(approvalPipelineRequestPackage);

        return "";
    }

    /**
     * Returns a list of request packages by position/user type
     *
     * @param userType
     * @return
     */
    public List<RequestPackage> getRequestPackagesByUserType(String userType) {
        List<RequestPackage> requestPackages = new ArrayList<>();
        List<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages = approvalPipelineRequestPackageRepository.findByPosition(userType); // userType == position
        for(ApprovalPipelineRequestPackage approvalPipelineRequestPackage : approvalPipelineRequestPackages) {
            RequestPackage requestPackage = approvalPipelineRequestPackage.getRequestPackage();
            requestPackages.add(requestPackage);
        }
        return requestPackages;
    }

    /**
     * Uses the Mail Service to send an email to the users in the next approving body according to the Dossier's
     * pipeline
     *
     * @param users
     * @param dossier
     * @return
     */
    private boolean sendMail(List<User> users, RequestPackage dossier) {
        boolean success = true;

        for(User user : users) {

            if(user != null && !user.getEmail().contains("@soen.com"))
                success = mailService.sendMailService(dossier.getId(), user);
        }

        return success;
    }

    /**
     * Removes a request package and its tracking when it is rejected by an approving body
     *
     * @param packageId
     * @param pipelineId
     * @param rationale
     * @return
     */
    public boolean removePackage(int packageId, int pipelineId, String rationale) {
        RequestPackage requestPackage = requestPackageRepository.findById(packageId);
        requestPackage.setRejectionRationale(rationale);
        approvalPipelineRequestPackageRepository.remove(pipelineId, packageId);
        requestPackageRepository.save(requestPackage);
        //requestPackageService.deleteCourseRequest(packageId);
        return true;
    }

    public String getRejectionRationale(int packageId) {
        RequestPackage requestPackage = requestPackageRepository.findById(packageId);
        return requestPackage.getRejectionRationale();
    }

    /**
     * Pushes an approval package from one approving body to the previous one in the approval pipeline
     *
     * @param packageId
     * @param pipelineId
     * @param pipeline
     * @param currentPosition
     */
    public void pushToPrevious(int packageId, int pipelineId, List<String> pipeline, int currentPosition, String rationale) {
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
        saveApprovalPipelineRequestPackage(approvalPipelineRequestPackage);
    }

    /**
     * TODO
     * Commits the changes of a request package
     *
     * @param dossier
     * @return
     */
    public String finalizeDossierRequests(RequestPackage dossier, ApprovalPipelineRequestPackage approvalPipelineRequestPackage, User user) {
        log.info("Finalizing dossier " + dossier.getId());
        requestPackageService.finalizeDossierRequests(dossier);
        return "Making the requested changes to the database";
    }

    /**
     * Receives a list of academic bodies in order for an approval pipeline
     * Will return an existing approval pipeline if one already exists in the database
     * Saves the new approval pipeline in the database if it does not exist and returns it
     *
     * @param pipeline
     * @return
     */
    public ApprovalPipeline createApprovalPipeline(String[] pipeline) throws JSONException {
        List<ApprovalPipeline> pipelines = approvalPipelineRepository.findAll();
        ApprovalPipeline approvalPipeline = new ApprovalPipeline();

        for(int i = 0; i < pipeline.length; i++) {
            if(pipeline[i].contains("Department Curriculum Committee")) {
                approvalPipeline.setDepartmentCurriculumCommittee(i + 1);
            }
            if(pipeline[i].contains("Department Council")){
                approvalPipeline.setDepartmentCouncil(i + 1);
            }
            if(pipeline[i].contains("Associate Dean Academic Programs Under Graduate Studies Committee")) {
                approvalPipeline.setUndergraduateStudiesCommittee(i + 1);
            }
            if(pipeline[i].contains("Faculty Council")) {
                approvalPipeline.setFacultyCouncil(i + 1);
            }
            if(pipeline[i].contains("APC")) {
                approvalPipeline.setAPC(i + 1);
            }
            if(pipeline[i].contains("Senate")) {
                approvalPipeline.setSenate(i + 1);
            }
        }

        // check if the one of the pipelines currently in the database is the same as the new one
        if(pipelines.contains(approvalPipeline)) {
            return pipelines.get(pipelines.indexOf(approvalPipeline));
        } else {
            saveApprovalPipeline(approvalPipeline);
        }

        return approvalPipeline;
    }

    public int getPipelineId(int id) {
        RequestPackage requestPackage = requestPackageRepository.findById(id);
        if(requestPackage == null) {
            return -1; // no package with that id in the database
        }

        List<ApprovalPipeline> approvalPipelines = getPipelineByPackageId(id);
        ApprovalPipeline approvalPipeline = approvalPipelines.get(approvalPipelines.size() - 1);

        if(approvalPipeline == null)
            return 0; // dossier not associated with a pipeline yet

        return approvalPipeline.getId();
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
        log.info(id);
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


    public List<ApprovalPipeline> getPipelineByPackageId(int package_id) {

        return approvalPipelineRepository.findByPackageId(package_id);
    }

    public List getPipelineRevisions(int id) {

        log.info("Retrieving revision history for pipeline " + id + ".");

        List<Object[]> revisions = approvalPipelineRequestPackageRepository.getRevisions(id);

        List<PipelineRevision> versions = new ArrayList<>();

        if (revisions.isEmpty()) return null;

        for (Object[] r : revisions)
            versions.add(new PipelineRevision((Integer) r[0], (Integer) r[1], (Byte) r[2],(BigInteger) r[3],
                    userRepository.findUserById((Integer) r[4]), (String) r[5]));

        return versions;
    }
}
