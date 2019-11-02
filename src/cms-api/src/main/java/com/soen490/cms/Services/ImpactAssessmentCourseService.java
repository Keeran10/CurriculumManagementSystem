package com.soen490.cms.Services;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Degree;
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
        // check description
        if(!(originalCourse.getDescription().equalsIgnoreCase(requestedCourse.getDescription())))
            responseMap.put("Description", requestedCourse.getDescription());

        // check preRequisites
        Map<String, Object> preReqRemovedMap = requisitesCompare(originalCourse,requestedCourse);
        if(!(preReqRemovedMap.isEmpty()))
            responseMap.put("RequisitesRemoved", preReqRemovedMap);

        Map<String, Object> preReqAddedMap = requisitesCompare(requestedCourse, originalCourse);
        if(!(preReqAddedMap.isEmpty()))
            responseMap.put("RequisitesAdded", preReqAddedMap);

        Map<String, Object> programResponseMap = new HashMap();
        if(originalCourse.getProgram().getId() != requestedCourse.getProgram().getId()){
            programResponseMap.put("original", originalCourse.getProgram().getName());
            programResponseMap.put("change", requestedCourse.getProgram().getName());
        }


        finalResponseMap.put("CourseEdits", responseMap);
        finalResponseMap.put("DegreeCourseRequiredImapct",getRequiredCourseDegreeImpactUpdatedCourse(originalCourse,requestedCourse));
        finalResponseMap.put("DegreeCourseElectiveImpact",getElectiveCourseDegreeImpactUpdatedCourse(originalCourse,requestedCourse));
        finalResponseMap.put("OriginalCourse",originalCourse);
        finalResponseMap.put("ProgramImpact",programResponseMap);

        return finalResponseMap;
    }

    private Map<Object, Object> getRequiredCourseDegreeImpactUpdatedCourse(Course originalCourse,Course requestedCourse){
        Map<Object, Object> responseMap = new HashMap();

        Collection<Degree> originalCourseRequiredDegrees = searchService.findDegreesByRequiredCourseId(originalCourse.getId());
        Collection<Degree> targetCourseRequiredDegrees = searchService.findDegreesByRequiredCourseId(requestedCourse.getId());

        ArrayList<Map> updatedList = new ArrayList();

        ArrayList<Map> removedList = new ArrayList();
        ArrayList<Map> addedList = new ArrayList();
        ArrayList<Map> originalList = new ArrayList();

        for(Degree originalReqdegree: originalCourseRequiredDegrees){
            boolean notFound = true;
            for(Degree requestedReqDegree: targetCourseRequiredDegrees){
                // When only the credits have changed in a required degree
                if(originalReqdegree.getId() == requestedReqDegree.getId()){
                    notFound = false;
                    if(originalCourse.getCredits() != requestedCourse.getCredits()){
                        double difference = + Math.abs(originalCourse.getCredits() - requestedCourse.getCredits());
                        double totalCredits = originalReqdegree.getCredits();
                        //if credits increased else credits decreased
                        if(originalCourse.getCredits() < requestedCourse.getCredits())
                            totalCredits += difference;
                        else{
                            totalCredits -= difference;
                        }
                        Map<String, Object> updatedMap = new HashMap();
                        updatedMap.put(originalReqdegree.getName(), totalCredits);
                        updatedList.add(updatedMap);
                        Map<String, Object> originalMap = new HashMap();
                        originalMap.put(originalReqdegree.getName(), originalReqdegree.getCredits());
                        originalList.add(originalMap);

                    }
                }
            }
            // if degree not found in original List then it was removed
            if(notFound){
                double totalCredits = originalReqdegree.getCredits() - originalCourse.getCredits();
                Map<String, Object> removedMap = new HashMap();
                removedMap.put(originalReqdegree.getName(), totalCredits);
                removedList.add(removedMap);
                Map<String, Object> originalMap = new HashMap();
                originalMap.put(originalReqdegree.getName(), originalReqdegree.getCredits());
                originalList.add(originalMap);
            }
        }

        // New course requirement for a degree
        for(Degree requestedReqDegree: targetCourseRequiredDegrees){
            boolean notFound = true;
            for(Degree originalReqdegree: originalCourseRequiredDegrees){
                if(originalReqdegree.getId() == requestedReqDegree.getId()) {
                    notFound = false;
                    }
                }
            if(notFound){
                double totalCredits = requestedReqDegree.getCredits() + requestedCourse.getCredits();
                Map<String, Object> addedMap = new HashMap();
                addedMap.put(requestedReqDegree.getName(), totalCredits);
                addedList.add(addedMap);
                Map<String, Object> originalMap = new HashMap();
                originalMap.put(requestedReqDegree.getName(), requestedReqDegree.getCredits());
                originalList.add(originalMap);
            }
        }

        responseMap.put("updated",updatedList);
        responseMap.put("removed",removedList);
        responseMap.put("added",addedList);
        responseMap.put("original",originalList);
        return responseMap;
    }

    private Map<Object, Object> getElectiveCourseDegreeImpactUpdatedCourse(Course originalCourse,Course requestedCourse) {
        Map<Object, Object> responseMap = new HashMap();

        Collection<Degree> originalCourseRequiredDegrees = searchService.findDegreesByElectiveCourseId(originalCourse.getId());
        Collection<Degree> targetCourseRequiredDegrees = searchService.findDegreesByElectiveCourseId(requestedCourse.getId());

        ArrayList removedList = new ArrayList();
        ArrayList addedList = new ArrayList();
        // List of Degrees elective is removed from
        for(Degree originalReqdegree: originalCourseRequiredDegrees) {
            boolean notFound = true;
            for (Degree requestedReqDegree : targetCourseRequiredDegrees) {
                // When only the credits have changed in a required degree
                if (originalReqdegree.getId() == requestedReqDegree.getId()) {
                    notFound = false;
                }
            }
            if(notFound){
                removedList.add(originalReqdegree.getName());
            }
        }
        // List of Degrees elective is added to
        for (Degree requestedReqDegree : targetCourseRequiredDegrees) {
            boolean notFound = true;
            for(Degree originalReqdegree: originalCourseRequiredDegrees) {
                // When only the credits have changed in a required degree
                if (originalReqdegree.getId() == requestedReqDegree.getId()) {
                    notFound = false;
                }
            }
            if(notFound){
                addedList.add(requestedReqDegree.getName());
            }
        }

        responseMap.put("removed", removedList);
        responseMap.put("added", addedList);
        return responseMap;
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
