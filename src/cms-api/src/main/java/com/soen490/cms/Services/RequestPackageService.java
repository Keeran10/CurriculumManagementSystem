package com.soen490.cms.Services;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.CourseRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
@Log4j2

public class RequestPackageService {

    @Autowired
    RequestPackageRepository packageRepo;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RequestRepository requestRepository;

    public RequestPackage find(Long packageId) {
        log.info("find supporting document with id " + packageId);
        return packageRepo.findById(packageId);
    }


    public boolean saveCourseRequest(){

        Course o = courseRepository.findById(1);
        Course c = new Course();
        c.setName("SOEN");
        c.setNumber(500);
        c.setCredits(3);
        c.setDescription("");
        c.setLevel(0);
        c.setTitle("");
        c.setNote("");
        c.setLabHours(0);
        c.setTutorialHours(0);
        c.setLectureHours(0);

        System.out.println(c.getId());
        System.out.println(c.getId());
        System.out.println(c.getId());

        courseRepository.save(c);

        System.out.println(c.getId());
        System.out.println(c.getId());
        System.out.println(c.getId());

        Request request = new Request();
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId(1); // soen 228
        request.setRationale("");
        request.setResourceImplications("");
        request.setRequestPackage(null);

        ArrayList<DegreeRequirement> list = new ArrayList<>();

        for(DegreeRequirement dr : o.getDegreeRequirements()){

            DegreeRequirement cdr = new DegreeRequirement();
            dr.getDegree().getDegreeRequirements().add(cdr);
            cdr.setCore(dr.getCore());
            cdr.setDegree(dr.getDegree());
            cdr.setCourse(c);
            list.add(cdr);

            System.out.println(dr);
            System.out.println(cdr);
        }
        c.setDegreeRequirements(list);

        courseRepository.save(c);

        requestRepository.save(request);

        return true;
    }
}
