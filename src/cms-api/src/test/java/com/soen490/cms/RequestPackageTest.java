package com.soen490.cms;

import com.soen490.cms.Controllers.RequestPackageController;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.RequestRepository;
import com.soen490.cms.Repositories.SectionsRepositories.Section70719Repository;
import com.soen490.cms.Services.RequestPackageService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestPackageTest {

    @Autowired
    private RequestPackageService requestPackageService;
    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestPackageController requestPackageController;
    @Autowired
    private Section70719Repository section70719Repository;

    @Before
    public void init(){

    }

    String courseJSON = "{\"id\":5,\"name\":\"SOEN\",\"number\":\"344\",\"title\":\"Advanced Software Architecture and" +
            " Design\",\"credits\":\"5\",\"note\":\"\",\"level\":2,\"lectureHours\":3,\"tutorialHours\":1,\"" +
            "labHours\":0,\"description\":\"Architectural activities, roles, and deliverables. Architectural view" +
            " models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, " +
            "process control) and frameworks. Architectural analysis and the interplay with requirements " +
            "elicitation. Notations for expressing architectural designs, structural and behavioural " +
            "specifications. From architectural design to detailed design. Domain specific architectures and " +
            "design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design " +
            "patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.\",\"isActive\":0,\"" +
            "program\":{\"id\":1,\"name\":\"Software Engineering\",\"description\":\"The Software Engineering " +
            "program is built on the fundamentals of computer science, an engineering core, and a discipline core " +
            "in Software Engineering to cover the engineering approach to all phases of the software process and " +
            "related topics. The curriculum builds on the traditional computer science core topics of computer " +
            "mathematics, theory, programming methodology, and mainstream applications to provide the computing " +
            "theory and practice which underlie the discipline. The engineering core covers basic science, " +
            "professional topics, and introduces the engineering approach to problem solving. The program core in " +
            "Software Engineering includes advanced programming techniques, software specification, design, " +
            "architecture, as well as metrics, security, project management, and quality control. The options " +
            "cover a broad range of advanced topics, from formal methods to distributed systems.\",\"isActive\":1," +
            "\"department\":{\"id\":4,\"name\":\"Computer Science & Software Engineering\",\"faculty\":{\"id\":2," +
            "\"name\":\"Gina Cody School of Engineering and Computer Science\"}}},\"requisites\":[{\"id\":7,\"" +
            "name\":\"SOEN\",\"number\":343,\"type\":\"prerequisite\",\"isActive\":0},{\"id\":8,\"name\":\"" +
            "SOEN\",\"number\":384,\"type\":\"prerequisite\",\"isActive\":0}],\"degreeRequirements\":[{\"id\":15," +
            "\"core\":\"\",\"degree\":{\"id\":2,\"name\":\"Master of Software Engineering (MEng)\",\"level\":2,\"" +
            "credits\":45}},{\"id\":16,\"core\":\"Computer Science Electives\",\"degree\":{\"id\":1,\"name\":\"" +
            "Bachelor of Software Engineering (BEng)\",\"level\":1,\"credits\":120}}]}";

    String json_course = "{\"id\":\"35\",\"name\":\"MECH\",\"number\":\"371\",\"title\":\"Analysis" +
            " and Design of Control Systems\",\"credits\":3.75,\"note\":\"NOTE: Students who have " +
            "received credit for ELEC 372 may not take this course for credit.\",\"level\":1,\"lectureHours" +
            "\":3,\"tutorialHours\":1,\"labHours\":3,\"description\":\"Stability of linear feedback" +
            " systems. Root-Locus method. Frequency response concepts. Stability in the frequency domain. Feedback" +
            " system design using Root Locus techniques. Compensator concepts and configurations. PID-controller" +
            " design. Simulation and computer-aided controller design using Matlab/Simulink. Lectures: three hours" +
            " per week. Tutorial: one hour per week. Laboratory: three hours per week, alternate weeks.\",\"" +
            "outline\":null,\"isActive\":1,\"program\":{\"id\":3,\"name\":\"Mechanical " +
            "Engineering\",\"description\":\"description...\",\"isActive\":1,\"department\":" +
            "{\"id\":8,\"name\":\"Mechanical, Industrial and Aerospace Engineering\",\"faculty\"" +
            ":{\"id\":2,\"name\":\"Gina Cody School of Engineering and Computer Science\"}}},\"" +
            "requisites\":[{\"id\":53,\"name\":\"ENGR\",\"number\":311,\"type\":\"" +
            "prerequisite\",\"isActive\":1},{\"id\":54,\"name\":\"MECH\",\"number\":370," +
            "\"type\":\"prerequisite\",\"isActive\":1},{\"id\":55,\"name\":\"ELEC\",\"" +
            "number\":372,\"type\":\"equivalent\",\"isActive\":1}],\"degreeRequirements\":[{\"" +
            "id\":36,\"core\":\"Mechanical Engineering Core\",\"degree\":{\"id\":5,\"name\"" +
            ":\"Mechanical Engineering (BEng)\",\"level\":1,\"credits\":120}}]}";


    // Verify JSON manipulation
    @Test
    public void saveCourseRequestTest() throws JSONException {

        JSONObject course = new JSONObject(json_course);

        // These prove JSON data handling in RequestPackageService
        assertEquals(Integer.valueOf(35), Integer.valueOf(String.valueOf(course.get("id"))));
        assertEquals("MECH", course.get("name"));
        assertEquals(Integer.valueOf(371), Integer.valueOf(String.valueOf(course.get("number"))));
        assertEquals(Double.valueOf(3.75), Double.valueOf(String.valueOf(course.get("credits"))));
        assertEquals(1, course.get("level"));
        assertEquals(3, course.get("labHours"));
        assertEquals(1, course.get("isActive"));
        // retrieving inner data
        assertEquals(3, course.getJSONObject("program").get("id"));
    }


    // Assert that a course request has been added to database.
    @Test
    public void testSaveEditCourseRequest(){

        String courseExtrasJSON = "{\"antirequisites\":\"SOEN390\",\"corequisites\":\"SOEN340\",\"equivalents\":" +
                "\"SOEN321\",\"implications\":\"\",\"packageId\":1,\"prerequisites\":\"SOEN343; SOEN384; \",\"" +
                "rationale\":\"\",\"userId\":1,\"requestId\":4}";

        MultipartFile[] files = new MultipartFile[0];

        int id = requestPackageController.saveCreateAndEditRequest(courseJSON, courseExtrasJSON, files);

        assertEquals("Advanced Software Architecture and Design", courseRepository.findById(6).getTitle());

    }


    // Assert that a create request has been added to database.
    @Test
    public void testSaveCreateCourseRequest(){

        String course = "{\"id\":0,\"name\":\"SOEN\",\"number\":344,\"title\":\"Advanced Software Architecture and" +
                " Design\",\"credits\":\"5\",\"note\":\"\",\"level\":2,\"lectureHours\":3,\"tutorialHours\":1,\"" +
                "labHours\":0,\"description\":\"Architectural activities, roles, and deliverables. Architectural view" +
                " models. Architectural styles (including client‑server, layered, pipes‑and‑filters, event‑based, " +
                "process control) and frameworks. Architectural analysis and the interplay with requirements " +
                "elicitation. Notations for expressing architectural designs, structural and behavioural " +
                "specifications. From architectural design to detailed design. Domain specific architectures and " +
                "design patterns. Evaluation and performance estimation of designs. Advanced object‑oriented design " +
                "patterns and idioms. Lectures: three hours per week. Tutorial: one hour per week.\",\"isActive\":0,\"" +
                "program\":{\"id\":1,\"name\":\"Software Engineering\",\"description\":\"The Software Engineering " +
                "program is built on the fundamentals of computer science, an engineering core, and a discipline core " +
                "in Software Engineering to cover the engineering approach to all phases of the software process and " +
                "related topics. The curriculum builds on the traditional computer science core topics of computer " +
                "mathematics, theory, programming methodology, and mainstream applications to provide the computing " +
                "theory and practice which underlie the discipline. The engineering core covers basic science, " +
                "professional topics, and introduces the engineering approach to problem solving. The program core in " +
                "Software Engineering includes advanced programming techniques, software specification, design, " +
                "architecture, as well as metrics, security, project management, and quality control. The options " +
                "cover a broad range of advanced topics, from formal methods to distributed systems.\",\"isActive\":1," +
                "\"department\":{\"id\":4,\"name\":\"Computer Science & Software Engineering\",\"faculty\":{\"id\":2," +
                "\"name\":\"Gina Cody School of Engineering and Computer Science\"}}},\"requisites\":[{\"id\":7,\"" +
                "name\":\"SOEN\",\"number\":343,\"type\":\"prerequisite\",\"isActive\":0},{\"id\":8,\"name\":\"" +
                "SOEN\",\"number\":384,\"type\":\"prerequisite\",\"isActive\":0}],\"degreeRequirements\":[{\"id\":15," +
                "\"core\":\"\",\"degree\":{\"id\":2,\"name\":\"Master of Software Engineering (MEng)\",\"level\":2,\"" +
                "credits\":45}},{\"id\":16,\"core\":\"Computer Science Electives\",\"degree\":{\"id\":1,\"name\":\"" +
                "Bachelor of Software Engineering (BEng)\",\"level\":1,\"credits\":120}}]}";

        String courseExtras = "{\"antirequisites\":\"\",\"corequisites\":\"\",\"equivalents\":\"\"," +
                "\"implications\":\"\",\"packageId\":1,\"prerequisites\":\"\",\"" +
                "rationale\":\"\",\"userId\":1,\"requestId\":0}";

        MultipartFile[] files = new MultipartFile[0];

        try {

            JSONObject courseJSON = new JSONObject(course);
            JSONObject courseExtrasJSON = new JSONObject(courseExtras);

            requestPackageService.saveCreateRequest(courseJSON, courseExtrasJSON, files);

            assertEquals("Advanced Software Architecture and Design", courseRepository.findById(7).getTitle());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    // Assert that a removal request is added to database.
    @Test
    public void testSaveCourseRemovalRequest(){

        String courseExtras = "{\"antirequisites\":\"\",\"corequisites\":\"\",\"equivalents\":\"\"," +
                "\"implications\":\"\",\"packageId\":1,\"prerequisites\":\"\",\"" +
                "rationale\":\"\",\"userId\":1,\"requestId\":0}";

        MultipartFile[] files = new MultipartFile[0];

        int id = requestPackageController.saveRemovalRequest(courseJSON, courseExtras, files);

        assertEquals(3, requestRepository.findByRequestId(7).getRequestType());

    }


    // Verify that dossier revisions is provided
    @Test
    public void testDossierRevisions(){

        requestPackageService.generatePdf(1, 1);

        List revisions = requestPackageService.getDossierRevisions(1);

        assertEquals(false, revisions.isEmpty());

    }


    // Asserts that original data have been overriden by requested changes.
    @Test
    public void testFinalizeDossier(){

        requestPackageService.finalizeDossierRequests(requestPackageRepository.findById(1));

        // confirm that course #1 has been modified
        assertEquals("Brocessor structure...", courseRepository.findById(1).getDescription());
        // confirm that course #3 has been added
        assertEquals(1, courseRepository.findById(3).getIsActive());
        // confirm that course #4 has been deleted
        assertEquals(null, courseRepository.findById(4));
    }


    // Assert subsection functionality
    @Test
    public void testSubSectionSave(){

        String section70719JSON = "{\"id\":1,\"first_core\":\"Engineering Core\",\"second_core\":\"Software Engineering Core\"," +
                "\"first_paragraph\":\"test\",\"isActive\":0,\"section_id\":\"70.71.9\", " +
                "\"section_title\":\"Degree Requirements for the BEng in Software Engineering\"}";

        String section70719ExtrasJSON = "{\"implications\":\"\",\"packageId\":1,\"prerequisites\":\"SOEN343; SOEN384; \",\"" +
                "rationale\":\"\",\"userId\":1,\"requestId\":0}";

        requestPackageController.saveSubSection70719(section70719JSON, section70719ExtrasJSON, null);

        assertEquals("test", section70719Repository.findById(2).getFirstParagraph());
    }
}
