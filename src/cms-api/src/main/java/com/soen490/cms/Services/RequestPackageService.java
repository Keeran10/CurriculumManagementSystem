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

import com.itextpdf.text.DocumentException;
import com.soen490.cms.Models.*;
import com.soen490.cms.Repositories.*;
import com.soen490.cms.Services.PdfService.PdfService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;

@Service
@Transactional
@Log4j2
public class RequestPackageService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private DegreeRequirementRepository degreeRequirementRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RequisiteRepository requisiteRepository;
    @Autowired
    private RequestPackageRepository requestPackageRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private SupportingDocumentRepository supportingDocumentsRepository;
    @Autowired
    private PdfService pdfService;
    @Autowired
    private SubSection70719Repository subSection70719Repository;


    // Return package with right id, if id given is 0, a new package is created and returned
    public RequestPackage getRequestPackage(int package_id, int department_id) {

        log.info("getRequestPackage called with package_id " + package_id + " and department_id " + department_id + ".");

        if (package_id == 0)
            return getNewPackage(department_id);

        return requestPackageRepository.findById(package_id);
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSection70719JSON       Stringified subsection70719 JSON received from client
     * @param sectionExtrasJSON Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @return True if susection70719 has been successfully added to database.
     * @throws JSONException
     */
    public boolean saveSubsection70719(String subSection70719JSON, String sectionExtrasJSON, MultipartFile[] files) throws JSONException {

        log.info("Json substring70719 received: " + subSection70719JSON);
        log.info("Json subsectionExtras received: " + sectionExtrasJSON);
        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }
        if(subSection70719JSON != null && !(subSection70719JSON.equals(""))) {
            JSONObject subsubSection70719JSONObject = new JSONObject(subSection70719JSON);

            SubSection70719 subSection70719 = null;
            subSection70719.setSecond_core((String) subsubSection70719JSONObject.get("Second_core"));
            subSection70719.setFirst_core((String) subsubSection70719JSONObject.get("First_core"));
            subSection70719.setFirst_core_courses(null);
            subSection70719.setFirst_paragraph((String) subsubSection70719JSONObject.get("First_paragraph"));
            subSection70719.setSection_id((String) subsubSection70719JSONObject.get("Section_title"));
            subSection70719.setSection_title((String) subsubSection70719JSONObject.get("Section_title"));

            subSection70719Repository.save(subSection70719);
            return true;
        }
        else
            return false;
    }
        /**
         * Saves an edited course to the database.
         *
         * @param courseJSON       Stringified course JSON received from client
         * @param courseExtrasJSON Stringified course JSON received from client
         * @param files            uploaded course outline
         * @return True if course has been successfully added to database.
         * @throws JSONException
         */
    public int saveCourseRequest(String courseJSON, String courseExtrasJSON, MultipartFile[] files) throws JSONException {

        log.info("Json course received: " + courseJSON);
        log.info("Json courseExtras received: " + courseExtrasJSON);
        for (MultipartFile file : files)
            log.info("File received received: " + file.getOriginalFilename());

        JSONObject course = new JSONObject(courseJSON);
        JSONObject courseExtras = new JSONObject(courseExtrasJSON);

        int original_id = (Integer) course.get("id");

        if (original_id == 0)
            return saveCreateRequest(course, courseExtras, files);

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId(original_id);

        Course original = null;

        if (!o.isEmpty())
            original = o.get(0);
        else return 0;

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Course c = null;

        if (request == null) {
            request = new Request();
            c = new Course();
        } else {
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

        courseRepository.save(c);

        try {
            saveSupportingDocument(files, "course", c.getId(), user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        if (c.getDegreeRequirements() == null) {

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

        setRequisites(c, pre, co, anti, eq);

        courseRepository.save(c);

        requestRepository.save(request);


        log.info("course saved: " + c);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }


    /**
     * Saves a newly created course to the database.
     *
     * @param course, courseExtras
     * @return request_id if course and request have been successfully added to database.
     * @throws JSONException
     */
    public int saveCreateRequest(JSONObject course, JSONObject courseExtras, MultipartFile[] files) throws JSONException {

        log.info("Inserting course creation request to database...");

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Course c = null;

        if (request == null) {
            request = new Request();
            c = new Course();
        } else {
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

        courseRepository.save(c);

        try {
            saveSupportingDocument(files, "course", c.getId(), user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

        setRequisites(c, pre, co, anti, eq);

        courseRepository.save(c);

        requestRepository.save(request);


        log.info("course saved: " + c);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }


    public int saveRemovalRequest(String courseJSON, String courseExtrasJSON, MultipartFile[] files) throws JSONException {

        JSONObject course = new JSONObject(courseJSON);
        JSONObject courseExtras = new JSONObject(courseExtrasJSON);

        int original_id = (Integer) course.get("id");

        if (original_id == 0)
            return 0;

        // Changed Course and Original Course
        List<Course> o = courseRepository.findByJsonId(original_id);

        Course original = null;

        if (!o.isEmpty())
            original = o.get(0);
        else return 0;

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        if (request == null)
            request = new Request();

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

        try {
            saveSupportingDocument(files, "course", original.getId(), user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestRepository.save(request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }


    // delete course request along its requested course
    public boolean deleteCourseRequest(int requestId) {

        log.info("Delete request " + requestId + " and remove all its dependencies as well.");

        Request request = requestRepository.findByRequestId(requestId);

        if (request == null)
            return true;

        int user_id = request.getUser().getId();
        int package_id = request.getRequestPackage().getId();

        if(request.getRequestType() == 3){

            requestRepository.delete(request);
            generatePdf(user_id, package_id);
            return true;
        }

        Course requested_course = courseRepository.findById(request.getTargetId());
        courseRepository.delete(requested_course);
        requestRepository.delete(request);
        generatePdf(user_id, package_id);
        return true;
    }


    // returns list of packages
    public List<RequestPackage> getRequestPackagesByDepartment(int department_id) {

        return requestPackageRepository.findByDepartment(department_id);
    }


    public RequestPackage findById(int id) {
        return requestPackageRepository.findById(id);
    }


    // Called when package id received is 0.
    private RequestPackage getNewPackage(int department_id) {

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
     * Takes in a package id and returns all change history made to said package
     *
     * @param id the id of the package
     * @return list of dossier revisions
     */
    public List getDossierRevisions(int id) {

        log.info("Retrieving revision history for dossier " + id + ".");

        List<Object[]> revisions = requestPackageRepository.getRevisions(id);

        List<DossierRevision> versions = new ArrayList<>();

        if (revisions.isEmpty()) return null;

        for (Object[] r : revisions)
            versions.add(new DossierRevision((Integer) r[0], (Integer) r[1], (Byte) r[2], (BigInteger) r[3],
                    userRepository.findUserById((Integer) r[4])));

        return versions;
    }


    /**
     * Reverts a package back to a previous state.
     *
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

    // Called after any request transaction
    public void generatePdf(int package_id, int user_id) {

        try {
            pdfService.generatePDF(package_id, user_id);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * Adds support documents to database.
     *
     * @param files      The files to be added.
     * @param targetType dossier or course.
     * @param targetId   The designated package.
     * @param userId     user who uploaded the files.
     * @return The saved supporting document object.
     * @throws IOException
     */
    public void saveSupportingDocument(MultipartFile[] files, String targetType, int targetId, int userId) throws IOException {

        for (MultipartFile file : files) {

            log.info("add supporting document " + file.getOriginalFilename());

            SupportingDocument supportingDocument = new SupportingDocument();

            supportingDocument.setUserId(userId);
            supportingDocument.setTargetType(targetType);
            supportingDocument.setTargetId(targetId);
            supportingDocument.setFileName(file.getOriginalFilename());
            supportingDocument.setFileType(file.getContentType());
            supportingDocument.setFile(file.getBytes());

            supportingDocumentsRepository.save(supportingDocument);
        }

    }

    /**
     * Saves the requisites to database
     *
     * @param c    target course
     * @param pre  pre-requisite
     * @param co   co-requisite
     * @param anti anti-requisite
     * @param eq   equivalent requisite
     */
    private void setRequisites(Course c, String pre, String co, String anti, String eq) {

        String[] prerequisites = pre.split(";|\\,");
        String[] corequisites = co.split(";|\\,");
        String[] antirequisites = anti.split(";|\\,");
        String[] equivalents = eq.split(";|,|or");

        for (String prerequisite : prerequisites) {

            prerequisite = prerequisite.trim();

            if (prerequisite.length() >= 7) {

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                if (prerequisite.startsWith("credits", 3)) {
                    requisite.setName(prerequisite);
                    requisite.setNumber(0);
                } else {
                    requisite.setName(prerequisite.substring(0, 4).trim());
                    requisite.setNumber(Integer.parseInt(prerequisite.substring(4).trim()));
                }
                requisite.setType("prerequisite");

                // handle duplicate case
                boolean isPresent = false;
                for (Requisite r : c.getRequisites()) {

                    if (Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if (!isPresent)
                    requisiteRepository.save(requisite);
            }

        }
        for (String corequisite : corequisites) {

            corequisite = corequisite.trim();

            if (corequisite.length() >= 7) {

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(corequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(corequisite.substring(4).trim()));
                requisite.setType("corequisite");

                // handle duplicate case
                boolean isPresent = false;
                for (Requisite r : c.getRequisites()) {

                    if (Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if (!isPresent)
                    requisiteRepository.save(requisite);
            }
        }
        for (String antirequisite : antirequisites) {

            antirequisite = antirequisite.trim();

            if (antirequisite.length() >= 7) {

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(antirequisite.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(antirequisite.substring(4).trim()));
                requisite.setType("antirequisite");

                // handle duplicate case
                boolean isPresent = false;
                for (Requisite r : c.getRequisites()) {

                    if (Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if (!isPresent)
                    requisiteRepository.save(requisite);
            }
        }
        for (String equivalent : equivalents) {

            equivalent = equivalent.trim();

            if (equivalent.length() >= 7) {

                Requisite requisite = new Requisite();
                requisite.setCourse(c);
                requisite.setIsActive(0);
                requisite.setName(equivalent.substring(0, 4).trim());
                requisite.setNumber(Integer.parseInt(equivalent.substring(4).trim()));
                requisite.setType("equivalent");

                // handle duplicate case
                boolean isPresent = false;
                for (Requisite r : c.getRequisites()) {

                    if (Objects.equals(r.getType(), requisite.getType()) && r.getNumber() == requisite.getNumber() &&
                            Objects.equals(r.getName(), requisite.getName()) && r.getIsActive() == requisite.getIsActive()) {
                        isPresent = true;
                    }
                }
                if (!isPresent)
                    requisiteRepository.save(requisite);
            }
        }
    }


    /**
     * All requested changes are processed and the dossier is subsequently deleted.
     *
     * @param dossier approved and ready to be finalized.
     */
    public void finalizeDossierRequests(RequestPackage dossier) {

        for (Request r : dossier.getRequests()) {

            if (r.getTargetType() == 1)
                continue;

            if (r.getRequestType() == 1)
                finalizeCourseCreationRequest(r);
            if (r.getRequestType() == 2)
                finalizeCourseUpdateRequest(r);
            if (r.getRequestType() == 3)
                finalizeCourseRemovalRequest(r);

        }

        log.info("Deleting dossier from database: " + dossier);
        requestPackageRepository.delete(dossier);
    }


    private void finalizeCourseUpdateRequest(Request r) {

        Course original = courseRepository.findById(r.getOriginalId());
        Course changed = courseRepository.findById(r.getTargetId());

        original.setName(changed.getName());
        original.setNumber(changed.getNumber());
        original.setTitle(changed.getTitle());
        original.setDescription(changed.getDescription());
        original.setCredits(changed.getCredits());

        original.setProgram(changed.getProgram());

        for (Requisite original_requisite : original.getRequisites())
            requisiteRepository.delete(original_requisite);

        original.setRequisites(changed.getRequisites());

        for (Requisite requisite : original.getRequisites())
            requisite.setCourse(original);

        // override degree requirements
        for (DegreeRequirement dro : original.getDegreeRequirements())
            degreeRequirementRepository.delete(dro);

        original.setDegreeRequirements(changed.getDegreeRequirements());

        for (DegreeRequirement dr : original.getDegreeRequirements())
            dr.setCourse(original);

        log.info("Modifying course in database: " + original);

        courseRepository.save(original);
        courseRepository.delete(changed);
    }


    private void finalizeCourseCreationRequest(Request r) {

        Course newcourse = courseRepository.findById(r.getTargetId());
        newcourse.setIsActive(1);
        log.info("Saving new course in database: " + newcourse);
        courseRepository.save(newcourse);
    }


    private void finalizeCourseRemovalRequest(Request r) {

        Course course = courseRepository.findById(r.getOriginalId());
        log.info("Removing existing course from database: " + course);
        courseRepository.delete(course);
    }


    // creates a new dossier and saves it to database
    public RequestPackage addDossier(int userId) {

        User user = userRepository.findById(userId);

        Department department = user.getDepartment();

        RequestPackage requestPackage = new RequestPackage();

        requestPackage.setDepartment(department);
        requestPackage.setUserId(user.getId());

        requestPackageRepository.save(requestPackage);

        return requestPackage;
    }


    // deletes dossier from database
    public boolean deleteDossier(int id) {

        RequestPackage requestPackage = requestPackageRepository.findById(id);

        for (Request r : requestPackage.getRequests()) {

            if (r.getRequestType() != 3) {
                Course course = courseRepository.findById(r.getTargetId());
                courseRepository.delete(course);
            }
        }
        requestPackageRepository.delete(requestPackage);
        return true;
    }


    // return dossier pdf for a specific revision
    public byte[] getRevPDF(int rev_id) {

        return requestPackageRepository.getPdfByRevision(rev_id);
    }
}
