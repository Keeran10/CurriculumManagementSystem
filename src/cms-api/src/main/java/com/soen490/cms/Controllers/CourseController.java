package com.soen490.cms.Controllers;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@RestController
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

    @GetMapping("/course/{id}")
    public Course getCourseById(@PathVariable @NotNull int id){
        return cs.findCourseById(id);
    }
}
