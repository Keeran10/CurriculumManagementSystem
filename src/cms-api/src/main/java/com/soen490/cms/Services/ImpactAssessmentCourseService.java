// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package com.soen490.cms.Services;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.SectionRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class ImpactAssessmentCourseService {

    @Autowired
    SearchService searchService;
    @Autowired
    private SectionRepository sectionRepository;

    // Object request causes trouble. Methods have been refactored to accept other parameters to avoid creating
    // unsaved request objects.
    //----------------------------------------------------------------------------
    public Map<String, Object> getCourseImpact2(Course originalCourse, Course requestedCourse, int requestType){
        //log.info("Getting course Impact for Request Package: ", request);
        Map<String, Object> responseMap = new HashMap();
        switch (requestType){
            //case 1: return courseCreationImpactReport2(request);
            case 2: return courseEditedImpact2(originalCourse, requestedCourse, requestType);
            //case 3: return courseRemovalImpactReport2(request);
            default: {
                responseMap.put("error","wrong course Request Type");
                return responseMap;
            }
        }
    }

    private Map<String, Object> courseEditedImpact2(Course originalCourse, Course requestedCourse, int requestType){

        if(originalCourse == null){
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("error","Original course not referred in request");
            log.info("Impact Report Error for course Update Request: Original course does not exist");
            return responseMap;
        }
        else{
            Map<String, Object> responseMap = getCourseDiffReport(originalCourse, requestedCourse);
            responseMap.put("RequestType", requestType);
            return responseMap;
        }
    }
    //----------------------------------------------------------------------------
    /**
     * Based on the Course Request type gets the Impact report
     * case 1: Course Creation impact
     * case 2: Course Edit impact
     * case 3: Course Removale impact
     *
     * @param request
     * @return Map<String, Object> Impact report object
     */
    public Map<String, Object> getCourseImpact(Request request){
        log.info("Getting course Impact for Request Package: ", request);
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

    /**
     * Finds the impact of the new course creation
     * Effect on Degrees if the course is Required
     * Effect on Core Programs if the course is Required
     * Effect on Degrees if the course is an Elective
     *
     * @param request
     * @return Map<String, Object> Impact report object
     */
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

        // Making Program Impact Report
        Collection<String> programCores = searchService.findPorgramCoreCourseId(course.getId());
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(String programCore : programCores){
            Map<String, Object> originalMap = new HashMap();
            Double originalCredits = searchService.findCreditsTotalOfCoreProgram(programCore);

            if(originalCredits == null)
                originalCredits = (double) 0;

            if(!(programCore.endsWith(" Electives"))) {
                originalMap.put(programCore, originalCredits);
                originalList.add(originalMap);
            }
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
        responseReport.put("Sections",sectionReport(course));
        responseReport.put("RequestType", request.getRequestType());
        log.info("Impact Report for a course creation: ", responseReport);
        return responseReport;
    }

    /**
     *Finds the differences of course requirements between two courses
     *
     *
     * @param request
     * @return Map<String, Object> Impact report object
     */
    private Map<String, Object> courseEditedImpact(Request request){
        Course originalCourse = searchService.findCourseById(request.getOriginalId());
        if(originalCourse == null){
            Map<String, Object> responseMap = new HashMap();
            responseMap.put("error","Original course not referred in request");
            log.info("Impact Report Error for course Update Request: Original course does not exist");
            return responseMap;
        }
        else{
            Course requestedCourse = searchService.findCourseById(request.getTargetId());
            Map<String, Object> responseMap = getCourseDiffReport(originalCourse, requestedCourse);
            responseMap.put("RequestType",request.getRequestType());
            return responseMap;
        }
    }

    /**
     * Finds the impact of the new course creation
     * Effect on Degrees if the course is Required
     * Effect on Core Programs if the course is Required
     * Effect on Degrees if the course is an Elective
     * Effects on parent courses
     *
     * @param request
     * @return Map<String, Object> Impact report object
     */
    private Map<String,Object> courseRemovalImpactReport(Request request){
        Map<String, Object> responseReport = new HashMap();
        // used to be get targetId() which is wrong as course removals do not have any target_ids.
        Course course = searchService.findCourseById(request.getOriginalId());
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

        // Making Program Impact Report
        Collection<String> programCores = searchService.findPorgramCoreCourseId(course.getId());
        updatedList = new ArrayList();
        originalList = new ArrayList();
        for(String programCore : programCores){
            Map<String, Object> originalMap = new HashMap();
            Double originalCredits = searchService.findCreditsTotalOfCoreProgram(programCore);

            if(originalCredits == null)
                originalCredits = (double) 0;
            if(!(programCore.endsWith(" Electives"))){
                originalMap.put(programCore, originalCredits);
                originalList.add(originalMap);
            }
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
        responseReport.put("Sections",sectionReport(course));
        log.info("Impact Report for a course Removal: ", responseReport);
        return responseReport;
    }

    /**
     * Finds the impact of the updated course
     * Effect on Degrees if the course is Required
     * Effect on Core Programs if the course is Required
     * Effect on Degrees if the course is an Elective
     * Effect on other Elective courses
     * Changes on other electives
     *
     * @param originalCourse, requestedCourse
     * @return Map<String, Object> Impact report object
     */
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
        finalResponseMap.put("OriginalCourse",originalCourse);
        finalResponseMap.put("ProgramImpact",getProgramImpactUpdatedCourse(originalCourse,requestedCourse));
        finalResponseMap.put("Sections",sectionReport(requestedCourse));
        log.info("Impact Report for a course Update: ", finalResponseMap);
        return finalResponseMap;
    }

    /**
     * Finds the differences of required Degrees of an updated course and its original
     *
     * @param originalCourse, requestedCourse
     * @return Map<String, Object> Impact report object
     */
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
        log.info("Impact Report for a course Update on the Required Degrees: ", responseMap);
        return responseMap;
    }


    /**
     * Finds the differences of a core Program of an updated course and its original
     *
     * @param originalCourse, requestedCourse
     * @return Map<String, Object> Impact report object
     */
    private Map<Object, Object> getProgramImpactUpdatedCourse( Course originalCourse, Course requestedCourse){
        Map<Object, Object> responseMap = new HashMap();
        Collection<String> originalCourseProgramCore = searchService.findPorgramCoreCourseId(originalCourse.getId());
        Collection<String> targetCourseProgramCore = searchService.findPorgramCoreCourseId(requestedCourse.getId());

        ArrayList<Map> updatedList = new ArrayList();
        ArrayList<Map> removedList = new ArrayList();
        ArrayList<Map> addedList = new ArrayList();
        ArrayList<Map> originalList = new ArrayList();

        for(String originalCore: originalCourseProgramCore){
            boolean notFound = true;
            for(String requestedCore: targetCourseProgramCore){
                // When only the credits have changed in a core
                if(originalCore.equals(requestedCore)){
                    notFound = false;
                    if(originalCourse.getCredits() != requestedCourse.getCredits()){
                        double difference =  Math.abs(originalCourse.getCredits() - requestedCourse.getCredits());
                        Double originalCredits = searchService.findCreditsTotalOfCoreProgram(originalCore);
                        double originalCredit = 0.0;
                        double updatedCredits = 0.0;
                       if(originalCredits != null) {
                           originalCredit = originalCredits.doubleValue();
                            updatedCredits = originalCredit;
                           //if credits increased else credits decreased
                           if (originalCourse.getCredits() < requestedCourse.getCredits())
                               updatedCredits += difference;
                           else {
                               updatedCredits -= difference;
                           }
                       }
                       if(!(originalCore.endsWith(" Electives"))) {
                           Map<String, Object> updatedMap = new HashMap();
                           updatedMap.put(originalCore, updatedCredits);
                           updatedList.add(updatedMap);
                           Map<String, Object> originalMap = new HashMap();
                           originalMap.put(originalCore, originalCredit);
                           originalList.add(originalMap);
                       }

                    }
                }
            }
            // if core not found in modified List then it was removed
            if(notFound){
                Double originalCredits = searchService.findCreditsTotalOfCoreProgram(originalCore);

                if(originalCredits == null)
                    originalCredits = (double) 0;

                double totalCredits = originalCredits - originalCourse.getCredits();
                Map<String, Object> removedMap = new HashMap();
                removedMap.put(originalCore, totalCredits);
                removedList.add(removedMap);
                if(!(originalCore.endsWith(" Electives"))) {
                    Map<String, Object> originalMap = new HashMap();
                    originalMap.put(originalCore, originalCredits);
                    originalList.add(originalMap);
                }
            }
        }

        // course is part of a new program core
        for(String requestedCore: targetCourseProgramCore){
            boolean notFound = true;
            for(String originalCore: originalCourseProgramCore){
                if(originalCore.equals(requestedCore)){
                    notFound = false;
                }
            }
            if(notFound){
                Double originalCredits = searchService.findCreditsTotalOfCoreProgram(requestedCore);
                double totalCredits = 0.0;
                double originalCredit = 0.0;
                if(originalCredits != null){
                    originalCredit = originalCredits.doubleValue();
                    totalCredits = originalCredits.doubleValue() + requestedCourse.getCredits();
                }
                Map<String, Object> addedMap = new HashMap();
                addedMap.put(requestedCore, totalCredits);
                addedList.add(addedMap);
                if(!(requestedCore.endsWith(" Electives"))) {
                    Map<String, Object> originalMap = new HashMap();
                    originalMap.put(requestedCore, originalCredit);
                    originalList.add(originalMap);
                }
            }
        }

        responseMap.put("updated",updatedList);
        responseMap.put("removed",removedList);
        responseMap.put("added",addedList);
        responseMap.put("original",originalList);
        log.info("Impact Report for a course Update on Program Cores: ", responseMap);
        return responseMap;
    }

    /**
     * Finds the differences or requisites between two courses
     *
     * @param originalCourse, requestedCourse
     * @return Map<String, Object> Impact report object
     */
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

    private Map<String, Object> sectionReport(Course course){
        Map<String, Object> responseMap = new HashMap();

        List<String> sectionCourses = sectionRepository.findByTarget("course", course.getId());
        List<String> sectionDegree = null;
        List<String> sectionDepartment = null;

        if(course.getDegreeRequirements()!= null) {
            if(course.getDegreeRequirements().size() != 0) {
                sectionDegree = sectionRepository.findByTarget("degree", course.getDegreeRequirements().get(0).getDegree().getId());
            }
        }
        if(course.getProgram()!= null) {
            sectionDepartment = sectionRepository.findByTarget("department", course.getProgram().getDepartment().getId());
        }

        responseMap.put("course",sectionCourses);
        responseMap.put("degree",sectionDegree);
        responseMap.put("department",sectionDepartment);

        return  responseMap;
    }

    /**
     * Inputs a mock object for Search Service to use in Junit Tests
     *
     * @param course, section
     */
    public void setServiceMock(SearchService course, SectionRepository section){
        searchService = course;
        sectionRepository = section;
    }
}
