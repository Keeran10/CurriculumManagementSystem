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

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Program;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Requisite;
import com.soen490.cms.Services.SearchService;
import com.soen490.cms.Services.ImpactAssessmentCourseService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImpactStatementUnitTest {

    SearchService searchService = mock(SearchService.class);
    Course course;
    ImpactAssessmentCourseService impactAssessmentCourse;

    @Before
    public void intiation(){
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
        when(searchService.findCourseById(1)).thenReturn(course);
        impactAssessmentCourse = new ImpactAssessmentCourseService();
        impactAssessmentCourse.setServiceMock(searchService);
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


        when(searchService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);

        assertThat(mapResponse.containsValue(course.getName())).isEqualTo(true);
        assertThat(mapResponse.containsValue(course.getNumber())).isEqualTo(true);
    }
}
