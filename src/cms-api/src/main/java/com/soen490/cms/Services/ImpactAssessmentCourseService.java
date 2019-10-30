package com.soen490.cms.Services;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Requisite;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    @Autowired
    SearchService searchService;

    public Map<String, Object>  getCourseImpact(Request request){
        Map<String, Object> responseMap = new HashMap();
        switch (request.getRequestType()){
            case 1: return courseCreationImpactReport(request);
            case 2: return courseEditedImpact(request);
            case 3: return courseRemovalImpactReport(request);
            default: {
                responseMap.put("error","wrong course Request Type");
                return responseMap;
            }
        }
    }
    private Map<String, Object> courseCreationImpactReport(Request request){

        Map<String, Object> responseReport = new HashMap();
        Course course = searchService.findCourseById(request.getTargetId());

        Collection<Requisite> coursesInReference = course.getRequisites();
        Map<String, Object> responseMap = new HashMap();
        List<String> requisiteCourses = new ArrayList();
        for(Requisite requisite : coursesInReference){
            requisiteCourses.add(requisite.getName()+" " + requisite.getNumber());
        }
        responseMap.put("Courses", requisiteCourses);
        responseReport.put("Name", course.getName());
        responseReport.put("Number", course.getNumber());
        responseReport.put("RequestType", request.getRequestType());
        responseReport.put("CoursesInRequisites", responseMap);
        return responseReport;
    }

    private Map<String, Object> courseEditedImpact(Request request){
        Course originalCourse = searchService.findCourseById(request.getOriginalId());
        if(originalCourse == null){
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("error","Original course not referred in request");
            return responseMap;
        }
        else{
            Course requestedCourse = searchService.findCourseById(request.getTargetId());
            Map<String, Object> responseMap = getCourseDiffReport(originalCourse, requestedCourse);
            responseMap.put("RequestType",request.getRequestType());
            return responseMap;
        }
    }

    private Map<String,Object> courseRemovalImpactReport(Request request){
        Map<String, Object> responseReport = new HashMap();
        Course course = searchService.findCourseById(request.getTargetId());
        int courseId = course.getId();

        Collection<Requisite> coursesInReference = searchService.findAllOccurrencesOfCourseAsRequisite(courseId);

        Map<String, Object> responseMap = new HashMap();
        List<String> parentCourses = new ArrayList();
        for(Requisite requisite : coursesInReference){
            Course parentCourse = requisite.getCourse();
            parentCourses.add(parentCourse.getName()+" " +parentCourse.getNumber());
        }
        responseMap.put("Courses",parentCourses);
        responseReport.put("Name",course.getName());
        responseReport.put("Number",course.getNumber());
        responseReport.put("RemovingFromParentCourses",responseMap);
        responseReport.put("RequestType",request.getRequestType());
        return responseReport;
    }

    private Map<String, Object>  getCourseDiffReport(Course originalCourse, Course requestedCourse){
        Map<String, Object> finalResponseMap = new HashMap();
        finalResponseMap.put("CourseName",originalCourse.getName());
        finalResponseMap.put("CourseNumber",Integer.toString(originalCourse.getNumber()));

        Map<String, Object> responseMap = new HashMap();
        // check name
        if(!(originalCourse.getName().equalsIgnoreCase(requestedCourse.getName())))
            responseMap.put("Name", requestedCourse.getName());
        // check number
        if(originalCourse.getNumber() != requestedCourse.getNumber())
            responseMap.put("Number", Integer.toString(requestedCourse.getNumber()));
        // check credit
        if(originalCourse.getCredits() != requestedCourse.getCredits())
            responseMap.put("Credits", Double.toString(requestedCourse.getCredits()));
        // check title
        if(!(originalCourse.getTitle().equalsIgnoreCase(requestedCourse.getTitle())))
            responseMap.put("Title", requestedCourse.getTitle());
        // check tutorial time
        if(originalCourse.getTutorialHours() != requestedCourse.getTutorialHours())
            responseMap.put("Tutorial_hours", Double.toString(requestedCourse.getTutorialHours()));
        // check lab time
        if(originalCourse.getLabHours() != requestedCourse.getLabHours())
            responseMap.put("Lab_hours", Double.toString(requestedCourse.getLabHours()));
        // check lecture time
        if(originalCourse.getLectureHours() != requestedCourse.getLectureHours())
            responseMap.put("Lecture_hours", Double.toString(requestedCourse.getLectureHours()));
        // check description
        if(!(originalCourse.getDescription().equalsIgnoreCase(requestedCourse.getDescription())))
            responseMap.put("Description", requestedCourse.getDescription());
        // check level
        if(originalCourse.getLevel() != requestedCourse.getLevel())
            responseMap.put("Level", Integer.toString(requestedCourse.getLevel()));

        // check preRequisites
        Map<String, Object> preReqRemovedMap = requisitesCompare(originalCourse,requestedCourse);
        if(!(preReqRemovedMap.isEmpty()))
            responseMap.put("RequisitesRemoved", preReqRemovedMap);

        Map<String, Object> preReqAddedMap = requisitesCompare(requestedCourse, originalCourse);
        if(!(preReqAddedMap.isEmpty()))
            responseMap.put("RequisitesAdded", preReqAddedMap);

        finalResponseMap.put("CourseChanges", responseMap);

        return finalResponseMap;
    }

    private Map<String, Object> requisitesCompare(Course originalCourse, Course requestedCourse){
        Collection<Requisite> originalRequisites = originalCourse.getRequisites();
        Collection<Requisite> requestedRequisites = requestedCourse.getRequisites();
        Map<String, Object> responseMap = new HashMap();
        List<String> coursesList = new ArrayList();

        for(Requisite original : originalRequisites){
            String oldName = original.getName();
            int oldNumber = original.getNumber();
            boolean exist = false;

            for(Requisite changed : requestedRequisites){
                String newName = changed.getName();
                int newNumber = changed.getNumber();
                if((oldName.equalsIgnoreCase(newName))&&(oldNumber == newNumber)){
                    exist = true;
                }
            }
            if(!exist){
                coursesList.add(oldName +" "+ oldNumber);
            }
        }

        if(coursesList.isEmpty()){
            return responseMap;
        }
        else {
            responseMap.put("Courses", coursesList);
            return responseMap;
        }
    }
    public void setServiceMock(SearchService course){
        searchService = course;
    }
}
