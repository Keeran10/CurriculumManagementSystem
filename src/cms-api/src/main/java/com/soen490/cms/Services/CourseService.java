package com.soen490.cms.Services;

import com.soen490.cms.Models.Course;
import com.soen490.cms.Models.Requisite;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequisiteRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Log4j2
@Service
public class CourseService {

    @Autowired
    CourseRepository cr;
    @Autowired
    RequisiteRepository rq;

    public Collection<Course> findAll(){
        log.info("findAll()");
        Collection<Course> courses = cr.findAll();
        for(Course course : courses)
            addRequisites(course);
        return courses;
    }

    public Collection<Course> find(String name, int number){
        log.info("find(" + name + number + ")");
        Collection<Course> courses = cr.findByCourseNumber(name, number);
        for(Course course : courses)
            addRequisites(course);
        return courses;
    }

    public Course findCourseById(int id){
        log.info("Course findbyId(): " + id);
        Course course = cr.findById(id);
        addRequisites(course);
        return course;
    }

    public Collection<Requisite> findAllOccurrencesOfCourseAsRequisite(int id){
        log.info("Course "+id+" requisites: ");
        return rq.findAllOccurrencesOfCourseAsRequisite(id);
    }

    public void addRequisites(Course course) {
        for(Requisite requisite : course.getRequisites()){
            if(requisite.getType() == 1) {
                Course prereq = cr.findById(requisite.getRequisiteCourseId());
                course.getPrerequisites().add(prereq.getName() + prereq.getNumber());
            }
            if(requisite.getType() == 2) {
                Course coreq = cr.findById(requisite.getRequisiteCourseId());
                course.getCorequisites().add(coreq.getName() + coreq.getNumber());
            }
            if(requisite.getType() == 3) {
                Course antireq = cr.findById(requisite.getRequisiteCourseId());
                course.getAntirequisites().add(antireq.getName() + antireq.getNumber());
            }
            if(requisite.getType() == 4) {
                Course eq = cr.findById(requisite.getRequisiteCourseId());
                course.getEquivalent().add(eq.getName() + eq.getNumber());
            }
        }
    }
}
