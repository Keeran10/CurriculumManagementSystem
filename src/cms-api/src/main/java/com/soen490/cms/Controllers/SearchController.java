// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.

package com.soen490.cms.Controllers;

import com.soen490.cms.Models.*;
import com.soen490.cms.Models.Sections.*;
import com.soen490.cms.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
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

    // URL should be 8080/sections
    @GetMapping("/sections")
    public List<Object> getSections(){
        List<Object> sections = new ArrayList<>();
         sections.add(searchService.findsection70719ById(1));
         return sections;
    }

    // URL should be 8080/section70711
    @GetMapping("/section70711")
    public Section70711 getSection70711(){
        return searchService.findsection70711ById(1);
    }

    // URL should be 8080/section70712
    @GetMapping("/section70712")
    public Section70712 getSection70712(){
        return searchService.findsection70712ById(1);
    }

    // URL should be 8080/section70713
    @GetMapping("/section70713")
    public Section70713 getSection70713(){
        return searchService.findsection70713ById(1);
    }

    // URL should be 8080/section70714
    @GetMapping("/section70714")
    public Section70714 getSection70714(){
        return searchService.findsection70714ById(1);
    }

    // URL should be 8080/section70715
    @GetMapping("/section70715")
    public Section70715 getSection70715(){
        return searchService.findsection70715ById(1);
    }

    // URL should be 8080/section70716
    @GetMapping("/section70716")
    public Section70716 getSection70716(){
        return searchService.findsection70716ById(1);
    }

    // URL should be 8080/section70717
    @GetMapping("/section70717")
    public Section70717 getSection70717(){
        return searchService.findsection70717ById(1);
    }

    // URL should be 8080/section70718
    @GetMapping("/section70718")
    public Section70718 getSection70718(){
        return searchService.findsection70718ById(1);
    }

    // URL should be 8080/section70719
    @GetMapping("/section70719")
    public Section70719 getSection70719(){
        return searchService.findsection70719ById(1);
    }

    // URL should be 8080/section707110
    @GetMapping("/section707110")
    public Section707110 getSection707110(){
        return searchService.findsection707110ById(1);
    }


    @GetMapping("/get_degrees_by_department")
    public List<Degree> getDegreesFromDepartment(@RequestParam int department_id){

        return searchService.findDegreesByDepartment(department_id);
    }
}
