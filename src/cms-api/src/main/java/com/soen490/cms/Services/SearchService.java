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
import com.soen490.cms.Models.Sections.Section70719;
import com.soen490.cms.Models.Sections.Section71401;
import com.soen490.cms.Models.Sections.Section71402;
import com.soen490.cms.Repositories.*;
import com.soen490.cms.Repositories.SectionsRepositories.Section70719Repository;
import com.soen490.cms.Repositories.SectionsRepositories.Section71401Repository;
import com.soen490.cms.Repositories.SectionsRepositories.Section71402Repository;
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
    Section70719Repository section70719Repository;
    @Autowired
    Section71401Repository section71401Repository;
    @Autowired
    Section71402Repository section71402Repository;
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

    public Section70719 findsection70719ById(int section_id){

        log.info("find Section70719 " + section_id);

        Section70719 section70719 = section70719Repository.findBySubSectionId(section_id);

        // can add as many core here for the entire section of 70.71.9
        section70719.setFirstCoreCourses(retrieveSectionCourseLists(section70719.getFirstCore()));
        section70719.setSecondCoreCourses(retrieveSectionCourseLists(section70719.getSecondCore()));

        return section70719;
    }

    public Section71401 findSection71401ById(int sectionId) { // BEng Mech Eng
        log.info("search for Section 71.40.1 " + sectionId);

        Section71401 section71401 = section71401Repository.findBySubSectionId(sectionId);

        section71401.setFirstCoreCourses(retrieveSectionCourseLists(section71401.getFirstCore()));
        section71401.setSecondCoreCourses(retrieveSectionCourseLists(section71401.getSecondCore()));
        section71401.setFirstOptionCourses(retrieveSectionCourseLists(section71401.getFirstOption()));
        section71401.setSectionOptionCourses(retrieveSectionCourseLists(section71401.getSecondOption()));
        section71401.setThirdOptionCourses(retrieveSectionCourseLists(section71401.getThirdOption()));
        section71401.setFourthOptionCourses(retrieveSectionCourseLists(section71401.getFourthOption()));
        section71401.setFifthOptionCourses(retrieveSectionCourseLists(section71401.getFifthOption()));
        section71401.setSixthOptionCourses(retrieveSectionCourseLists(section71401.getSixthOption()));

        return section71401;
    }

    public Section71402 findSection71402ById(int sectionId) { // BEng Indu Eng
        log.info("search for Section 71.40.2 " + sectionId);

        Section71402 section71402 = section71402Repository.findBySubSectionId(sectionId);

        section71402.setFirstCoreCourses(retrieveSectionCourseLists(section71402.getFirstCore()));
        section71402.setSecondCoreCourses(retrieveSectionCourseLists(section71402.getSecondCore()));
        section71402.setScienceCoreCourses(retrieveSectionCourseLists(section71402.getScienceCore()));
        section71402.setElectiveCourses(retrieveSectionCourseLists("Indu Electives"));

        return section71402;
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
