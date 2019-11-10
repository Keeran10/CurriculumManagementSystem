package com.soen490.cms;

import com.soen490.cms.Controllers.ApprovalPipelineController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApprovalPipelineTest {

    @Autowired
    private ApprovalPipelineController approvalPipelineController;

    @Test
    public void testCurrentPosition() {
        // tests that the current position isn't null and that the approval pipeline isn't null
        assertNotNull(approvalPipelineController.getApprovalPipeline(1));
        assertNotNull(approvalPipelineController.getCurrentPosition(1, 1));
        assertEquals("Department Curriculum Committee", approvalPipelineController.getCurrentPosition(1,1));
    }
}
