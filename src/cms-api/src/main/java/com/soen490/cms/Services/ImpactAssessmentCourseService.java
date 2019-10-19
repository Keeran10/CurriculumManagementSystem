package com.soen490.cms.Services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Request;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
           return getDiffReport(originalCourse, requestedCourse);
        }
    }

    private String getDiffReport(Course originalCourse, Course requestedCourse){
        String report= "";
        report = "{\"courseName\":" + originalCourse.getName()
                +",\"courseNumber\":" + originalCourse.getNumber()
                +",\"changed\":";
        report += getCourseDiff(originalCourse, requestedCourse);
        report += "}";
        return report;
    }

    private String getCourseDiff(Course originalCourse, Course requestedCourse){
        String courseChanges = "";
        Map<String, String> elements = new HashMap();
        // check name
        if(!(originalCourse.getName().equalsIgnoreCase(requestedCourse.getName())))
            elements.put("name", requestedCourse.getName());
        // check number
        if(originalCourse.getNumber() != requestedCourse.getNumber())
            elements.put("number", Integer.toString(requestedCourse.getNumber()));
        // check credit
        if(originalCourse.getCredits() != requestedCourse.getCredits())
            elements.put("credits", Double.toString(requestedCourse.getCredits()));
        // check title
        if(!(originalCourse.getTitle().equalsIgnoreCase(requestedCourse.getTitle())))
            elements.put("title", requestedCourse.getTitle());
        // check tutorial time
        if(originalCourse.getTutorialHours() != requestedCourse.getTutorialHours())
            elements.put("tutorial_hours", Double.toString(requestedCourse.getTutorialHours()));
        // check lab time
        if(originalCourse.getLabHours() != requestedCourse.getLabHours())
            elements.put("lab_hours", Double.toString(requestedCourse.getLabHours()));
        // check lecture time
        if(originalCourse.getLectureHours() != requestedCourse.getLectureHours())
            elements.put("lecture_hours", Double.toString(requestedCourse.getLectureHours()));
        // check description
        if(!(originalCourse.getDescription().equalsIgnoreCase(requestedCourse.getDescription())))
            elements.put("description", requestedCourse.getDescription());
        // check level
        if(originalCourse.getLevel() != requestedCourse.getLevel())
            elements.put("level", Integer.toString(requestedCourse.getLevel()));
        // check preRequisites
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            courseChanges = objectMapper.writeValueAsString(elements);
            System.out.println("json = " + courseChanges);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return courseChanges;
    }



}
