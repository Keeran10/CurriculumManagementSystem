package com.soen490.cms;

import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.UserRepository;
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
    private UserRepository userRepo;

    @Test
    public void confirmCourses(){
        /**/ // DATABASE CALLS SHOULD NOT BE TESTED TO AVOID HARD COUPLING
        Collection<User> users = userRepo.findAll();
        int id_ctr = 1;

        // confirms that 1 user exists in database
        for(User user : users){
            System.out.println("made it here");
            assertEquals(user.getId(), id_ctr++);
        }
        /**/
    }
}
