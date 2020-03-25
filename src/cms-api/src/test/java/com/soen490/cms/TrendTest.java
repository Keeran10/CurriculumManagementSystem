package com.soen490.cms;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Services.TrendService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class TrendTest {

    @Autowired
    private TrendService trendService;


    // Verify that keywords are filtered to remove stop words
    @Test
    public void testKeywordStopwordFilter(){

        Course testCourse = new Course();
        Course testCourse2 = new Course();
        testCourse2.setTitle("Not Software I Security and System");

        try {
            TrendService.loadStopwords();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> keywords = trendService.getTitleKeywords(testCourse, testCourse2);

        String keyword = "";
        for(String k : keywords)
            keyword += k + " ";

        assertEquals("software security system ", keyword);
    }
}
