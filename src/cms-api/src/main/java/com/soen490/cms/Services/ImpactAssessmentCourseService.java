package com.soen490.cms.Services;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    @Autowired
    CourseService courseService;

    public String getCourseImpact(Request request){
        switch (request.getRequestType()){
            case 1: return "Course Creation Impact Report";//courseCreationImpact();
            case 2: return courseEditedImpact(request);
            case 3: return "Course Deletion Impact Report";//courseRemovalImpact();
            default: return "wrong course Request Type";
        }
    }
    private String courseEditedImpact(Request request){
        Course originalCourse = courseService.findCourseById(request.getOriginalId());
        if(originalCourse == null){
            return "Original course not referenced";
        }
        else{
            Course requestedCourse = courseService.findCourseById(request.getTargetId());
            getCourseDiff(originalCourse, requestedCourse);
            return "course editted";
        }
    }
    private String getCourseDiff(Course originalCourse, Course requestedCourse){
        
    }
}
