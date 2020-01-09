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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
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

        log.info("getRequestPackage called with package_id " + package_id + " and department_id " + department_id + ".");

        if(package_id == 0)
            return getNewPackage(department_id);

        return requestPackageRepository.findById(package_id);
    }

    /**
     * Saves an edited course to the database.
     * @param courseJSON Stringified course JSON received from client
     * @param courseExtrasJSON Stringified course JSON received from client
     * @param file uploaded course outline
     * @return True if course has been successfully added to database.
     * @throws JSONException
     */
    public int saveCourseRequest(String courseJSON, String courseExtrasJSON, byte[] file) throws JSONException {

        log.info("Json course received: " + courseJSON);
        log.info("Json courseExtras received: " + courseExtrasJSON);
        log.info("File received received: " + file);

        JSONObject course = new JSONObject(courseJSON);
        JSONObject courseExtras = new JSONObject(courseExtrasJSON);

        int original_id = (Integer) course.get("id");

        if(original_id == 0)
            return saveCreateRequest(course, courseExtras, file);

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId(original_id);

        Course original = null;

        if(!o.isEmpty())
            original = o.get(0);
        else return 0;

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Course c = null;

        if(request == null) {
            request = new Request();
            c = new Course();
        }
        else{
            c = courseRepository.findById(request.getTargetId());
        }

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

        if(file != null)
            c.setOutline(file);

        courseRepository.save(c);

        // Requests
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId((Integer) course.get("id"));
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        request.setTitle(original.getName().toUpperCase() + original.getNumber() + "_update");

        if(c.getDegreeRequirements() == null) {

            // Degree Requirements
            ArrayList<DegreeRequirement> list = new ArrayList<>();

            for (DegreeRequirement dr : original.getDegreeRequirements()) {

                DegreeRequirement cdr = new DegreeRequirement();

                cdr.setCore(dr.getCore());
                cdr.setDegree(dr.getDegree());
                cdr.setCourse(c);

                degreeRequirementRepository.save(cdr);

                dr.getDegree().getDegreeRequirements().add(cdr);

                list.add(cdr);

            }
            c.setDegreeRequirements(list);
        }

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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
                    requisiteRepository.save(requisite);
            }
        }

        // Supporting Documents
        // initialize supporting doc and save it to its repository

        courseRepository.save(c);

        requestRepository.save(request);


        log.info("course saved: " + c);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);


        return request.getId();
    }


    /**
     * Saves a newly created course to the database.
     * @param course, courseExtras
     * @return request_id if course and request have been successfully added to database.
     * @throws JSONException
     */
    private int saveCreateRequest(JSONObject course, JSONObject courseExtras, byte[] file) throws JSONException {

        log.info("Inserting course creation request to database...");

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Course c = null;

        if(request == null) {
            request = new Request();
            c = new Course();
        }
        else{
            c = courseRepository.findById(request.getTargetId());
        }

        c.setName((String) course.get("name"));
        c.setNumber((Integer.parseInt((String)course.get("number"))));
        c.setTitle((String) course.get("title"));
        c.setCredits(Double.valueOf(String.valueOf(course.get("credits"))));
        c.setDescription((String) course.get("description"));
        c.setLevel((Integer) course.get("level"));
        c.setNote((String) course.get("note"));
        c.setLabHours(Double.valueOf(String.valueOf(course.get("labHours"))));
        c.setTutorialHours(Double.valueOf(String.valueOf(course.get("tutorialHours"))));
        c.setLectureHours(Double.valueOf(String.valueOf(course.get("lectureHours"))));
        c.setIsActive(0);

        if(file != null)
            c.setOutline(file);

        //c.setProgram(original.getProgram());

        courseRepository.save(c);

        // Requests
        request.setRequestType(1);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId(0);
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        request.setTitle(c.getName().toUpperCase() + c.getNumber() + "_create");

        // TODO: Degree Requirements
        /*
        ArrayList<DegreeRequirement> list = new ArrayList<>();


        for(DegreeRequirement dr : original.getDegreeRequirements()){

            DegreeRequirement cdr = new DegreeRequirement();

            cdr.setCore(dr.getCore());
            cdr.setDegree(dr.getDegree());
            cdr.setCourse(c);

            degreeRequirementRepository.save(cdr);

            dr.getDegree().getDegreeRequirements().add(cdr);

            list.add(cdr);

        }
        c.setDegreeRequirements(list);
        */

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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
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

                // handle duplicate case
                boolean isPresent = false;
                for(Requisite r : c.getRequisites()){

                    if(Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if(!isPresent)
                    requisiteRepository.save(requisite);
            }
        }

        courseRepository.save(c);

        requestRepository.save(request);


        log.info("course saved: " + c);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }


    /**
     * saves a removal request to database.
     * @param requestForm
     * @return the request id upon successful insertion into database
     * @throws JSONException
     */
    public int saveRemovalRequest(String requestForm) throws JSONException {

        log.info("Inserting course removal request to database...");
        log.info("Json received: " + requestForm);

        JSONObject json = new JSONObject(requestForm);

        JSONArray array = json.getJSONObject("params").getJSONArray("updates");

        JSONObject course = new JSONObject((String) array.getJSONObject(0).get("value"));
        JSONObject courseExtras = new JSONObject((String) array.getJSONObject(1).get("value"));

        int original_id = (Integer) course.get("id");

        if(original_id == 0)
            return 0;

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId(original_id);

        Course original = null;

        if(!o.isEmpty())
            original = o.get(0);
        else return 0;

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        if(request == null)
            request = new Request();


        // Requests
        request.setRequestType(3);
        request.setTargetType(2);
        request.setTargetId(0);
        request.setOriginalId(original.getId());
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        request.setTitle(original.getName().toUpperCase() + original.getNumber() + "_remove");

        requestRepository.save(request);

        return request.getId();
    }


    // delete course request along its requested course
    public boolean deleteCourseRequest(int requestId) {

        log.info("Delete request " + requestId + " and remove all its dependencies as well.");

        Request request = requestRepository.findByRequestId(requestId);

        if(request == null)
            return false;

        Course requested_course = courseRepository.findById(request.getTargetId());

        for(Requisite requisite: requested_course.getRequisites())
            requisiteRepository.delete(requisite);

        for(DegreeRequirement dr: requested_course.getDegreeRequirements())
            degreeRequirementRepository.delete(dr);

        courseRepository.delete(requested_course);

        requestRepository.delete(request);

        return true;
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

    public boolean saveRequestFile(byte[] file, int id){

        Request request = requestRepository.findByRequestId(id);
        Course course = courseRepository.findById(request.getTargetId());

        if(course == null) return false;

        course.setOutline(file);
        courseRepository.save(course);
        return true;
    }


    /**
     * Takes in a package id and returns all change history made to said package
     * @param id the id of the package
     * @return list of dossier revisions
     */
    public List getDossierRevisions(int id){

        log.info("Retrieving revision history for dossier " + id + ".");

        List<Object[]> revisions = requestPackageRepository.getRevisions(id);

        List<DossierRevision> versions = new ArrayList<>();

        if(revisions.isEmpty()) return null;

        for(Object[] r : revisions)
            versions.add(new DossierRevision((Integer) r[0], (Integer) r[1], (Byte) r[2], (BigInteger) r[4],
                    userRepository.findUserById((Integer) r[5]), (byte[]) r[3]));

        return versions;
    }


    /**
     * Reverts a package back to a previous state.
     * @param rev
     */
    public void revertDossier(int rev) {

        //RequestPackage requestPackage = requestPackageRepository.findByRevId(rev);
    }


    /**
     * Returns a user with the specified ID
     *
     * @param user_id
     * @return
     */
    public User getUser(int user_id) {
        log.info("getUser called with user_id " + user_id);
        return userRepository.findById(user_id);
    }

}
