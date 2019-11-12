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

package com.soen490.cms.Services;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Log4j2
@Service
public class SearchService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    DegreeRepository degreeRepository;
    @Autowired
    ProgramRepository programRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    RequisiteRepository requisiteRepository;

    public Collection<Course> findAllCourses(){
        log.info("findAllCourses()");
        return courseRepository.findAll();
    }
    public Collection<Course> findCourseByNameAndNumber(String name, int number){
        log.info("find(" + name + number + ")");
        return courseRepository.findByCourseNumber(name, number);
    }

    public Course findCourseById(int id){
        log.info("Course findbyId(): " + id);
        return courseRepository.findById(id);
    }

    public Collection<Degree> findAllDegrees(){
        log.info("findAllDegrees()");
        return degreeRepository.findAll();
    }

    public Collection<Degree> findDegreesByRequiredCourseId(int id){
        log.info("find all degrees by required Course id");
        return degreeRepository.findDegreeByRequiredCourseId(id);
    }

    public Collection<Degree> findDegreesByElectiveCourseId(int id){
        log.info("find all degrees by elective Course id");
        return degreeRepository.findDegreeByElectiveCourseId(id);
    }

    public Double findCreditsTotalOfCoreProgram(String name){
        log.info("find total fo credits of core programs");
        return degreeRepository.findCreditsTotalOfCoreProgram(name);
    }

    public Degree findDegreeByName(String name) {
        log.info("find degree " + name);
        return degreeRepository.findByName(name);
    }

    public Collection<Program> findAllPrograms(){
        log.info("findAllPrograms()");
        return programRepository.findAll();
    }

    public Program findProgramByName(String name) {
        log.info("find program " + name);
        return programRepository.findByName(name);
    }

    public Collection<Department> findAllDepartments() {
        log.info("findAllDepartments()");
        return departmentRepository.findAll();
    }

    public Department findDepartmentByName(String name){
        log.info("find department " + name);
        return departmentRepository.findByName(name);
    }

    public Collection<Faculty> findAllFaculties(){
        log.info("findAllFaculties()");
        return facultyRepository.findAll();
    }

    public Faculty findFacultyByName(String name){
        log.info("find faculty " + name);
        return facultyRepository.findByName(name);
    }

    public Collection<Requisite> findAllOccurrencesOfCourseAsRequisite(int id){
        log.info("Course "+id+" requisites: ");
        Course course = courseRepository.findById(id);
        return requisiteRepository.findAllOccurrencesOfCourseAsRequisite(course.getName(), course.getNumber());
    }
}
