package com.soen490.cms.Services;

import com.soen490.cms.Models.Request;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ImpactAssessmentService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ImpactAssessmentCourseService impactAssessmentCourseService;

    public String getAssessment(int requestId){
        Request request = requestRepository.findByRequestId(requestId);
        log.info("Looking for the Request with: "+ requestId );

        if(request == null){
            return "Request does not exisit";
        }
        else{
            log.info("Content of Request "+ request );
            switch (request.getTargetType()){
                case 1: return "it's a program change request";
                case 2: return impactAssessmentCourseService.getCourseImpact(request);
                default: return "invalid Target Type in the Request";
            }
        }
    }
}
