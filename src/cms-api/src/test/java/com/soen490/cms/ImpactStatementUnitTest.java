package com.soen490.cms;

import com.soen490.cms.Models.Course;
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

        Course courseUpdated = new Course();
        courseUpdated.setId(6);
        courseUpdated.setName("COMP");
        courseUpdated.setNumber(363);
        courseUpdated.setTitle("Math");
        courseUpdated.setCredits(5);
        courseUpdated.setDescription("test");
        courseUpdated.setRequisites(new ArrayList<>());

        when(searchService.findCourseById(2)).thenReturn(courseUpdated);
        when(searchService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        when(searchService.findAllOccurrencesOfCourseAsRequisite(6)).thenReturn(new ArrayList<Requisite>());

        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);
        assertThat(mapResponse.get("CourseChanges").toString()).isEqualTo("{Number="+courseUpdated.getNumber()+"}");
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
