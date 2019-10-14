package com.soen490.cms.Controllers;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

    @Autowired
    CourseService cs;

    @GetMapping("/courses")
    public Collection<Course> getCourses(){
        return cs.findAll();
    }

    @GetMapping("/course")
    public Collection<Course> getCourse(@RequestParam String name, @RequestParam int number){
        return cs.find(name, number);
    }
}
