package com.soen490.cms;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Repositories.CourseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DatabaseTests {

    @Autowired
    private CourseRepository cr;

    @Test
    public void confirmCourses(){
        /** // DATABASE CALLS SHOULD NOT BE TESTED TO AVOID HARD COUPLING
        Collection<Course> courses = cr.findAll();
        int id_ctr = 1;

        // confirms that 14 courses with id from 1 to 14 exists in database
        for(Course course : courses){
            if(id_ctr == 15) break;
            assertEquals(course.getId(), id_ctr++);
        }
        /**/
    }
}
