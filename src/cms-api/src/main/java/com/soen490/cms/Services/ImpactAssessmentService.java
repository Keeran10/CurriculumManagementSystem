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

    public String getAssessment(int requestId){
        System.err.println("Hello Request ID is: " + requestId);
        Request request = requestRepository.findByRequestId(requestId);
        System.err.println("Here"+ request);
        return "Your request Id is:" + requestId;
    }
}
