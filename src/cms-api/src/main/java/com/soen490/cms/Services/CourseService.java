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
        return cr.findAll();
    }

    public Collection<Course> find(String name, int number){
        log.info("find(" + name + number + ")");
        return cr.findByCourseNumber(name, number);
    }

    public Course findCourseById(int id){
        log.info("Course findbyId(): " + id);
        return cr.findById(id);
    }

    public Collection<Requisite> findAllOccurrencesOfCourseAsRequisite(int id){
        log.info("Course "+id+" requisites: ");
        return rq.findAllOccurrencesOfCourseAsRequisite(id);
    }


}
