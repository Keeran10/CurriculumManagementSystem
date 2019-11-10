package com.soen490.cms.Controllers;

import com.soen490.cms.Services.ImpactAssessmentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImpactAssessmentController {

    @Autowired
    ImpactAssessmentService impactAssessmentService;


    /**
     * endpoint of: /ImpactAssessment
     * takes as parameter the request Id
     *
     * @param requestId
     * @return Map<String, Object> Impact report object
     */
    @GetMapping(value = "/ImpactAssessment")
    public Map<String, Object> getImpactAssessment(@RequestParam int requestId){
        log.info("Getting the Impact of Request Package: Id = ", requestId);
        return impactAssessmentService.getAssessment(requestId);
    }
}
