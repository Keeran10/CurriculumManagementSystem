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
import com.soen490.cms.Models.Sections.*;
import com.soen490.cms.Repositories.*;
import com.soen490.cms.Repositories.SectionsRepositories.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

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
    @Autowired
    UserRepository userRepository;
    @Autowired
    Section71701Repository section71701Repository;
    @Autowired
    Section71702Repository section71702Repository;
    @Autowired
    Section71703Repository section71703Repository;
    @Autowired
    Section71704Repository section71704Repository;
    @Autowired
    Section71705Repository section71705Repository;
    @Autowired
    Section71706Repository section71706Repository;
    @Autowired
    Section71707Repository section71707Repository;
    @Autowired
    Section71708Repository section71708Repository;
    @Autowired
    Section71709Repository section71709Repository;
    @Autowired
    Section717010Repository section717010Repository;
    @Autowired
    DegreeRequirementRepository degreeRequirementRepository;

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

    public Collection<String> findPorgramCoreCourseId(int id){
        return degreeRepository.findProgramCoreCourseId(id);
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

    public Section71701 findsection71701ById(int section_id){
        log.info("find Section71701 " + section_id);

        Section71701 section71701 = section71701Repository.findBySubSectionId(section_id);
        return section71701;
    }

    public Section71702 findsection71702ById(int section_id){
        log.info("find Section71702 " + section_id);

        Section71702 section71702 = section71702Repository.findBySubSectionId(section_id);
        section71702.setFirstCoreCourses(retrieveSectionCourseLists(section71702.getFirstCore()));
        return section71702;
    }

    public Section71703 findsection71703ById(int section_id){
        log.info("find Section71703 " + section_id);

        Section71703 section71703 = section71703Repository.findBySubSectionId(section_id);
        return section71703;
    }

    public Section71704 findsection71704ById(int section_id){
        log.info("find Section71704 " + section_id);

        Section71704 section71704 = section71704Repository.findBySubSectionId(section_id);
        return section71704;
    }

    public Section71705 findsection71705ById(int section_id){
        log.info("find Section71705 " + section_id);

        Section71705 section71705 = section71705Repository.findBySubSectionId(section_id);
        return section71705;
    }

        public Section71706 findsection71706ById(int section_id){
            log.info("find Section71706 " + section_id);

            Section71706 section71706 = section71706Repository.findBySubSectionId(section_id);
            return section71706;
    }

    public Section71707 findsection71707ById(int section_id){
        log.info("find Section71707 " + section_id);

        Section71707 section71707 = section71707Repository.findBySubSectionId(section_id);
        return section71707;
    }

    public Section71708 findsection71708ById(int section_id){
        log.info("find Section71706 " + section_id);

        Section71708 section71708 = section71708Repository.findBySubSectionId(section_id);
        return section71708;
    }

    public Section71709 findsection71709ById(int section_id){

        log.info("find Section71709 " + section_id);

        Section71709 section71709 = section71709Repository.findBySubSectionId(section_id);

        // can add as many core here for the entire section of 70.71.9
        section71709.setFirstCoreCourses(retrieveSectionCourseLists(section71709.getFirstCore()));
        section71709.setSecondCoreCourses(retrieveSectionCourseLists(section71709.getSecondCore()));

        return section71709;
    }

    public Section717010 findsection717010ById(int section_id){
        log.info("find Section71706 " + section_id);

        Section717010 section717010 = section717010Repository.findBySubSectionId(section_id);
        return section717010;
    }

    private List<Course> retrieveSectionCourseLists(String core) {

        List<Integer> courses_id = degreeRequirementRepository.findCoursesByCore(core);
        List<Course> second_core_courses = new ArrayList<>();

        for(int id : courses_id){
            Course course = courseRepository.findById(id);
            if(course != null && course.getIsActive() != 0)
                second_core_courses.add(course);
        }
        second_core_courses.sort(Comparator.comparing(Course::getName));
        second_core_courses.sort(Comparator.comparing(Course::getNumber));

        return second_core_courses;
    }

    public Collection<Requisite> findAllOccurrencesOfCourseAsRequisite(int id){
        log.info("Course "+id+" requisites: ");
        Course course = courseRepository.findById(id);
        return requisiteRepository.findAllOccurrencesOfCourseAsRequisite(course.getName(), course.getNumber());
    }
    public User findUserById(int id){
        log.info("user id searched: "+ id);
        return userRepository.findUserById(id);
    }

    public List<Degree> findDegreesByDepartment(int department_id) {
        return degreeRepository.findDegreesByDepartment(department_id);
    }
}
