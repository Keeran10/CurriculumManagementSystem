package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Services.ApprovalPipelineService;
import com.soen490.cms.Services.RequestPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApprovalPipelineController {

    @Autowired
    ApprovalPipelineService approvalPipelineService;

    @Autowired
    RequestPackageService requestPackageService;

    /**
     * Returns an approval pipeline
     *
     * @param approvalPipelineId
     * @return
     */
    @GetMapping(value = "/approvalPipeline")
    public ApprovalPipeline getApprovalPipeline(@RequestParam("approval_pipeline_id") int approvalPipelineId) {
        return approvalPipelineService.findApprovalPipeline(approvalPipelineId);
    }

    /**
     * Returns the current position that the request package is at
     *
     * @param packageId
     * @param approvalPipelineId
     * @return
     */
    @GetMapping(value = "/approvalPipelinePosition")
    public String getCurrentPosition(@RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId) {
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineService.findApprovalPipelineRequestPackage(approvalPipelineId, packageId);
        return approvalPipelineRequestPackage.getPosition();
    }

    /**
     * Creates a new Approval Pipeline if the requested approval pipeline does not exist and associates it to a request package
     *
     * @param pipeline
     * @param packageId
     */
    @PostMapping(value = "/setApprovalPipeline")
    public void setApprovalPipeline(@RequestParam("approval_pipeline") String[] pipeline, @RequestParam("package_id") int packageId) {
        ApprovalPipeline approvalPipeline = approvalPipelineService.createApprovalPipeline(pipeline);
        RequestPackage requestPackage = requestPackageService.find((long)packageId);
        List<String> pipelineList = approvalPipelineService.getPipeline(approvalPipeline.getId());
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = new ApprovalPipelineRequestPackage();

        // create ApprovalPipelineRequestPackage to link a request package to an approval pipeline -> tracking
        approvalPipelineRequestPackage.setRequestPackage(requestPackage);
        approvalPipelineRequestPackage.setApprovalPipeline(approvalPipeline);
        approvalPipelineRequestPackage.setPosition(pipelineList.get(0)); // get first position of pipeline

        approvalPipelineService.addApprovalPipelineRequestPackage(approvalPipelineRequestPackage);
    }

    /**
     * Method that moves a request package to the next/previous step in the pipeline if it is approved/not approved
     *
     * @param user
     * @param packageId
     * @param approvalPipelineId
     * @param isApproved
     */
    @PostMapping(value = "/validatePackage")
    public void setApprove(User user, @RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId, @RequestParam("is_approved") boolean isApproved) {
        String type = user.getUserType();
        String currentPosition = getCurrentPosition(packageId, approvalPipelineId);
        List<String> pipeline = approvalPipelineService.getPipeline(approvalPipelineId);

        if(isCorrectUserType(type, currentPosition)) {
            int index = pipeline.indexOf(currentPosition);
            if(isApproved) { // move to next step
                if(index == pipeline.size() - 1) { // last step, merge change package into database
                    approvalPipelineService.executeUpdate(packageId);
                } else {
                    approvalPipelineService.pushToNext(packageId, approvalPipelineId, pipeline, index);
                }
            } else { // not approved - move back a step
                approvalPipelineService.pushToPrevious(packageId, approvalPipelineId, pipeline, index);
            }
        }
    }

    @GetMapping(value = "/lol")
    public void lol() {
        User user = new User();
        int packageId = 1;
        int approvalPipelineId = 1;
        boolean isApproved = true;

        getCurrentPosition(1, 1);
    }

    // TODO
    /**
     *
     *
     * @param userType
     * @param position
     * @return
     */
    private boolean isCorrectUserType(String userType, String position) {
        return true;
    }
}
