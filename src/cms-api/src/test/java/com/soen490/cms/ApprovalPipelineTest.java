package com.soen490.cms;

import com.soen490.cms.Controllers.ApprovalPipelineController;
import com.soen490.cms.Models.ApprovalPipeline;
import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.ApprovalPipelineRepository;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.UserRepository;
import com.soen490.cms.Services.PipelineService.*;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApprovalPipelineTest {

    ApprovalPipeline approvalPipeline;
    ApprovalPipeline secondApprovalPipeline;
    ApprovalPipelineRequestPackage approvalPipelineRequestPackage;
    ApprovalPipelineRequestPackage secondApprovalPipelineRequestPackage;
    ArrayList<User> userList;
    RequestPackage requestPackage;
    User senateUser;
    User dccUser;
    User facultyUser;
    User uscUser;
    User apcUser;
    User departmentCouncilUser;
    Collection<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages;
    Collection<ApprovalPipeline> pipelines;

    @Autowired
    ApprovalPipelineService approvalPipelineService;

    @Autowired
    ApprovalPipelineController approvalPipelineController;

    @MockBean
    ApprovalPipelineRequestPackageRepository approvalPipelineRequestPackageRepository;

    @MockBean
    ApprovalPipelineRepository approvalPipelineRepository;

    @MockBean
    RequestPackageRepository requestPackageRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    RequestPackageService requestPackageService;

    @Mock
    FacultyCouncilService facultyCouncilService;

    @Before
    public void init() {
        // set up approval pipeline
        approvalPipeline = new ApprovalPipeline();
        approvalPipeline.setId(1);
        approvalPipeline.setDepartmentCurriculumCommittee(1);
        approvalPipeline.setDepartmentCouncil(2);
        approvalPipeline.setFacultyCouncil(3);
        approvalPipeline.setUndergraduateStudiesCommittee(4);
        approvalPipeline.setAPC(5);
        approvalPipeline.setSenate(6);
        // set up second approval pipeline
        secondApprovalPipeline = new ApprovalPipeline();
        secondApprovalPipeline.setId(2);
        secondApprovalPipeline.setSenate(1);
        secondApprovalPipeline.setDepartmentCurriculumCommittee(2);
        // set up request package
        requestPackage = new RequestPackage();
        requestPackage.setId(1);
        // set up approval pipeline request package
        approvalPipelineRequestPackage = new ApprovalPipelineRequestPackage();
        approvalPipelineRequestPackage.setPosition("Department Curriculum Committee");
        approvalPipelineRequestPackage.setApprovalPipeline(approvalPipeline);
        approvalPipelineRequestPackage.setRequestPackage(requestPackage);
        //
        secondApprovalPipelineRequestPackage = new ApprovalPipelineRequestPackage();
        secondApprovalPipelineRequestPackage.setPosition("Senate");
        secondApprovalPipelineRequestPackage.setApprovalPipeline(secondApprovalPipeline);
        secondApprovalPipelineRequestPackage.setRequestPackage(requestPackage);
        // set up users
        dccUser = new User();
        dccUser.setId(1);
        dccUser.setUserType("Department Curriculum Committee");
        //
        senateUser = new User();
        senateUser.setId(2);
        senateUser.setUserType("Senate");
        //
        facultyUser = new User();
        facultyUser.setId(3);
        facultyUser.setUserType("Faculty Council");
        //
        uscUser = new User();
        uscUser.setId(4);
        uscUser.setUserType("UGSC");
        //
        apcUser = new User();
        apcUser.setId(5);
        apcUser.setUserType("APC");
        //
        departmentCouncilUser = new User();
        departmentCouncilUser.setId(6);
        departmentCouncilUser.setUserType("Department Council");

        userList = new ArrayList<>();
        userList.add(dccUser);

        approvalPipelineRequestPackages = new ArrayList<>();
        approvalPipelineRequestPackages.add(approvalPipelineRequestPackage);

        pipelines = new ArrayList<>();
        pipelines.add(approvalPipeline);
    }

    @Test
    public void testApproval() {
        doReturn(requestPackage).when(requestPackageRepository).findById(1);
        doReturn(userList).when(userRepository).findUserByType(any(String.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).save(any(ApprovalPipelineRequestPackage.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).save(any(ApprovalPipelineRequestPackage.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(1,1);
        doReturn(secondApprovalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(2,1);
        doReturn(approvalPipelineRequestPackages).when(approvalPipelineRequestPackageRepository).findAll();
        doReturn(approvalPipeline).when(approvalPipelineRepository).findApprovalPipeline(1);
        doReturn(secondApprovalPipeline).when(approvalPipelineRepository).findApprovalPipeline(2);
        doNothing().when(facultyCouncilService).receivePackage(any(RequestPackage.class));

        String currentPosition = "";
        int currentPositionIndex;
        int nextPositionIndex;
        String nextPosition;
        List<String> pipeline = approvalPipelineService.getPipeline(approvalPipeline.getId());
        List<User> users = new ArrayList<>();
        users.add(dccUser);
        users.add(departmentCouncilUser);
        users.add(facultyUser);
        users.add(uscUser);
        users.add(apcUser);

        for(User user : users) {
            currentPosition = approvalPipelineRequestPackage.getPosition();
            currentPositionIndex = pipeline.indexOf(currentPosition);
            nextPositionIndex = currentPositionIndex + 1;
            nextPosition = pipeline.get(nextPositionIndex);
            approvalPipelineService.pushToNext(requestPackage.getId(), approvalPipeline.getId(), pipeline, currentPositionIndex, user);
            assertEquals(nextPosition, approvalPipelineRequestPackage.getPosition());
        }
        // final approval
        currentPosition = approvalPipelineRequestPackage.getPosition();
        currentPositionIndex = pipeline.indexOf(currentPosition);
        String finalMessage = approvalPipelineService.pushToNext(requestPackage.getId(), approvalPipeline.getId(), pipeline, currentPositionIndex, senateUser);
        assertEquals("Making the requested changes to the database", finalMessage);

        // test approval for a pipeline with less than 6 approving bodies
        List<String> secondPipeline = approvalPipelineService.getPipeline(secondApprovalPipeline.getId());
        currentPosition = secondApprovalPipelineRequestPackage.getPosition();
        currentPositionIndex = secondPipeline.indexOf(currentPosition);
        nextPositionIndex = currentPositionIndex + 1;
        nextPosition = secondPipeline.get(nextPositionIndex);
        approvalPipelineService.pushToNext(requestPackage.getId(), secondApprovalPipeline.getId(), secondPipeline, currentPositionIndex, senateUser);
        assertEquals(nextPosition, secondApprovalPipelineRequestPackage.getPosition());
        currentPosition = secondApprovalPipelineRequestPackage.getPosition();
        currentPositionIndex = secondPipeline.indexOf(currentPosition);
        finalMessage = approvalPipelineService.pushToNext(requestPackage.getId(), secondApprovalPipeline.getId(), secondPipeline, currentPositionIndex, dccUser);
        assertEquals("Making the requested changes to the database", finalMessage);
    }

    @Test
    public void testRejection() {
        doNothing().when(approvalPipelineRequestPackageRepository).remove(1,1);
        doReturn(requestPackage).when(requestPackageRepository).findById(1);
        doReturn(requestPackage).when(requestPackageRepository).save(any(RequestPackage.class));

        String rejectionRationale = "Changes not approved";
        approvalPipelineService.removePackage(requestPackage.getId(), approvalPipeline.getId(), rejectionRationale);
        assertEquals(rejectionRationale, approvalPipelineService.getRejectionRationale(requestPackage.getId()));
    }

    @Test
    public void testFinalApproval() {
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).save(any(ApprovalPipelineRequestPackage.class));
        doNothing().when(requestPackageService).finalizeDossierRequests(requestPackage);

        String message = "Making the requested changes to the database";
        approvalPipelineRequestPackage.setPosition("Senate");
        String finalApprovalMessage = approvalPipelineService.finalizeDossierRequests(requestPackage, approvalPipelineRequestPackage, senateUser);
        assertEquals(message, finalApprovalMessage);
    }

    @Test
    public void testSynchronous() throws InterruptedException {
        approvalPipelineController.getEditKey(1); // cannot review package 1
        approvalPipelineController.getReviewKey(2); // cannot edit package 2

        assertEquals(false, approvalPipelineController.getReviewKey(1));
        assertEquals(false, approvalPipelineController.getEditKey(2));

        approvalPipelineController.releaseEditKey(1);
        assertEquals(false, approvalPipelineController.getEditKey(2));
        assertEquals(true, approvalPipelineController.getReviewKey(1));

        approvalPipelineController.releaseReviewKey(2);
        assertEquals(false, approvalPipelineController.getEditKey(1));
        assertEquals(true, approvalPipelineController.getReviewKey(2));
    }

    @Test
    public void testCreatePipeline() throws JSONException  {
        String[] pipeline1 = {"Department Curriculum Committee", "APC", "Faculty Council", "Associate Dean Academic Programs Under Graduate Studies Committee",
                "Department Council", "Senate"};
        ApprovalPipeline testApprovalPipeline = approvalPipelineService.createApprovalPipeline(pipeline1);
        doReturn(testApprovalPipeline).when(approvalPipelineRepository).save(approvalPipeline);
        doReturn(approvalPipeline).when(approvalPipelineRepository).findApprovalPipeline(1);
        doReturn(pipelines).when(approvalPipelineRepository).findAll();
        List<String> pipeline2 = approvalPipelineService.getPipeline(1);
        String[] pipelineString = new String[6];
        assertEquals(testApprovalPipeline, approvalPipelineService.createApprovalPipeline(pipeline1)); // test a new pipeline
        assertEquals(approvalPipeline, approvalPipelineService.createApprovalPipeline(pipeline2.toArray(pipelineString))); // test a pipeline that was already created
    }

    @Test
    public void testPipelinePosition() {
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(1,1);
        doReturn(null).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(1, 2); // package 2 does not exist
        doReturn(null).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(2, 1); // pipeline 2 does not exist
        doReturn(requestPackage).when(requestPackageRepository).findById(1);
        doReturn(null).when(requestPackageRepository).findById(2);

        assertEquals(approvalPipelineRequestPackage.getPosition(), approvalPipelineService.getPipelinePosition(1,1));
        assertEquals("Approved", approvalPipelineService.getPipelinePosition(1,2));
        assertEquals("Not Submitted", approvalPipelineService.getPipelinePosition(2,1));
    }

    @Test
    public void testGetPackagesByType() {
        String userType = "Senate"; // should return a list of request packages with size 1
        List<ApprovalPipelineRequestPackage> testApprovalPipelineRequestPackages = new ArrayList<>();
        testApprovalPipelineRequestPackages.add(secondApprovalPipelineRequestPackage);

        doReturn(testApprovalPipelineRequestPackages).when(approvalPipelineRequestPackageRepository).findByPosition(userType);

        assertEquals(1, approvalPipelineService.getRequestPackagesByUserType(userType).size());
        assertEquals(requestPackage, approvalPipelineService.getRequestPackagesByUserType(userType).get(0));
    }
}
