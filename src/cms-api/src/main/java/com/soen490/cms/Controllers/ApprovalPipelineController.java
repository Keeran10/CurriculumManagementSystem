package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Services.ApprovalPipelineService;
import com.soen490.cms.Services.RequestPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.TreeMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ApprovalPipelineController {

    @Autowired
    ApprovalPipelineService approvalPipelineService;

    @Autowired
    RequestPackageService requestPackageService;

    // Add pipeline controller to receive package and approving order
    @GetMapping(value = "/requestPackage")
    public RequestPackage getRequestPackage(@RequestParam("package_id") Long requestPackageId) {
        return requestPackageService.find(requestPackageId);
    }

    @GetMapping(value = "/approvalPipelinePosition")
    public String getCurrentPosition(@RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId) {
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineService.findApprovalPipelineRequestPackage(approvalPipelineId, packageId);
        return approvalPipelineRequestPackage.getPosition();
    }

    /**
     * Method to update the position of the request package pipeline
     * It will create a new one if one does not already exist in the database
     *
     * @param packageId
     * @param approvalPipelineId
     * @param position (Department Curriculum Committee | Department Council | Associate Dean Academic Programs Under Graduate Studies Committee | Faculty Council | APC | Senate
     */
    @PostMapping(value = "/updatePipelinePosition")
    public void setCurrentPosition(@RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId, @RequestParam("position") String position) {
        ApprovalPipeline pipeline = approvalPipelineService.findApprovalPipeline(approvalPipelineId);
        RequestPackage requestPackage = requestPackageService.find((long)packageId);
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = new ApprovalPipelineRequestPackage();
        approvalPipelineRequestPackage.setApprovalPipeline(pipeline);
        approvalPipelineRequestPackage.setRequestPackage(requestPackage);
        approvalPipelineRequestPackage.setPosition(position);

        approvalPipelineService.addUpdateApprovalPipelineRequestPackage(approvalPipelineRequestPackage);
    }

    @PostMapping(value = "/validatePackage")
    public void setApprove(User user, @RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId, @RequestParam("is_approved") boolean isApproved) {
        String type = user.getUserType();
        String currentPosition = approvalPipelineService.findApprovalPipelineRequestPackage(approvalPipelineId, packageId).getPosition();
        ArrayList<String> pipeline = approvalPipelineService.getPipeline(approvalPipelineId);

        if(isCorrectUserType(type, currentPosition)) {
            currentPosition = getCurrentPosition(packageId, approvalPipelineId);
            int index = pipeline.indexOf(currentPosition);
            if(isApproved) { // move to next step
                if(index == pipeline.size() - 1) { // last step, merge change package into database
                    approvalPipelineService.executeUpdate(packageId);
                } else {
                   setCurrentPosition(packageId, approvalPipelineId, pipeline.get(index + 1));
                }
            } else { // not approved - move back a step
                setCurrentPosition(packageId, approvalPipelineId, pipeline.get(index - 1));
            }
        }
    }

    @GetMapping(value = "/lol")
    public void lol() {
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = new ApprovalPipelineRequestPackage();
        RequestPackage requestPackage = requestPackageService.find((long)1);
        ApprovalPipeline pipeline = approvalPipelineService.findApprovalPipeline(1);
        approvalPipelineRequestPackage.setPosition("Senate");
        approvalPipelineRequestPackage.setRequestPackage(requestPackage);
        approvalPipelineRequestPackage.setApprovalPipeline(pipeline);
        approvalPipelineService.addUpdateApprovalPipelineRequestPackage(approvalPipelineRequestPackage);
    }

    // TODO
    private boolean isCorrectUserType(String userType, String position) {
        return true;
    }
}
