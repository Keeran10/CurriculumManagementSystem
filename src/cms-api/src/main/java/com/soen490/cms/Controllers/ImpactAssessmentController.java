package com.soen490.cms.Controllers;

import com.soen490.cms.Services.CourseService;
import com.soen490.cms.Services.ImpactAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImpactAssessmentController {

    @Autowired
    ImpactAssessmentService impactAssessmentService;

    @GetMapping(value = "/ImpactAssessment")
    public String getImpactAssessment(@RequestParam int requestId){
        return impactAssessmentService.getAssessment(requestId);
    }
}
