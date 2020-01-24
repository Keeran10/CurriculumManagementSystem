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
import com.soen490.cms.Services.PipelineService.ApprovalPipelineService;
import com.soen490.cms.Services.PipelineService.DCCService;
import com.soen490.cms.Services.PipelineService.FacultyCouncilService;
import com.soen490.cms.Services.RequestPackageService;
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
    ApprovalPipelineRequestPackage approvalPipelineRequestPackage;
    ArrayList<User> userList;
    RequestPackage requestPackage;
    User senateUser;
    User dccUser;

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
    DCCService dccService;

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
        approvalPipeline.setFacultyCouncil(2);
        approvalPipeline.setUndergraduateStudiesCommittee(3);
        approvalPipeline.setAPC(4);
        approvalPipeline.setSenate(5);
        // set up request package
        requestPackage = new RequestPackage();
        requestPackage.setId(1);
        // set up approval pipeline request package
        approvalPipelineRequestPackage = new ApprovalPipelineRequestPackage();
        approvalPipelineRequestPackage.setPosition("Department Curriculum Committee");
        approvalPipelineRequestPackage.setApprovalPipeline(approvalPipeline);
        approvalPipelineRequestPackage.setRequestPackage(requestPackage);
        // set up users
        dccUser = new User();
        dccUser.setId(1);
        dccUser.setUserType("Department Curriculum Committee");
        //
        senateUser = new User();
        senateUser.setId(2);
        senateUser.setUserType("Senate");

        userList = new ArrayList<>();
        userList.add(dccUser);
    }

    @Test
    public void testApproval() {
        doReturn(requestPackage).when(requestPackageRepository).findById(1);
        doReturn(userList).when(userRepository).findUserByType(any(String.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).save(any(ApprovalPipelineRequestPackage.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).save(any(ApprovalPipelineRequestPackage.class));
        doReturn(approvalPipelineRequestPackage).when(approvalPipelineRequestPackageRepository).findApprovalPipelineRequestPackage(1,1);
        doReturn(approvalPipeline).when(approvalPipelineRepository).findApprovalPipeline(1);
        doReturn(requestPackage).when(dccService).sendPackage(anyInt());
        doNothing().when(facultyCouncilService).receivePackage(any(RequestPackage.class));

        List<String> pipeline = approvalPipelineService.getPipeline(approvalPipeline.getId());
        String currentPosition = approvalPipelineRequestPackage.getPosition();
        int currentPositionIndex = pipeline.indexOf(currentPosition);
        int nextPositionIndex = currentPositionIndex + 1;
        String nextPosition = pipeline.get(nextPositionIndex);
        approvalPipelineService.pushToNext(requestPackage.getId(), approvalPipeline.getId(), pipeline, currentPositionIndex, dccUser);
        assertEquals(nextPosition, approvalPipelineRequestPackage.getPosition());
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
        assertEquals("Making the requested changes to the database", finalApprovalMessage);
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
}
