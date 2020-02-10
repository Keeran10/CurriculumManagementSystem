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
         sections.add(searchService.findsection71709ById(1));
         sections.add(searchService.findSection71401ById(1));
         sections.add(searchService.findSection71402ById(1));
         sections.add(searchService.findsection71709ById(1));
         return sections;
    }

    @GetMapping("/fetch_all_sections")
    public List<Object> getSectionsByDepartment(@RequestParam int department_id) {
        if (department_id == 1) { // Building, Civil and Environmental Engineering
            return null; // not yet added to DB
        } else if (department_id == 2) { // Centre for Engineering in Society
            return null; // not yet added to DB
        } else if (department_id == 3) { // Chemical and Materials Engineering
            return null; // not yet added to DB
        } else if (department_id == 4) { // Computer Science and Software Engineering
            List<Object> soenCompSciSections = new ArrayList<>();

            soenCompSciSections.add(searchService.findsection71701ById(1));
            soenCompSciSections.add(searchService.findsection71702ById(1));
            soenCompSciSections.add(searchService.findsection71703ById(1));
            soenCompSciSections.add(searchService.findsection71704ById(1));
            soenCompSciSections.add(searchService.findsection71705ById(1));
            soenCompSciSections.add(searchService.findsection71706ById(1));
            soenCompSciSections.add(searchService.findsection71707ById(1));
            soenCompSciSections.add(searchService.findsection71708ById(1));
            soenCompSciSections.add(searchService.findsection71709ById(1));
            soenCompSciSections.add(searchService.findsection717010ById(1));

            return soenCompSciSections;
        } else if (department_id == 5) { // Concordia Institude for Information Systems Security
            return null; // not yet added to DB
        } else if (department_id == 6) { // Concordia Institude for Aerospace Design and Innovation
            return null; // not yet added to DB
        } else if (department_id == 7) { // Electrical and Computer Engineering
            return null; // not yet added to DB
        } else if (department_id == 8) { // Mechanical, Industrial and Aerospace Engineering
            List<Object> mechInduSections = new ArrayList<>(); // AERO not added to DB

            mechInduSections.add(searchService.findSection71401ById(1));
            mechInduSections.add(searchService.findSection71402ById(1));

            return mechInduSections;
        } else { // invalid department ID
            return null;
        }
    }

    // URL should be 8080/section71701
    @GetMapping("/section71701")
    public Section71701 getSection71701(){
        return searchService.findsection71701ById(1);
    }

    // URL should be 8080/section71702
    @GetMapping("/section71702")
    public Section71702 getSection71702(){ return searchService.findsection71702ById(1); }

    // URL should be 8080/section71703
    @GetMapping("/section71703")
    public Section71703 getSection71703(){
        return searchService.findsection71703ById(1);
    }

    // URL should be 8080/section71704
    @GetMapping("/section71704")
    public Section71704 getSection71704(){
        return searchService.findsection71704ById(1);
    }

    // URL should be 8080/section71705
    @GetMapping("/section71705")
    public Section71705 getSection71705(){
        return searchService.findsection71705ById(1);
    }

    // URL should be 8080/section71706
    @GetMapping("/section71706")
    public Section71706 getSection71706(){
        return searchService.findsection71706ById(1);
    }

    // URL should be 8080/section71707
    @GetMapping("/section71707")
    public Section71707 getSection71707(){
        return searchService.findsection71707ById(1);
    }

    // URL should be 8080/section71708
    @GetMapping("/section71708")
    public Section71708 getSection71708(){
        return searchService.findsection71708ById(1);
    }

    // URL should be 8080/section71709
    @GetMapping("/section71709")
    public Section71709 getSection71709(){
        return searchService.findsection71709ById(1);
    }

    // URL should be 8080/section717010
    @GetMapping("/section717010")
    public Section717010 getSection717010(){
        return searchService.findsection717010ById(1);
    }

    @GetMapping("/section71401")
    public Section71401 getSection71401(){
        return searchService.findSection71401ById(1);
    }

    @GetMapping("/section71402")
    public Section71402 getSection71402(){
        return searchService.findSection71402ById(1);
    }

    @GetMapping("/get_degrees_by_department")
    public List<Degree> getDegreesFromDepartment(@RequestParam int department_id){

        return searchService.findDegreesByDepartment(department_id);
    }
}
