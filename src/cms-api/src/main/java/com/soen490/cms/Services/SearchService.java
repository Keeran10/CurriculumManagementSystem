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
        Collection<Course> courses = courseRepository.findAll();
        for(Course course : courses)
            addRequisites(course);
        return courses;
    }
    public Collection<Course> findCourseByNameAndNumber(String name, int number){
        log.info("find(" + name + number + ")");
        Collection<Course> courses = courseRepository.findByCourseNumber(name, number);
        for(Course course : courses)
            addRequisites(course);
        return courses;
    }

    public Course findCourseById(int id){
        log.info("Course findbyId(): " + id);
        Course course = courseRepository.findById(id);
        addRequisites(course);
        return course;
    }

    public Collection<Degree> findAllDegrees(){
        log.info("findAllDegrees()");
        return degreeRepository.findAll();
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
        return requisiteRepository.findAllOccurrencesOfCourseAsRequisite(id);
    }

    public void addRequisites(Course course) {

        if(course == null) return;

        for(Requisite requisite : course.getRequisites()){
            if(requisite.getType() == 1) {
                Course prereq = courseRepository.findById(requisite.getRequisiteCourseId());
                course.getPrerequisites().add(prereq.getName() + prereq.getNumber());
            }
            if(requisite.getType() == 2) {
                Course coreq = courseRepository.findById(requisite.getRequisiteCourseId());
                course.getCorequisites().add(coreq.getName() + coreq.getNumber());
            }
            if(requisite.getType() == 3) {
                Course antireq = courseRepository.findById(requisite.getRequisiteCourseId());
                course.getAntirequisites().add(antireq.getName() + antireq.getNumber());
            }
            if(requisite.getType() == 4) {
                Course eq = courseRepository.findById(requisite.getRequisiteCourseId());
                course.getEquivalent().add(eq.getName() + eq.getNumber());
            }
        }
    }

}
