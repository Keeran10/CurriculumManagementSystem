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
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.DegreeRequirementRepository;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class ImpactAssessmentService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    ImpactAssessmentCourseService impactAssessmentCourseService;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DegreeRequirementRepository degreeRequirementRepository;

    public Map<String, Object> getCourseImpact(String requestForm) throws JSONException {

        log.info("Json received: " + requestForm);

        JSONObject json = new JSONObject(requestForm);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId((Integer) course.get("id"));

        Course original = null;

        if(!o.isEmpty())
            original = o.get(0);
        else return null;

        Course c = new Course();

        c.setName((String) course.get("name"));
        c.setNumber(Integer.valueOf((String) course.get("number")));
        c.setTitle((String) course.get("title"));
        c.setCredits(Double.valueOf(String.valueOf(course.get("credits"))));
        c.setDescription((String) course.get("description"));
        c.setLevel((Integer) course.get("level"));
        c.setNote((String) course.get("note"));
        c.setLabHours(Double.valueOf(String.valueOf(course.get("labHours"))));
        c.setTutorialHours(Double.valueOf(String.valueOf(course.get("tutorialHours"))));
        c.setLectureHours(Double.valueOf(String.valueOf(course.get("lectureHours"))));
        c.setIsActive(0);
        c.setProgram(original.getProgram());

        // this needs to be deleted upon successful impact.
        courseRepository.save(c);

        // Degree Requirements
        ArrayList<DegreeRequirement> list = new ArrayList<>();

        for(DegreeRequirement dr : original.getDegreeRequirements()){

            DegreeRequirement cdr = new DegreeRequirement();

            cdr.setCore(dr.getCore());
            cdr.setDegree(dr.getDegree());
            cdr.setCourse(c);

            degreeRequirementRepository.save(cdr);

            dr.getDegree().getDegreeRequirements().add(cdr);

            list.add(cdr);

        }
        c.setDegreeRequirements(list);

        // Requisites
        String pre = (String) courseExtras.get("prerequisites");
        String co = (String) courseExtras.get("corequisites");
        String anti = (String) courseExtras.get("antirequisites");
        String eq = (String) courseExtras.get("equivalents");

        String[] prerequisites = pre.split(";|\\,");
        String[] corequisites = co.split(";|\\,");
        String[] antirequisites = anti.split(";|\\,");
        String[] equivalents = eq.split(";|,|or");

        for(String prerequisite : prerequisites){

            prerequisite = prerequisite.trim();

            if(prerequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                if(prerequisite.startsWith("credits", 3)){
                    requisite.setName(prerequisite);
                    requisite.setNumber(0);
                }
                else{
                    requisite.setName(prerequisite.substring(0, 4).trim());
                    requisite.setNumber(Integer.parseInt(prerequisite.substring(4).trim()));
                }
                requisite.setType("prerequisite");
            }

        }
        for(String corequisite : corequisites){

            corequisite = corequisite.trim();

            if(corequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(corequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(corequisite.substring(4).trim()));
                requisite.setType("corequisite");
            }
        }
        for(String antirequisite : antirequisites){

            antirequisite = antirequisite.trim();

            if(antirequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(antirequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(antirequisite.substring(4).trim()));
                requisite.setType("antirequisite");
            }
        }
        for(String equivalent : equivalents){

            equivalent = equivalent.trim();

            if(equivalent.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(equivalent.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(equivalent.substring(4).trim()));
                requisite.setType("equivalent");
            }
        }

        Map<String, Object> impact =  getCourseAssessment(original, c, 2);

        for(DegreeRequirement dr : c.getDegreeRequirements()){
            degreeRequirementRepository.delete(dr);
        }
        courseRepository.delete(c);

        return impact;
    }
    /**
     * Based on the requestId gets the Impact statement of the specific report
     * case 1: Program Request type
     * case 2: Course Request type
     *
     * @param requestId
     * @return Map<String, Object> Json
     */
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

    // Use this to avoid creating unused request objects for client impact statements
    public Map<String, Object> getCourseAssessment(Course originalCourse, Course requestedCourse, int requestType){
        Map<String, Object> responseMap = new HashMap();
        //Request request = requestRepository.findByRequestId(requestId);
        //log.info("Looking for the Request with: "+ requestId );

        if(originalCourse == null) {
            responseMap.put("error","Request does not exist");
            return responseMap; }

        else{
            //log.info("Content of Request "+ request );
            return impactAssessmentCourseService.getCourseImpact2(originalCourse, requestedCourse, requestType);
        }
    }
}
