package com.soen490.cms.Services;

import com.soen490.cms.Models.Request;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ImpactAssessmentService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ImpactAssessmentCourseService impactAssessmentCourseService;

    public Map<String, Object> getAssessment(int requestId){
        Map<String, Object> responseMap = new HashMap();
        Request request = requestRepository.findByRequestId(requestId);
        log.info("Looking for the Request with: "+ requestId );

        if(request == null) {
            responseMap.put("error","Request does not exist");
            return responseMap; }

        else{
            log.info("Content of Request "+ request );
            switch (request.getTargetType()){
                //TODO case 1: return "it's a program change request";
                case 2: return impactAssessmentCourseService.getCourseImpact(request);
                default: {
                    responseMap.put("error","invalid Target Type in the Request");
                    return responseMap; }
            }
        }
    }
}
