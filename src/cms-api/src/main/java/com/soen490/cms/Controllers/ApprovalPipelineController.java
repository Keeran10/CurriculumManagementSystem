package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Services.ApprovalPipelineService;
import com.soen490.cms.Services.RequestPackageService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://192.168.99.100:4200"})
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
        log.info("getApprovalPipelineList " + approvalPipelineId);
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
        log.info(packageId + " " + approvalPipelineId);
        ApprovalPipelineRequestPackage approvalPipelineRequestPackage = approvalPipelineService.findApprovalPipelineRequestPackage(approvalPipelineId, packageId);
        return approvalPipelineRequestPackage.getPosition();
    }

    /**
     * Creates a new Approval Pipeline if the requested approval pipeline does not exist and associates it to a request package
     *
     * @param pipeline_info
     */
    @PostMapping(value = "/setApprovalPipeline")
    public void setApprovalPipeline(@RequestBody String pipeline_info) throws JSONException {
        JSONObject json = new JSONObject(pipeline_info);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        String pipeline = (String) array.getJSONObject(0).get("value");
        int packageId = Integer.valueOf(String.valueOf(array.getJSONObject(1).get("value")));

        String[] pipeline_array = pipeline.split(",");

        pipeline_array[0] = pipeline_array[0].substring(1);
        pipeline_array[pipeline_array.length - 1] = pipeline_array[pipeline_array.length - 1].substring(0, pipeline_array[pipeline_array.length - 1].length() - 1);
        ApprovalPipeline approvalPipeline = approvalPipelineService.createApprovalPipeline(pipeline_array);
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


    @GetMapping(value = "/get_pipeline")
    public int get(@RequestParam int package_id){

        log.info("get pipeline for package " + package_id);

        List<ApprovalPipeline> approvalPipelines = approvalPipelineService.getPipelineByPackageId(package_id);

        ApprovalPipeline approvalPipeline = approvalPipelines.get(approvalPipelines.size() - 1);

        if(approvalPipeline == null)
            return 0;

        return approvalPipeline.getId();
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
