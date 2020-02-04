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
    Section70711Repository section70711Repository;
    @Autowired
    Section70712Repository section70712Repository;
    @Autowired
    Section70713Repository section70713Repository;
    @Autowired
    Section70714Repository section70714Repository;
    @Autowired
    Section70715Repository section70715Repository;
    @Autowired
    Section70716Repository section70716Repository;
    @Autowired
    Section70717Repository section70717Repository;
    @Autowired
    Section70718Repository section70718Repository;
    @Autowired
    Section70719Repository section70719Repository;
    @Autowired
    Section707110Repository section707110Repository;
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

    public Section70711 findsection70711ById(int section_id){
        log.info("find Section70711 " + section_id);

        Section70711 section70711 = section70711Repository.findBySubSectionId(section_id);
        return section70711;
    }

    public Section70712 findsection70712ById(int section_id){
        log.info("find Section70712 " + section_id);

        Section70712 section70712 = section70712Repository.findBySubSectionId(section_id);
        return section70712;
    }

    public Section70713 findsection70713ById(int section_id){
        log.info("find Section70713 " + section_id);

        Section70713 section70713 = section70713Repository.findBySubSectionId(section_id);
        return section70713;
    }

    public Section70714 findsection70714ById(int section_id){
        log.info("find Section70714 " + section_id);

        Section70714 section70714 = section70714Repository.findBySubSectionId(section_id);
        return section70714;
    }

    public Section70715 findsection70715ById(int section_id){
        log.info("find Section70715 " + section_id);

        Section70715 section70715 = section70715Repository.findBySubSectionId(section_id);
        return section70715;
    }

        public Section70716 findsection70716ById(int section_id){
            log.info("find Section70716 " + section_id);

            Section70716 section70716 = section70716Repository.findBySubSectionId(section_id);
            return section70716;
    }

    public Section70717 findsection70717ById(int section_id){
        log.info("find Section70717 " + section_id);

        Section70717 section70717 = section70717Repository.findBySubSectionId(section_id);
        return section70717;
    }

    public Section70718 findsection70718ById(int section_id){
        log.info("find Section70716 " + section_id);

        Section70718 section70718 = section70718Repository.findBySubSectionId(section_id);
        return section70718;
    }

    public Section70719 findsection70719ById(int section_id){

        log.info("find Section70719 " + section_id);

        Section70719 section70719 = section70719Repository.findBySubSectionId(section_id);

        // can add as many core here for the entire section of 70.71.9
        section70719.setFirstCoreCourses(retrieveSectionCourseLists(section70719.getFirstCore()));
        section70719.setSecondCoreCourses(retrieveSectionCourseLists(section70719.getSecondCore()));

        return section70719;
    }

    public Section707110 findsection707110ById(int section_id){
        log.info("find Section70716 " + section_id);

        Section707110 section707110 = section707110Repository.findBySubSectionId(section_id);
        return section707110;
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
