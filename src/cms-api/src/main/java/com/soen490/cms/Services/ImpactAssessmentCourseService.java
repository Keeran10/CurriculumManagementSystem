package com.soen490.cms.Services;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Requisite;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    @Autowired
    CourseService courseService;

    public Map<String, Object>  getCourseImpact(Request request){
        Map<String, Object> responseMap = new HashMap();
        switch (request.getRequestType()){

            //TODO case 1: return courseCreationImpact(request);
            case 2: return courseEditedImpact(request);
            case 3: return courseRemovalImpact(request);
            default: {
                responseMap.put("error","wrong course Request Type");
                return responseMap;
            }
        }
    }
    private Map<String, Object>  courseEditedImpact(Request request){
        Course originalCourse = courseService.findCourseById(request.getOriginalId());
        if(originalCourse == null){
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("error","Original course not referred in request");
            return responseMap;
        }
        else{
            Course requestedCourse = courseService.findCourseById(request.getTargetId());
           return getCourseDiffReport(originalCourse, requestedCourse);
        }
    }
    private Map<String,Object> courseRemovalImpact(Request request){
        Course course = courseService.findCourseById(request.getTargetId());
        int courseId = course.getId();
        Collection<Requisite> coursesInRefference = courseService.findAllOccurancesOfCourseAsRequisite(courseId);
        
        for(Requisite requisite : coursesInRefference){
            System.out.println(requisite.getCourse());
        }

        return null;
    }

    private Map<String, Object>  getCourseDiffReport(Course originalCourse, Course requestedCourse){
        Map<String, Object> finalResponseMap = new HashMap();
        finalResponseMap.put("CourseName",originalCourse.getName());
        finalResponseMap.put("courseNumber",Integer.toString(originalCourse.getNumber()));

        Map<String, Object> responseMap = new HashMap();
        // check name
        if(!(originalCourse.getName().equalsIgnoreCase(requestedCourse.getName())))
            responseMap.put("name", requestedCourse.getName());
        // check number
        if(originalCourse.getNumber() != requestedCourse.getNumber())
            responseMap.put("number", Integer.toString(requestedCourse.getNumber()));
        // check credit
        if(originalCourse.getCredits() != requestedCourse.getCredits())
            responseMap.put("credits", Double.toString(requestedCourse.getCredits()));
        // check title
        if(!(originalCourse.getTitle().equalsIgnoreCase(requestedCourse.getTitle())))
            responseMap.put("title", requestedCourse.getTitle());
        // check tutorial time
        if(originalCourse.getTutorialHours() != requestedCourse.getTutorialHours())
            responseMap.put("tutorial_hours", Double.toString(requestedCourse.getTutorialHours()));
        // check lab time
        if(originalCourse.getLabHours() != requestedCourse.getLabHours())
            responseMap.put("lab_hours", Double.toString(requestedCourse.getLabHours()));
        // check lecture time
        if(originalCourse.getLectureHours() != requestedCourse.getLectureHours())
            responseMap.put("lecture_hours", Double.toString(requestedCourse.getLectureHours()));
        // check description
        if(!(originalCourse.getDescription().equalsIgnoreCase(requestedCourse.getDescription())))
            responseMap.put("description", requestedCourse.getDescription());
        // check level
        if(originalCourse.getLevel() != requestedCourse.getLevel())
            responseMap.put("level", Integer.toString(requestedCourse.getLevel()));

        // check preRequisites
        String preReqRemoved = "";
        Map<String, Object> preReqRemovedMap = preReqCompare(originalCourse,requestedCourse);
        if(!(preReqRemovedMap.isEmpty()))
            responseMap.put("PreReq_removed", preReqRemovedMap);

        String preReqAdded = "";
        Map<String, Object> preReqAddedMap = preReqCompare(requestedCourse, originalCourse);
        if(!(preReqAddedMap.isEmpty()))
            responseMap.put("PreReq_added", preReqAddedMap);


        if(responseMap.keySet().size() > 2){
            finalResponseMap.put("Course_Changes", responseMap);}
        else{
            finalResponseMap.put("Course_Changes", responseMap);}

        return finalResponseMap;
    }

    private Map<String, Object> preReqCompare(Course originalCourse, Course requestedCourse){
        Collection<Requisite> originalRequisites = originalCourse.getRequisites();
        Collection<Requisite> requestedRequisites = requestedCourse.getRequisites();
        Map<String, Object> responseMap = new HashMap();
        for(Requisite original : originalRequisites){
            Course oldCourse = courseService.findCourseById(original.getRequisiteCourseId());
            String oldName = oldCourse.getName();
            int oldNumber = oldCourse.getNumber();
            boolean exist = false;

            for(Requisite changed : requestedRequisites){
                Course newCourse = courseService.findCourseById(changed.getRequisiteCourseId());
                String newName = newCourse.getName();
                int newNumber = newCourse.getNumber();
                if((oldName.equalsIgnoreCase(newName))&&(oldNumber == newNumber)){
                    exist = true;
                }
            }
            if(!exist){
                responseMap.put("Course",oldName +" "+ oldNumber);
            }
        }
      return responseMap;
    }

}
