package com.soen490.cms.Services;

import com.soen490.cms.Models.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    @Autowired
    SearchService searchService;

    public Map<String, Object> getCourseImpact(Request request){
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

        // Making Required Degree Impact Report
        Collection<Degree> courseRequiredDegrees = searchService.findDegreesByRequiredCourseId(course.getId());
        ArrayList<Map> updatedList = new ArrayList();
        ArrayList<Map> originalList = new ArrayList();
        for(Degree degree : courseRequiredDegrees){
            Map<String, Object> originalMap = new HashMap();
            originalMap.put(degree.getName(), degree.getCredits());
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totaCredits =  degree.getCredits() + course.getCredits();
            changedMap.put(degree.getName(), totaCredits);
            updatedList.add(changedMap);
        }
        Map<String, Object> map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("DegreeCourseRequiredImpact", map);

        // Making Elective Degree Impact Report
        Collection<Degree> courseElectiveDegrees = searchService.findDegreesByElectiveCourseId(course.getId());
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(Degree degree : courseElectiveDegrees){
            Map<String, Object> originalMap = new HashMap();
            originalMap.put(degree.getName(), degree.getCredits());
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totaCredits =  degree.getCredits() + course.getCredits();
            changedMap.put(degree.getName(), totaCredits);
            updatedList.add(changedMap);
        }
        map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("DegreeCourseElectiveImpact", map);

        // Making Program Impact Report
        Set<String> programCores = getAlldegreeRequirementsCores(courseRequiredDegrees);
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(String programCore : programCores){
            Map<String, Object> originalMap = new HashMap();
            double originalCredits = searchService.findCreditsTotalOfCoreProgram(programCore);
            originalMap.put(programCore, originalCredits);
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totalCredits =  originalCredits + course.getCredits();
            changedMap.put(programCore, totalCredits);
            updatedList.add(changedMap);
        }
        map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("ProgramImpact", map);

        responseReport.put("Name",course.getName());
        responseReport.put("Number",course.getNumber());
        responseReport.put("Course", course);
        responseReport.put("RequestType", request.getRequestType());
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
        // Making Required Degree Impact Report
        Collection<Degree> courseRequiredDegrees = searchService.findDegreesByRequiredCourseId(course.getId());
        ArrayList<Map> updatedList = new ArrayList();
        ArrayList<Map> originalList = new ArrayList();
        for(Degree degree : courseRequiredDegrees){
            Map<String, Object> originalMap = new HashMap();
            originalMap.put(degree.getName(), degree.getCredits());
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totaCredits =  degree.getCredits() - course.getCredits();
            changedMap.put(degree.getName(), totaCredits);
            updatedList.add(changedMap);
        }
        Map<String, Object> map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("DegreeCourseRequiredImpact", map);

        // Making Elective Degree Impact Report
        Collection<Degree> courseElectiveDegrees = searchService.findDegreesByElectiveCourseId(course.getId());
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(Degree degree : courseElectiveDegrees){
            Map<String, Object> originalMap = new HashMap();
            originalMap.put(degree.getName(), degree.getCredits());
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totaCredits =  degree.getCredits() - course.getCredits();
            changedMap.put(degree.getName(), totaCredits);
            updatedList.add(changedMap);
        }
        map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("DegreeCourseElectiveImpact", map);

        // Making Program Impact Report
        Set<String> programCores = getAlldegreeRequirementsCores(courseRequiredDegrees);
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(String programCore : programCores){
            Map<String, Object> originalMap = new HashMap();
            double originalCredits = searchService.findCreditsTotalOfCoreProgram(programCore);
            originalMap.put(programCore, originalCredits);
            originalList.add(originalMap);
            Map<String, Object> changedMap = new HashMap();
            double totalCredits =  originalCredits - course.getCredits();
            changedMap.put(programCore, totalCredits);
            updatedList.add(changedMap);
        }
        map = new HashMap();
        map.put("updated",updatedList);
        map.put("original",originalList);
        responseReport.put("ProgramImpact", map);

        responseMap.put("Courses",parentCourses);
        responseReport.put("Name",course.getName());
        responseReport.put("Number",course.getNumber());
        responseReport.put("Course",course);
        responseReport.put("RemovingFromParentCourses",responseMap);
        responseReport.put("RequestType",request.getRequestType());
        return responseReport;
    }

    private Map<String, Object> getCourseDiffReport(Course originalCourse, Course requestedCourse){
        Map<String, Object> finalResponseMap = new HashMap();

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
        // check description
        if(!(originalCourse.getDescription().equalsIgnoreCase(requestedCourse.getDescription())))
            responseMap.put("description", requestedCourse.getDescription());
        // check tutorial hour
        if(originalCourse.getTutorialHours() != requestedCourse.getTutorialHours())
            responseMap.put("tutorialHours", Double.toString(requestedCourse.getTutorialHours()));
        // check lab hour
        if(originalCourse.getLabHours() != requestedCourse.getLabHours())
            responseMap.put("labHours", Double.toString(requestedCourse.getLabHours()));
        // check lecture hour
        if(originalCourse.getLectureHours() != requestedCourse.getLectureHours())
            responseMap.put("lectureHours", Double.toString(requestedCourse.getLectureHours()));
        // check level
        if(originalCourse.getLevel() != requestedCourse.getLevel())
            responseMap.put("level", Integer.toString(requestedCourse.getLevel()));


        // check preRequisites
        Map<String, Object> preReqRemovedMap = requisitesCompare(originalCourse,requestedCourse);
        if(!(preReqRemovedMap.isEmpty()))
            responseMap.put("RequisitesRemoved", preReqRemovedMap);

        Map<String, Object> preReqAddedMap = requisitesCompare(requestedCourse, originalCourse);
        if(!(preReqAddedMap.isEmpty()))
            responseMap.put("RequisitesAdded", preReqAddedMap);


        finalResponseMap.put("CourseEdits", responseMap);
        finalResponseMap.put("DegreeCourseRequiredImpact",getRequiredCourseDegreeImpactUpdatedCourse(originalCourse,requestedCourse));
        finalResponseMap.put("DegreeCourseElectiveImpact",getElectiveCourseDegreeImpactUpdatedCourse(originalCourse,requestedCourse));
        finalResponseMap.put("OriginalCourse",originalCourse);
        finalResponseMap.put("ProgramImpact",getProgramImpactUpdatedCourse(originalCourse,requestedCourse));

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
                        double difference =  Math.abs(originalCourse.getCredits() - requestedCourse.getCredits());
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

    private Map<Object, Object> getProgramImpactUpdatedCourse( Course originalCourse, Course requestedCourse){
        Map<Object, Object> responseMap = new HashMap();
        Collection<Degree> originalCourseRequiredDegrees = searchService.findDegreesByRequiredCourseId(originalCourse.getId());
        Collection<Degree> targetCourseRequiredDegrees = searchService.findDegreesByRequiredCourseId(requestedCourse.getId());
        Set<String> originalCores = getAlldegreeRequirementsCores(originalCourseRequiredDegrees);
        Set<String> requestedCores = getAlldegreeRequirementsCores(targetCourseRequiredDegrees);

        ArrayList<Map> updatedList = new ArrayList();

        ArrayList<Map> removedList = new ArrayList();
        ArrayList<Map> addedList = new ArrayList();
        ArrayList<Map> originalList = new ArrayList();

        for(String originalCore: originalCores){
            boolean notFound = true;
            for(String requestedCore: requestedCores){
                // When only the credits have changed in a core
                if(originalCore.equals(requestedCore)){
                    notFound = false;
                    if(originalCourse.getCredits() != requestedCourse.getCredits()){
                        double difference =  Math.abs(originalCourse.getCredits() - requestedCourse.getCredits());
                        double originalCredits = searchService.findCreditsTotalOfCoreProgram(originalCore);
                        double updatedCredits = originalCredits;
                        //if credits increased else credits decreased
                        if(originalCourse.getCredits() < requestedCourse.getCredits())
                            updatedCredits += difference;
                        else{
                            updatedCredits -= difference;
                        }
                        Map<String, Object> updatedMap = new HashMap();
                        updatedMap.put(originalCore, updatedCredits);
                        updatedList.add(updatedMap);
                        Map<String, Object> originalMap = new HashMap();
                        originalMap.put(originalCore, originalCredits);
                        originalList.add(originalMap);

                    }
                }
            }
            // if core not found in modified List then it was removed
            if(notFound){
                double originalCredits = searchService.findCreditsTotalOfCoreProgram(originalCore);
                double totalCredits = originalCredits - originalCourse.getCredits();
                Map<String, Object> removedMap = new HashMap();
                removedMap.put(originalCore, totalCredits);
                removedList.add(removedMap);
                Map<String, Object> originalMap = new HashMap();
                originalMap.put(originalCore, originalCredits);
                originalList.add(originalMap);
            }
        }

        // New course requirement for a degree
        for(String requestedCore: requestedCores){
            boolean notFound = true;
            for(String originalCore: originalCores){
                if(originalCore.equals(requestedCore)){
                    notFound = false;
                }
            }
            if(notFound){
                double originalCredits = searchService.findCreditsTotalOfCoreProgram(requestedCore);
                double totalCredits = originalCredits + requestedCourse.getCredits();
                Map<String, Object> addedMap = new HashMap();
                addedMap.put(requestedCore, totalCredits);
                addedList.add(addedMap);
                Map<String, Object> originalMap = new HashMap();
                originalMap.put(requestedCore, originalCredits);
                originalList.add(originalMap);
            }
        }

        responseMap.put("updated",updatedList);
        responseMap.put("removed",removedList);
        responseMap.put("added",addedList);
        responseMap.put("original",originalList);
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

    public Set<String> getAlldegreeRequirementsCores(Collection<Degree> requiredDegrees){
        Set<String> coreSet = new HashSet<String>();
        for(Degree degree: requiredDegrees){
            Collection<DegreeRequirement> requirements = degree.getDegreeRequirements();
            for(DegreeRequirement degreeRequirement: requirements){
                coreSet.add(degreeRequirement.getCore());
            }
        }
        return coreSet;
    }

    public void setServiceMock(SearchService course){
        searchService = course;
    }
}
