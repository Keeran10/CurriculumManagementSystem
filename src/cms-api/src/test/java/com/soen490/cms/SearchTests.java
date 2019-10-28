package com.soen490.cms;

import com.soen490.cms.Controllers.SearchController;
import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTests {

    @Autowired
    private SearchController searchController;

    @Test
    public void confirmSearchCourses(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getCourses());
        assertNotNull(searchController.getCourse("SOEN", 228));
        assertNotNull(searchController.getCourseById(1));

    }
    @Test
    public void confirmSearchDegrees(){
        assertNotNull(searchController.getDegree("Bachelor of Software Engineering (BEng)"));
        assertNotEquals(Collections.EMPTY_LIST, searchController.getDegrees());
    }
    @Test
    public void confirmSearchPrograms(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getPrograms());
        assertNotNull(searchController.getProgram("Software Engineering"));
    }

    @Test
    public void confirmSearchDepartments(){
        assertNotNull(searchController.getDepartment("Computer Science & Software Engineering"));
        assertNotEquals(Collections.EMPTY_LIST, searchController.getDepartments());
    }

    @Test
    public void confirmSearchFaculties(){
        assertNotEquals(Collections.EMPTY_LIST, searchController.getFaculties());
        assertNotNull(searchController.getFaculty("Gina Cody School of Engineering and Computer Science"));
    }
}
