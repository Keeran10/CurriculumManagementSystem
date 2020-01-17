package com.soen490.cms;

import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.RequestRepository;
import com.soen490.cms.Services.RequestPackageService;
import lombok.extern.log4j.Log4j2;
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
public class DossierServiceTest {

    @Autowired
    private RequestPackageService requestPackageService;
    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Before
    public void init(){

    }


    // Assert that a course request has been added to database.
    @Test
    public void testSaveEditCourseRequest(){

        String courseJSON = "{\"id\":5,\"name\":\"SOEN\",\"number\":344,\"title\":\"Advanced Software Architecture and" +
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

        String courseExtrasJSON = "{\"antirequisites\":\"SOEN390\",\"corequisites\":\"SOEN340\",\"equivalents\":" +
                "\"SOEN321\",\"implications\":\"\",\"packageId\":1,\"prerequisites\":\"SOEN343; SOEN384; \",\"" +
                "rationale\":\"\",\"userId\":1,\"requestId\":4}";

        MultipartFile[] files = new MultipartFile[0];

        try {

            int id = requestPackageService.saveCourseRequest(courseJSON, courseExtrasJSON, files);

            assertEquals("Advanced Software Architecture and Design", courseRepository.findById(6).getTitle());

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

        String course = "{\"id\":5,\"name\":\"SOEN\",\"number\":344,\"title\":\"Advanced Software Architecture and" +
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

            int id = requestPackageService.saveRemovalRequest(course, courseExtras, files);

            assertEquals(3, requestRepository.findByRequestId(6).getRequestType());

        } catch (JSONException e) {
            e.printStackTrace();
        }
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


    //
}
