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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    @Autowired
    SupportingDocumentRepository supportingDocumentsRepository;


    // Return package with right id, if id given is 0, a new package is created and returned
    public RequestPackage getRequestPackage(int package_id, int department_id){

        if(package_id == 0)
            return getNewPackage(department_id);

        return requestPackageRepository.findById(package_id);
    }

    /**
     * Saves an edited course to the database.
     * Todo: Equivalent, package_id, and user are set to defaults, commented out or still missing.
     * @param requestForm Stringified JSON received from client
     * @return True if course has been successfully added to database.
     * @throws JSONException
     */
    public int saveCourseRequest(String requestForm) throws JSONException {

        System.out.println(requestForm);

        JSONObject json = new JSONObject(requestForm);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId((Integer) course.get("id"));

        Course original = null;

        if(!o.isEmpty())
            original = o.get(0);
        else return 0;

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));

        Request request = requestRepository.findByTripleId(user_id, package_id, original.getId());

        if(request == null)
            request = new Request();

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
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId((Integer) course.get("id"));
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setRequestPackage(null);
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(userRepository.findById(user_id));
        request.setRequestPackage(requestPackageRepository.findById(package_id));

        request.setTitle(original.getName().toUpperCase() + original.getNumber() + "_update");
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
                if(prerequisite.startsWith("credits", 3)){
                    requisite.setName(prerequisite);
                    requisite.setNumber(0);
                }
                else{
                    requisite.setName(prerequisite.substring(0, 4).trim());
                    requisite.setNumber(Integer.parseInt(prerequisite.substring(4).trim()));
                }
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

        courseRepository.save(c);

        requestRepository.save(request);

        return request.getId();
    }


    // returns list of packages
    public List<RequestPackage> getRequestPackagesByDepartment(int department_id) {

        return requestPackageRepository.findByDepartment(department_id);
    }

    public RequestPackage findById(int id) {
        return requestPackageRepository.findById(id);
    }


    /**
     * Saves a new request package to the database
     * @param requestPackageString contains department name and files (memos, cover letters, supporting docs)
     * @return True if request package was added to database
     * @throws JSONException
     */
    public boolean saveRequestPackage(String requestPackageString) throws JSONException {

        JSONObject requestPackageForm = new JSONObject(requestPackageString);

        System.out.println(requestPackageForm);

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


    // Called when package id received is 0.
    private RequestPackage getNewPackage(int department_id){

        RequestPackage requestPackage = new RequestPackage();

        requestPackage.setDepartment(departmentRepository.findById(department_id));

        requestPackageRepository.save(requestPackage);

        return requestPackage;
    }


    // retrieves all supporting document for a given package
    public List<SupportingDocument> getAllDocuments(int package_id) {
        log.info("find all supporting docs");
        return supportingDocumentsRepository.findByPackage(package_id);
    }


    // retrieves a supporting document
    public SupportingDocument find(int documentId) {
        log.info("find supporting document with id " + documentId);
        return supportingDocumentsRepository.findBySupportingDocumentId(documentId);
    }


    /**
     * Adds support document to an existing package
     * @param document The file to be added.
     * @param packageId The designated package.
     * @return The saved supporting document object.
     * @throws IOException
     */
    public SupportingDocument addSupportingDocument(File document, int packageId) throws IOException {

        log.info("add supporting document " + document.getName());

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        SupportingDocument supportingDocument = new SupportingDocument();
        supportingDocument.setDocument(Files.readAllBytes(document.toPath()));
        supportingDocument.setRequestPackage(requestPackageRepository.findById(packageId));
        supportingDocument.setTimestamp(timestamp);

        return supportingDocumentsRepository.save(supportingDocument);
    }

}
