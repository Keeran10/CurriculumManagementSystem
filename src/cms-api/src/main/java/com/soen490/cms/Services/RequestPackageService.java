package com.soen490.cms.Services;

import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.*;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
@Log4j2

public class RequestPackageService {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    DegreeRequirementRepository degreeRequirementRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RequisiteRepository requisiteRepository;
    @Autowired
    RequestPackageRepository requestPackageRepository;
    @Autowired
    DepartmentRepository departmentRepository;


    public RequestPackage getRequestPackage(int id){
        return requestPackageRepository.findById(id);
    }

    /**
     * Saves an edited course to the database.
     * Todo: Equivalent, package_id, and user are set to defaults, commented out or still missing.
     * @param course JSON object with course data.
     * @param courseExtras JSON object with request, requisite, supporting doc and package data.
     * @return True if course has been successfully added to database.
     * @throws JSONException
     */
    public boolean saveCourseRequest(JSONObject course, JSONObject courseExtras) throws JSONException {

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId((Integer) course.get("id"));

        Course original = null;

        if(!o.isEmpty())
            original = o.get(0);
        else return false;

        Course c = new Course();

        c.setName((String) course.get("name"));
        c.setNumber((Integer) course.get("number"));
        c.setTitle((String) course.get("title"));
        c.setCredits(Double.valueOf(String.valueOf(course.get("credits"))));
        c.setDescription((String) course.get("description"));
        c.setLevel((Integer) course.get("level"));
        c.setNote((String) course.get("note"));
        c.setLabHours(Double.valueOf(String.valueOf(course.get("labHours"))));
        c.setTutorialHours(Double.valueOf(String.valueOf(course.get("tutorialHours"))));
        c.setLectureHours(Double.valueOf(String.valueOf(course.get("lectureHours"))));
        c.setIsActive(0);
        c.setProgram(original.getProgram());

        courseRepository.save(c);

        // Requests
        Request request = new Request();
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId((Integer) course.get("id"));
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setRequestPackage(null);
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(userRepository.findById(Integer.parseInt(String.valueOf(courseExtras.get("userId")))));
        request.setRequestPackage(requestPackageRepository.findById(Integer.parseInt(String.valueOf(courseExtras.get("packageId")))));

        // Degree Requirements
        ArrayList<DegreeRequirement> list = new ArrayList<>();

        for(DegreeRequirement dr : original.getDegreeRequirements()){

            DegreeRequirement cdr = new DegreeRequirement();

            cdr.setCore(dr.getCore());
            cdr.setDegree(dr.getDegree());
            cdr.setCourse(c);

            degreeRequirementRepository.save(cdr);

            dr.getDegree().getDegreeRequirements().add(cdr);

            list.add(cdr);

            System.out.println(dr);
            System.out.println(cdr);
        }
        c.setDegreeRequirements(list);

        // Requisites
        String pre = (String) courseExtras.get("prerequisites");
        String co = (String) courseExtras.get("corequisites");
        String anti = (String) courseExtras.get("antirequisites");
        String eq = (String) courseExtras.get("equivalents");

        String[] prerequisites = pre.split(";|\\,");
        String[] corequisites = co.split(";|\\,");
        String[] antirequisites = anti.split(";|\\,");
        String[] equivalents = eq.split(";|,|or");

        for(String prerequisite : prerequisites){

            prerequisite = prerequisite.trim();

            if(prerequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(prerequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(prerequisite.substring(4).trim()));
                requisite.setType("prerequisite");
                requisiteRepository.save(requisite);
            }

        }
        for(String corequisite : corequisites){

            corequisite = corequisite.trim();

            if(corequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(corequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(corequisite.substring(4).trim()));
                requisite.setType("corequisite");
                requisiteRepository.save(requisite);
            }
        }
        for(String antirequisite : antirequisites){

            antirequisite = antirequisite.trim();

            if(antirequisite.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(antirequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(antirequisite.substring(4).trim()));
                requisite.setType("antirequisite");
                requisiteRepository.save(requisite);
            }
        }
        for(String equivalent : equivalents){

            equivalent = equivalent.trim();

            if(equivalent.length() >= 7){

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(equivalent.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(equivalent.substring(4).trim()));
                requisite.setType("equivalent");
                requisiteRepository.save(requisite);
            }
        }

        // Supporting Documents
        // initialize supporting doc and save it to its repository
        if(courseExtras.get("files") != null){

            JSONArray outline = courseExtras.getJSONArray("files");

            System.out.println("this is what I actually receive: " + outline.getJSONObject(0).toString().getBytes());
            //c.setOutline(outline.getJSONObject(0).toString().getBytes());
        }

        courseRepository.save(c);

        requestRepository.save(request);

        return true;
    }


    // returns list of packages
    public List<RequestPackage> getRequestPackagesByDepartment(int department_id) {

        return requestPackageRepository.findByDepartment(department_id);
    }


    /**
     * Saves a new request package to the database
     * @param requestPackageForm contains department name and files (memos, cover letters, supporting docs)
     * @return True if request package was added to database
     * @throws JSONException
     */
    public boolean saveRequestPackage(JSONObject requestPackageForm) throws JSONException {

        RequestPackage requestPackage = new RequestPackage();

        requestPackage.setDepartment(departmentRepository.findByName((String) requestPackageForm.get("name")));

        List<SupportingDocument> supportingDocuments = new ArrayList<>();

        if(requestPackageForm.get("support_docs") != null){

            // init supporting docs, save them to db and then add them to list
        }

        requestPackage.setSupportingDocuments(supportingDocuments);

        requestPackageRepository.save(requestPackage);

        return true;
    }
}
