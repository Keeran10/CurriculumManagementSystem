package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/courses")
    public Collection<Course> getCourses(){
        return searchService.findAllCourses();
    }

    // URL should be 8080/course_search?int=course_id
    @GetMapping("/course_search")
    public Collection<Course> getCourse(@RequestParam String name, @RequestParam int number){
        return searchService.findCourseByNameAndNumber(name, number); }

    // URL should be 8080/course_edit?int=course_id
    @GetMapping("/course_edit")
    public Course getCourseById(@RequestParam int id){ return searchService.findCourseById(id); }

    @GetMapping("/degrees")
    public Collection<Degree> getDegrees(){
        return searchService.findAllDegrees();
    }

    // URL should be 8080/degree_search?name=degree_name
    @GetMapping("/degree_search")
    public Degree getDegree(@RequestParam String name){
        return searchService.findDegreeByName(name); }


    @GetMapping("/programs")
    public Collection<Program> getPrograms(){
        return searchService.findAllPrograms();
    }

    // URL should be 8080/program_search?name=program_name
    @GetMapping("/program_search")
    public Program getProgram(@RequestParam String name){
        return searchService.findProgramByName(name); }

    @GetMapping("/departments")
    public Collection<Department> getDepartments(){
        return searchService.findAllDepartments();
    }

    // URL should be 8080/department_search?name=department_name
    @GetMapping("/department_search")
    public Department getDepartment(@RequestParam String name){
        return searchService.findDepartmentByName(name); }

    @GetMapping("/faculties")
    public Collection<Faculty> getFaculties(){
        return searchService.findAllFaculties();
    }

    // URL should be 8080/faculty_search?name=faculty_name
    @GetMapping("/faculty_search")
    public Faculty getFaculty(@RequestParam String name){
        return searchService.findFacultyByName(name); }
}
