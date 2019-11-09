package com.soen490.cms;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.*;
import com.soen490.cms.Services.RequestPackageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestPackageServiceTests {

    String json_course = "\"{\\\"id\\\":35,\\\"name\\\":\\\"MECH\\\",\\\"number\\\":371,\\\"title\\\":\\\"Analysis" +
            " and Design of Control Systems\\\",\\\"credits\\\":3.75,\\\"note\\\":\\\"NOTE: Students who have " +
            "received credit for ELEC 372 may not take this course for credit.\\\",\\\"level\\\":1,\\\"lectureHours" +
            "\\\":3,\\\"tutorialHours\\\":1,\\\"labHours\\\":3,\\\"description\\\":\\\"Stability of linear feedback" +
            " systems. Root-Locus method. Frequency response concepts. Stability in the frequency domain. Feedback" +
            " system design using Root Locus techniques. Compensator concepts and configurations. PID-controller" +
            " design. Simulation and computer-aided controller design using Matlab/Simulink. Lectures: three hours" +
            " per week. Tutorial: one hour per week. Laboratory: three hours per week, alternate weeks.\\\",\\\"" +
            "outline\\\":null,\\\"isActive\\\":1,\\\"program\\\":{\\\"id\\\":3,\\\"name\\\":\\\"Mechanical " +
            "Engineering\\\",\\\"description\\\":\\\"description...\\\",\\\"isActive\\\":1,\\\"department\\\":" +
            "{\\\"id\\\":8,\\\"name\\\":\\\"Mechanical, Industrial and Aerospace Engineering\\\",\\\"faculty\\\"" +
            ":{\\\"id\\\":2,\\\"name\\\":\\\"Gina Cody School of Engineering and Computer Science\\\"}}},\\\"" +
            "requisites\\\":[{\\\"id\\\":53,\\\"name\\\":\\\"ENGR\\\",\\\"number\\\":311,\\\"type\\\":\\\"" +
            "prerequisite\\\",\\\"isActive\\\":1},{\\\"id\\\":54,\\\"name\\\":\\\"MECH\\\",\\\"number\\\":370," +
            "\\\"type\\\":\\\"prerequisite\\\",\\\"isActive\\\":1},{\\\"id\\\":55,\\\"name\\\":\\\"ELEC\\\",\\\"" +
            "number\\\":372,\\\"type\\\":\\\"equivalent\\\",\\\"isActive\\\":1}],\\\"degreeRequirements\\\":[{\\\"" +
            "id\\\":36,\\\"core\\\":\\\"Mechanical Engineering Core\\\",\\\"degree\\\":{\\\"id\\\":5,\\\"name\\\"" +
            ":\\\"Mechanical Engineering (BEng)\\\",\\\"level\\\":1,\\\"credits\\\":120}}]}\"";


    String json_courseExtras = "\"{\\\"prerequisites\\\":\\\"ENGR311; MECH370; \\\",\\\"corequisites\\\":\\\"SOEN321" +
            "\\\",\\\"antirequisites\\\":\\\"s\\\",\\\"rationale\\\":\\\"Too old for school.\\\",\\\"implications" +
            "\\\":\\\"Duh Hello?\\\"}\"";


    @Autowired
    private RequestPackageService requestPackageService;

    private CourseRepository courseRepository = mock(CourseRepository.class);

    private RequestRepository requestRepository = mock(RequestRepository.class);

    private DegreeRequirementRepository degreeRequirementRepository = mock(DegreeRequirementRepository.class);

    private UserRepository userRepository = mock(UserRepository.class);

    private RequisiteRepository requisiteRepository = mock(RequisiteRepository.class);

    private RequestPackageRepository requestPackageRepository = mock(RequestPackageRepository.class);


    @Before
    public void init(){

        Random rand = new Random();

        Course course = new Course();
        Requisite requisite = new Requisite();
        Request request = new Request();
        User user = new User();
        RequestPackage requestPackage = new RequestPackage();
        DegreeRequirement degreeRequirement = new DegreeRequirement();


        course.setId(rand.nextInt(100) + 100);
        requisite.setId(rand.nextInt(100) + 100);
        request.setId(rand.nextInt(100) + 100);
        user.setId(rand.nextInt(100) + 100);
        requestPackage.setId(rand.nextInt(100) + 100);
        degreeRequirement.setId(rand.nextInt(100) + 100);

        when(courseRepository.save(course)).thenReturn(course);
        when(requisiteRepository.save(requisite)).thenReturn(requisite);
    }

    @Test
    public void saveCourseRequestTest(){

    }
}
