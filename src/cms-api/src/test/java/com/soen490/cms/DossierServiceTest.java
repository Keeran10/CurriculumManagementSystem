package com.soen490.cms;

import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Services.RequestPackageService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DossierServiceTest {

    @Autowired
    private RequestPackageService requestPackageService;
    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void init(){

    }

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
}
