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
import com.soen490.cms.Repositories.DegreeRepository;
import com.soen490.cms.Repositories.DegreeRequirementRepository;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    DegreeRepository degreeRepository;

    public Map<String, Object> getCourseImpact(String requestForm) throws JSONException {

        log.info("Json received: " + requestForm);

        JSONObject json = new JSONObject(requestForm);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        int original_id = (Integer) course.get("id");

        Course original = null;

        if (original_id != 0)
            original = courseRepository.findById(original_id);

        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));
        Request request = requestRepository.findByRequestId(request_id);

        Course c = new Course();

        c.setName((String) course.get("name"));
        c.setNumber(Integer.valueOf((String.valueOf(course.get("number")))));
        c.setTitle((String) course.get("title"));
        c.setCredits(Double.valueOf(String.valueOf(course.get("credits"))));
        c.setDescription((String) course.get("description"));
        c.setLevel((Integer) course.get("level"));
        c.setNote((String) course.get("note"));
        c.setLabHours(Double.valueOf(String.valueOf(course.get("labHours"))));
        c.setTutorialHours(Double.valueOf(String.valueOf(course.get("tutorialHours"))));
        c.setLectureHours(Double.valueOf(String.valueOf(course.get("lectureHours"))));
        c.setIsActive(0);

        courseRepository.save(c);


        if(original != null)
            request.setTitle(original.getName().toUpperCase() + original.getNumber() + "_update");
        else
            request.setTitle(c.getName().toUpperCase() + c.getNumber() + "_create");

        // Requisites
        String pre = (String) courseExtras.get("prerequisites");
        String co = (String) courseExtras.get("corequisites");
        String anti = (String) courseExtras.get("antirequisites");
        String eq = (String) courseExtras.get("equivalents");

        courseRepository.save(c);

        // Set degree requirements
        ArrayList<DegreeRequirement> list = new ArrayList<>();
        int size = course.getJSONArray("degreeRequirements").length();
        int ctr = 0;

        for(int i = 0; i < size; i++){

            JSONObject degreeRequirements = (JSONObject) course.getJSONArray("degreeRequirements").get(i);
            int degreeReq_id = (Integer) degreeRequirements.get("id");

            JSONObject degreeJSON = (JSONObject) degreeRequirements.get("degree");
            int degree_id = (Integer) degreeJSON.get("id");
            Degree degree = degreeRepository.findById(degree_id);
            String core = (String) degreeRequirements.get("core");

            if(ctr == 0 && degree != null){
                c.setProgram(degree.getProgram());
                courseRepository.save(c);
                ctr++;
            }

            DegreeRequirement cdr = null;

            if(degreeReq_id == 0)
                cdr = new DegreeRequirement();
            else
                cdr = degreeRequirementRepository.findById(degreeReq_id);

            if(core == null || degree == null || cdr == null)
                continue;

            cdr.setCore(core);
            cdr.setDegree(degree);
            cdr.setCourse(c);

            degreeRequirementRepository.save(cdr);
            list.add(cdr);
        }

        c.setDegreeRequirements(list);

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
