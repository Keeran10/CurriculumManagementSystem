package com.soen490.cms.Services;

import com.soen490.cms.Models.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    public String getCourseImpact(Request request){
        return "Its a course";
    }
}
