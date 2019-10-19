package com.soen490.cms.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImpactAssessmentController {

    @GetMapping(value = "/CourseImpactAssessment")
    public String getCourseImpactAssessment(@RequestParam int requestId){
        return "Your request id is: " + requestId;
    }
}
