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

package com.soen490.cms;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.SectionRepository;
import com.soen490.cms.Services.ImpactAssessmentService;
import com.soen490.cms.Services.SearchService;
import com.soen490.cms.Services.ImpactAssessmentCourseService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ImpactStatementUnitTest {

    SearchService searchService = mock(SearchService.class);
    SectionRepository sectionRepository = mock(SectionRepository.class);
    Course course;
    ImpactAssessmentCourseService impactAssessmentCourse;

    @Autowired
    ImpactAssessmentService impactAssessmentService;

    @Before
    public void initiation(){
        course = new Course();
        course.setId(5);
        course.setName("COMP");
        course.setNumber(362);
        course.setCredits(5);
        course.setDescription("test");
        course.setLabHours(5);
        course.setTutorialHours(3);
        course.setLectureHours(1);
        course.setLevel(2);
        course.setTitle("Math");
        course.setRequisites(new ArrayList<>());
        Program program = new Program();
        program.setId(1);
        Department department = new Department();
        department.setId(0);
        program.setDepartment(department);
        course.setProgram(program);
        when(searchService.findCourseById(1)).thenReturn(course);
        List<String> sectionsList = null;
        when(sectionRepository.findByTarget("department", course.getProgram().getDepartment().getId())).thenReturn(sectionsList);
        impactAssessmentCourse = new ImpactAssessmentCourseService();
        impactAssessmentCourse.setServiceMock(searchService,sectionRepository);
    }

    @Test
    public void ImpactStatementCourseCreation(){
        Request request = new Request();
        request.setRequestType(1);
        request.setTargetId(1);

        when(searchService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);

        assertThat(mapResponse.containsValue(course.getName())).isEqualTo(true);
        assertThat(mapResponse.containsValue(course.getNumber())).isEqualTo(true);
    }

    @Test
    public void ImpactStatementCourseUpdate(){
        Request request = new Request();
        request.setRequestType(2);
        request.setTargetId(2);
        request.setOriginalId(1);
        Program program = new Program();
        program.setId(1);
        Department department = new Department();
        department.setId(0);
        program.setDepartment(department);
        course.setProgram(program);
        Course courseUpdated = new Course();
        courseUpdated.setProgram(program);
        courseUpdated.setId(6);
        courseUpdated.setName("COMP");
        courseUpdated.setNumber(363);
        courseUpdated.setTitle("Math");
        courseUpdated.setCredits(5);
        courseUpdated.setDescription("test");
        courseUpdated.setLabHours(5);
        courseUpdated.setTutorialHours(3);
        courseUpdated.setLectureHours(1);
        courseUpdated.setLevel(2);
        courseUpdated.setRequisites(new ArrayList<>());

        when(searchService.findCourseById(2)).thenReturn(courseUpdated);
        when(searchService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        when(searchService.findAllOccurrencesOfCourseAsRequisite(6)).thenReturn(new ArrayList<Requisite>());

        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);
        assertThat(mapResponse.get("CourseEdits").toString()).isEqualTo("{number="+courseUpdated.getNumber()+"}");
    }

    @Test
    public void ImpactStatementCourseRemoval(){
        Request request = new Request();
        request.setRequestType(3);
        request.setTargetId(1);

        when(searchService.findCourseById(0)).thenReturn(course);
        when(searchService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);

        assertThat(mapResponse.containsValue(course.getName())).isEqualTo(true);
        assertThat(mapResponse.containsValue(course.getNumber())).isEqualTo(true);
    }


    @Test
    public void testGetCourseImpact(){

        String requestForm = "{\"params\":{\"updates\":[{\"param\":\"course\",\"value\":\"{\\\"id\\\":8,\\\"name\\\":\\\"SOEN\\\",\\\"number\\\":344,\\\"title\\\":\\\"Advanced Software Architecture and Design\\\",\\\"credits\\\":\\\"5\\\",\\\"note\\\":\\\"\\\",\\\"level\\\":2,\\\"lectureHours\\\":3,\\\"tutorialHours\\\":1,\\\"labHours\\\":0,\\\"description\\\":\\\"Architectural activities, roles, and deliverables. Architectural view models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, process control) and frameworks. Architectural analysis and the interplay with requirements elicitation. Notations for expressing architectural designs, structural and behavioural specifications. From architectural design to detailed design. Domain specific architectures and design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.\\\",\\\"isActive\\\":0,\\\"program\\\":{\\\"id\\\":1,\\\"name\\\":\\\"Software Engineering\\\",\\\"description\\\":\\\"The Software Engineering program is built on the fundamentals of computer science, an engineering core, and a discipline core in Software Engineering to cover the engineering approach to all phases of the software process and related topics. The curriculum builds on the traditional computer science core topics of computer mathematics, theory, programming methodology, and mainstream applications to provide the computing theory and practice which underlie the discipline. The engineering core covers basic science, professional topics, and introduces the engineering approach to problem solving. The program core in Software Engineering includes advanced programming techniques, software specification, design, architecture, as well as metrics, security, project management, and quality control. The options cover a broad range of advanced topics, from formal methods to distributed systems.\\\",\\\"isActive\\\":1,\\\"department\\\":{\\\"id\\\":4,\\\"name\\\":\\\"Computer Science & Software Engineering\\\",\\\"faculty\\\":{\\\"id\\\":2,\\\"name\\\":\\\"Gina Cody School of Engineering and Computer Science\\\"}}},\\\"requisites\\\":[{\\\"id\\\":7,\\\"name\\\":\\\"SOEN\\\",\\\"number\\\":343,\\\"type\\\":\\\"prerequisite\\\",\\\"isActive\\\":0},{\\\"id\\\":8,\\\"name\\\":\\\"SOEN\\\",\\\"number\\\":384,\\\"type\\\":\\\"prerequisite\\\",\\\"isActive\\\":0}],\\\"degreeRequirements\\\":[{\\\"id\\\":16,\\\"core\\\":\\\"Computer Science Electives\\\",\\\"degree\\\":{\\\"id\\\":1,\\\"name\\\":\\\"Bachelor of Software Engineering (BEng)\\\",\\\"level\\\":1,\\\"credits\\\":120}}]}\",\"op\":\"s\"},{\"param\":\"courseExtras\",\"value\":\"{\\\"antirequisites\\\":\\\"\\\",\\\"corequisites\\\":\\\"SOEN357;\\\",\\\"equivalents\\\":\\\"\\\",\\\"implications\\\":\\\"\\\",\\\"packageId\\\":1,\\\"prerequisites\\\":\\\"SOEN343; SOEN384;\\\",\\\"rationale\\\":\\\"\\\",\\\"userId\\\":1,\\\"requestId\\\":1}\",\"op\":\"s\"}],\"cloneFrom\":{\"updates\":null,\"cloneFrom\":null,\"encoder\":{},\"map\":null},\"encoder\":{},\"map\":null}}\n";

        Map<String, Object> impact_report = null;
        try {
            impact_report = impactAssessmentService.getCourseImpact(requestForm);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        assertThat(impact_report != null && !impact_report.isEmpty());
    }
}
