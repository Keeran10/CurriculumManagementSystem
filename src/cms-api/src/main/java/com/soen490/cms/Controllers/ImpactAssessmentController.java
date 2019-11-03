package com.soen490.cms.Controllers;

import com.soen490.cms.Services.ImpactAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImpactAssessmentController {

    @Autowired
    ImpactAssessmentService impactAssessmentService;

    @GetMapping(value = "/ImpactAssessment")
    public Map<String, Object> getImpactAssessment(@RequestParam int requestId){
        return impactAssessmentService.getAssessment(requestId);
    }
}
