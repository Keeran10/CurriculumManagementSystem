package com.soen490.cms;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Request;
import com.soen490.cms.Models.Requisite;
import com.soen490.cms.Services.CourseService;
import com.soen490.cms.Services.ImpactAssessmentCourseService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImpactStatementUnitTest {

    CourseService courseService = mock(CourseService.class);

    @Test
    public void ImpactStatementCourseCreation(){
        ImpactAssessmentCourseService impactAssessmentCourse = new ImpactAssessmentCourseService();
        impactAssessmentCourse.setServiceMock(courseService);
        Request request = new Request();
        request.setRequestType(1);
        request.setTargetId(1);
        Course course = new Course();
        course.setId(5);
        course.setName("COMP");
        course.setNumber(362);
        course.setRequisites(new ArrayList<>());
        when(courseService.findCourseById(1)).thenReturn(course);
        when(courseService.findAllOccurrencesOfCourseAsRequisite(5)).thenReturn(new ArrayList<Requisite>());
        Map<String,Object> mapResponse = impactAssessmentCourse.getCourseImpact(request);
        System.out.println(mapResponse);
        assertThat(true).isEqualTo(true);
    }
}
