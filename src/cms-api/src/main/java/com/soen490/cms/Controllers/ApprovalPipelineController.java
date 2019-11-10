package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Services.ApprovalPipelineService;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
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
    @GetMapping(value = "/approvalPipelineObject")
    public ApprovalPipeline getApprovalPipeline(@RequestParam("approval_pipeline_id") int approvalPipelineId) {
        return approvalPipelineService.findApprovalPipeline(approvalPipelineId);
    }

    /**
     * Returns an array representation of the approval pipeline
     *
     * @param approvalPipelineId
     * @return
     */
    @GetMapping(value = "/approvalPipeline")
    public String[] getApprovalPipelineList(@RequestParam("approval_pipeline_id") int approvalPipelineId) {
        List<String> pipeline = approvalPipelineService.getPipeline(approvalPipelineId);
        String[] pipelineArray = new String[pipeline.size()];
        return pipeline.toArray(pipelineArray);
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
    public void setApprovalPipeline(@RequestParam("approval_pipeline") String pipeline, @RequestParam("package_id") int packageId) throws JSONException {
        ApprovalPipeline approvalPipeline = approvalPipelineService.createApprovalPipeline(pipeline);
        RequestPackage requestPackage = requestPackageService.findById(packageId);
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
     * @return (true|false) depending on whether the user has the required permission to approve/deny at the current step in the approval pipeline
     */
    @PostMapping(value = "/validatePackage")
    public boolean setApprove(User user, @RequestParam("package_id") int packageId, @RequestParam("approval_pipeline_id") int approvalPipelineId, @RequestParam("is_approved") boolean isApproved) {
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
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the user is able to approve/request changes at the current approval position
     *
     * @param userType
     * @param position
     * @return
     */
    private boolean isCorrectUserType(String userType, String position) {
        if(userType.equals("admin")) {
            return true;
        } else if(userType.equals("senate") && position.equals("Senate")) {
            return true;
        } else if(userType.equals("fcc") && position.equals("Faculty Council")) {
            return true;
        } else if(userType.equals("dcc") && position.equals("Department Curriculum Committee")) {
            return true;
        } else if(userType.equals("apc") && position.equals("APC")) {
            return true;
        } else if(userType.equals("departmentCouncil") && position.equals("Department Council")) {
            return true;
        } else if(userType.equals("ugsc") && position.equals("Associate Dean Academic Programs Under Graduate Studies Committee")) {
            return true;
        } else {
            return false;
        }
    }
}
