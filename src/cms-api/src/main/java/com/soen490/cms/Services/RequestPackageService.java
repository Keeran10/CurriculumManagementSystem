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
import com.soen490.cms.Models.Sections.*;
import com.soen490.cms.Repositories.*;
import com.soen490.cms.Repositories.SectionsRepositories.*;
import com.soen490.cms.Services.PdfService.PdfService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
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
    Section71701Repository section71701Repository;
    @Autowired
    Section71702Repository section71702Repository;
    @Autowired
    Section71703Repository section71703Repository;
    @Autowired
    Section71704Repository section71704Repository;
    @Autowired
    Section71705Repository section71705Repository;
    @Autowired
    Section71706Repository section71706Repository;
    @Autowired
    Section71707Repository section71707Repository;
    @Autowired
    Section71708Repository section71708Repository;
    @Autowired
    Section71709Repository section71709Repository;
    @Autowired
    Section717010Repository section717010Repository;
    @Autowired
    private DegreeRepository degreeRepository;


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
     * @param subSections71701      Stringified subsection70711 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70711 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71701(String subSections71701, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70711 received: " + subSections71701);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);


        Section71701 section71701 = null;

        if (request == null) {
            request = new Request();
            section71701 = new Section71701();
        } else {
            section71701 = section71701Repository.findById(request.getTargetId());
        }

        JSONObject subSection71701JSON = new JSONObject(subSections71701);

       section71701.setFirstParagraph((String) subSection71701JSON.get("firstParagraph"));
        section71701.setSectionId((String) subSection71701JSON.get("sectionId"));
        section71701.setSectionTitle((String) subSection71701JSON.get("sectionTitle"));
        section71701.setIsActive(0);

        section71701Repository.save(section71701);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71701.getId());
        request.setOriginalId((Integer) subSection71701JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71701.getSectionId() + "_create");
        else
            request.setTitle(section71701.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71701 saved: " + section71701);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71702     Stringified subsection70712 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70712 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71702(String subSections71702, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70712 received: " + subSections71702);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71702 section71702 = null;

        if (request == null) {
            request = new Request();
            section71702 = new Section71702();
        } else {
            section71702 = section71702Repository.findById(request.getTargetId());
        }

        JSONObject subSection71702JSON = new JSONObject(subSections71702);

        section71702.setSecondCore((String) subSection71702JSON.get("secondCore"));
        section71702.setFirstCore((String) subSection71702JSON.get("firstCore"));
        section71702.setFirstParagraph((String) subSection71702JSON.get("firstParagraph"));
        section71702.setSectionId((String) subSection71702JSON.get("sectionId"));
        section71702.setSectionTitle((String) subSection71702JSON.get("sectionTitle"));
        section71702.setThirdCore((String) subSection71702JSON.get("thirdCore"));
        section71702.setSecondParagraph((String) subSection71702JSON.get("secondParagraph"));
        section71702.setFourthCore((String) subSection71702JSON.get("fourthCore"));
        section71702.setThirdParagraph((String) subSection71702JSON.get("thirdParagraph"));
        section71702.setFifthCore((String) subSection71702JSON.get("fifthCore"));
        section71702.setFourthParagraph((String) subSection71702JSON.get("fourthParagraph"));
        section71702.setSixthCore((String) subSection71702JSON.get("sixthCore"));
        section71702.setFifthParagraph((String) subSection71702JSON.get("fifthParagraph"));
        section71702.setSeventhCore((String) subSection71702JSON.get("seventhCore"));
        section71702.setSixthParagraph((String) subSection71702JSON.get("sixthParagraph"));
        section71702.setEightCore((String) subSection71702JSON.get("eightCore"));
        section71702.setSeventhParagraph((String) subSection71702JSON.get("seventhParagraph"));
        section71702.setNinthCore((String) subSection71702JSON.get("ninthCore"));
        section71702.setEightParagraph((String) subSection71702JSON.get("eightParagraph"));
        section71702.setTenthCore((String) subSection71702JSON.get("tenthCore"));
        section71702.setNinthParagraph((String) subSection71702JSON.get("ninthParagraph"));
        section71702.setEleventhCore((String) subSection71702JSON.get("eleventhCore"));
        section71702.setTenthParagraph((String) subSection71702JSON.get("tenthParagraph"));
        section71702.setTwelfthCore((String) subSection71702JSON.get("twelfthCore"));
        section71702.setEleventhParagraph((String) subSection71702JSON.get("eleventhParagraph"));
        section71702.setThirteenthCore((String) subSection71702JSON.get("thirteenthCore"));
        section71702.setTwelfthParagraph((String) subSection71702JSON.get("twelfthParagraph"));
        section71702.setFourteenthCore((String) subSection71702JSON.get("fourteenthCore"));
        section71702.setFifteenthCore((String) subSection71702JSON.get("fifteenthCore"));
        section71702.setThirteenthParagraph((String) subSection71702JSON.get("thirteenthParagraph"));
        section71702.setSixteenthCore((String) subSection71702JSON.get("sixteenthCore"));
        section71702.setFourteenthParagraph((String) subSection71702JSON.get("fourteenthParagraph"));
        section71702.setSeventeenthCore((String) subSection71702JSON.get("seventeenthCore"));
        section71702.setFifteenthParagraph((String) subSection71702JSON.get("fifteenthParagraph"));
        section71702.setEighteenthCore((String) subSection71702JSON.get("eighteenthCore"));
        section71702.setSixteenthParagraph((String) subSection71702JSON.get("sixteenthParagraph"));
        section71702.setNineteenthCore((String) subSection71702JSON.get("nineteenthCore"));
        section71702.setSeventeenthParagraph((String) subSection71702JSON.get("seventeenthParagraph"));
        section71702.setIsActive(0);

        section71702Repository.save(section71702);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71702.getId());
        request.setOriginalId((Integer) subSection71702JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71702.getSectionId() + "_create");
        else
            request.setTitle(section71702.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71702 saved: " + section71702);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71703      Stringified subsection70713 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70713 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71703(String subSections71703, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70713 received: " + subSections71703);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71703 section71703 = null;

        if (request == null) {
            request = new Request();
            section71703 = new Section71703();
        } else {
            section71703 = section71703Repository.findById(request.getTargetId());
        }

        JSONObject subSection71703JSON = new JSONObject(subSections71703);

        section71703.setFirstParagraph((String) subSection71703JSON.get("firstParagraph"));
        section71703.setSectionId((String) subSection71703JSON.get("sectionId"));
        section71703.setSectionTitle((String) subSection71703JSON.get("sectionTitle"));
        section71703.setIsActive(0);

        section71703Repository.save(section71703);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71703.getId());
        request.setOriginalId((Integer) subSection71703JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71703.getSectionId() + "_create");
        else
            request.setTitle(section71703.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71703 saved: " + section71703);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71704      Stringified subsection70714 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70714 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71704(String subSections71704, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70714 received: " + subSections71704);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71704 section71704 = null;

        if (request == null) {
            request = new Request();
            section71704 = new Section71704();
        } else {
            section71704 = section71704Repository.findById(request.getTargetId());
        }

        JSONObject subSection71704JSON = new JSONObject(subSections71704);

        section71704.setFirstCore((String) subSection71704JSON.get("firstCore"));
        section71704.setFirstParagraph((String) subSection71704JSON.get("firstParagraph"));
        section71704.setSecondParagraph((String) subSection71704JSON.get("secondParagraph"));
        section71704.setSectionId((String) subSection71704JSON.get("sectionId"));
        section71704.setSectionTitle((String) subSection71704JSON.get("sectionTitle"));
        section71704.setIsActive(0);

        section71704Repository.save(section71704);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71704.getId());
        request.setOriginalId((Integer) subSection71704JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71704.getSectionId() + "_create");
        else
            request.setTitle(section71704.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71704 saved: " + section71704);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71705      Stringified subsection70715 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70715 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71705(String subSections71705, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70715 received: " + subSections71705);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71705 section71705 = null;

        if (request == null) {
            request = new Request();
            section71705 = new Section71705();
        } else {
            section71705 = section71705Repository.findById(request.getTargetId());
        }

        JSONObject subSection71705JSON = new JSONObject(subSections71705);

        section71705.setFirstCore((String) subSection71705JSON.get("firstCore"));
        section71705.setFirstParagraph((String) subSection71705JSON.get("firstParagraph"));
        section71705.setSectionId((String) subSection71705JSON.get("sectionId"));
        section71705.setSectionTitle((String) subSection71705JSON.get("sectionTitle"));
        section71705.setIsActive(0);

        section71705Repository.save(section71705);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71705.getId());
        request.setOriginalId((Integer) subSection71705JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71705.getSectionId() + "_create");
        else
            request.setTitle(section71705.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71705 saved: " + section71705);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71706     Stringified subsection70716 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70716 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71706(String subSections71706, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70716 received: " + subSections71706);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71706 section71706 = null;

        if (request == null) {
            request = new Request();
            section71706 = new Section71706();
        } else {
            section71706 = section71706Repository.findById(request.getTargetId());
        }

        JSONObject subSection71706JSON = new JSONObject(subSections71706);

       section71706.setFirstParagraph((String) subSection71706JSON.get("firstParagraph"));
        section71706.setSectionId((String) subSection71706JSON.get("sectionId"));
        section71706.setSectionTitle((String) subSection71706JSON.get("sectionTitle"));
        section71706.setIsActive(0);

        section71706Repository.save(section71706);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71706.getId());
        request.setOriginalId((Integer) subSection71706JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71706.getSectionId() + "_create");
        else
            request.setTitle(section71706.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71706 saved: " + section71706);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71707      Stringified subsection70717 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70717 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71707(String subSections71707, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70717 received: " + subSections71707);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71707 section71707 = null;

        if (request == null) {
            request = new Request();
            section71707 = new Section71707();
        } else {
            section71707 = section71707Repository.findById(request.getTargetId());
        }

        JSONObject subSection71707JSON = new JSONObject(subSections71707);

        section71707.setFirstParagraph((String) subSection71707JSON.get("firstParagraph"));
        section71707.setSectionId((String) subSection71707JSON.get("sectionId"));
        section71707.setSectionTitle((String) subSection71707JSON.get("sectionTitle"));
        section71707.setIsActive(0);

        section71707Repository.save(section71707);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71707.getId());
        request.setOriginalId((Integer) subSection71707JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71707.getSectionId() + "_create");
        else
            request.setTitle(section71707.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71707 saved: " + section71707);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections71708      Stringified subsection70718 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection70718 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection71708(String subSections71708, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70718 received: " + subSections71708);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71708 section71708 = null;

        if (request == null) {
            request = new Request();
            section71708 = new Section71708();
        } else {
            section71708 = section71708Repository.findById(request.getTargetId());
        }

        JSONObject subSection71708JSON = new JSONObject(subSections71708);

        section71708.setFirstParagraph((String) subSection71708JSON.get("firstParagraph"));
        section71708.setSectionId((String) subSection71708JSON.get("sectionId"));
        section71708.setSectionTitle((String) subSection71708JSON.get("sectionTitle"));
        section71708.setIsActive(0);

        section71708Repository.save(section71708);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71708.getId());
        request.setOriginalId((Integer) subSection71708JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71708.getSectionId() + "_create");
        else
            request.setTitle(section71708.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71708 saved: " + section71708);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
         * Saves an edited course to the database.
         *
         * @param subSections71709      Stringified subsection70719 JSON received from client
         * @param sectionExtras Stringified subsection JSON received from client
         * @param files            uploaded course outline
         * @param descriptions
         * @return True if susection70719 has been successfully added to database.
         * @throws JSONException
         */
    public int saveSection71709(String subSections71709, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring70719 received: " + subSections71709);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section71709 section71709 = null;

        if (request == null) {
            request = new Request();
            section71709 = new Section71709();
        } else {
            section71709 = section71709Repository.findById(request.getTargetId());
        }

        JSONObject subSection71709JSON = new JSONObject(subSections71709);

        section71709.setFirstCore((String) subSection71709JSON.get("firstCore"));
        section71709.setSecondCore((String) subSection71709JSON.get("secondCore"));
        section71709.setThirdCore((String) subSection71709JSON.get("thirdCore"));
        section71709.setFourthCore((String) subSection71709JSON.get("fourthCore"));
        section71709.setFifthCore((String) subSection71709JSON.get("fifthCore"));
        section71709.setSixthCore((String) subSection71709JSON.get("sixthCore"));
        section71709.setSeventhCore((String) subSection71709JSON.get("seventhCore"));
        section71709.setEighthCore((String) subSection71709JSON.get("eighthCore"));
        section71709.setNinthCore((String) subSection71709JSON.get("ninthCore"));
        section71709.setFirstParagraph((String) subSection71709JSON.get("firstParagraph"));
        section71709.setSecondParagraph((String) subSection71709JSON.get("secondParagraph"));
        section71709.setThirdParagraph((String) subSection71709JSON.get("thirdParagraph"));
        section71709.setSectionId((String) subSection71709JSON.get("sectionId"));
        section71709.setSectionTitle((String) subSection71709JSON.get("sectionTitle"));
        section71709.setIsActive(0);

        section71709Repository.save(section71709);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section71709.getId());
        request.setOriginalId((Integer) subSection71709JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section71709.getSectionId() + "_create");
        else
            request.setTitle(section71709.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71709 saved: " + section71709);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }

    /**
     * Saves an edited course to the database.
     *
     * @param subSections717010      Stringified subsection707110 JSON received from client
     * @param sectionExtras Stringified subsection JSON received from client
     * @param files            uploaded course outline
     * @param descriptions
     * @return True if susection707110 has been successfully added to database.
     * @throws JSONException
     */
    public int saveSection717010(String subSections717010, String sectionExtras, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json substring717010 received: " + subSections717010);
        log.info("Json subsectionExtras received: " + sectionExtras);

        if(files != null) {
            for (MultipartFile file : files)
                log.info("File received received: " + file.getOriginalFilename());
        }

        JSONObject sectionExtrasJSON = new JSONObject(sectionExtras);

        int user_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(sectionExtrasJSON.get("requestId")));

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Section717010 section717010 = null;

        if (request == null) {
            request = new Request();
            section717010 = new Section717010();
        } else {
            section717010 = section717010Repository.findById(request.getTargetId());
        }

        JSONObject subSection717010JSON = new JSONObject(subSections717010);

        section717010.setFirstParagraph((String) subSection717010JSON.get("firstParagraph"));
        section717010.setSectionId((String) subSection717010JSON.get("sectionId"));
        section717010.setSectionTitle((String) subSection717010JSON.get("sectionTitle"));
        section717010.setIsActive(0);

        section717010Repository.save(section717010);

        // Requests
        request.setRequestType(2); // update
        request.setTargetType(1); // calendar change
        request.setTargetId(section717010.getId());
        request.setOriginalId((Integer) subSection717010JSON.get("id"));
        request.setRationale((String) sectionExtrasJSON.get("rationale"));
        request.setResourceImplications((String) sectionExtrasJSON.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(request.getId() == 0)
            request.setTitle(section717010.getSectionId() + "_create");
        else
            request.setTitle(section717010.getSectionId() + "_update");

        requestRepository.save(request);

        log.info("section71710 saved: " + section717010);
        log.info("request saved: " + request);

        requestPackage.getRequests().add(request);

        return request.getId();
    }


    /**
         * Saves an edited course to the database.
         *
         * @param courseJSON       Stringified course JSON received from client
         * @param courseExtrasJSON Stringified course JSON received from client
         * @param files            uploaded course outline
         * @param descriptions
         * @return True if course has been successfully added to database.
         * @throws JSONException
         */
    public int saveCourseRequest(String courseJSON, String courseExtrasJSON, MultipartFile[] files, String descriptions) throws JSONException {

        log.info("Json course received: " + courseJSON);
        log.info("Json courseExtras received: " + courseExtrasJSON);
        for (MultipartFile file : files)
            log.info("File received received: " + file.getOriginalFilename());

        JSONObject course = new JSONObject(courseJSON);
        JSONObject courseExtras = new JSONObject(courseExtrasJSON);

        int original_id = (Integer) course.get("id");

        Course original = null;

        if (original_id != 0)
            original = courseRepository.findById(original_id);

        int user_id = Integer.parseInt(String.valueOf(courseExtras.get("userId")));
        int package_id = Integer.parseInt(String.valueOf(courseExtras.get("packageId")));
        int request_id = Integer.parseInt(String.valueOf(courseExtras.get("requestId")));
        boolean alreadyInDossier = false;

        RequestPackage requestPackage = requestPackageRepository.findById(package_id);

        Request request = requestRepository.findByRequestId(request_id);

        User user = userRepository.findById(user_id);

        Course c = null;

        if (request == null) {
            request = new Request();
            c = new Course();
        } else {
            c = courseRepository.findById(request.getTargetId());
            alreadyInDossier = true;
        }

        c.setName((String) course.get("name"));
        c.setNumber(Integer.valueOf((String.valueOf(course.get("number")))));
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
            saveSupportingDocument(files, descriptions, "course", c.getId(), user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Requests
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId(original_id);
        request.setRationale((String) courseExtras.get("rationale"));
        request.setResourceImplications((String) courseExtras.get("implications"));
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);

        if(original != null)
            request.setTitle(original.getName().toUpperCase() + original.getNumber() + "_update");
        else
            request.setTitle(c.getName().toUpperCase() + c.getNumber() + "_create");

        // Requisites
        String pre = (String) courseExtras.get("prerequisites");
        String co = (String) courseExtras.get("corequisites");
        String anti = (String) courseExtras.get("antirequisites");
        String eq = (String) courseExtras.get("equivalents");

        setRequisites(c, pre, co, anti, eq);

        courseRepository.save(c);

        // Set degree requirements
        ArrayList<DegreeRequirement> list = new ArrayList<>();
        int size = course.getJSONArray("degreeRequirements").length();
        int ctr = 0;

        for(int i = 0; i < size; i++){

            JSONObject degreeRequirements = (JSONObject) course.getJSONArray("degreeRequirements").get(i);
            int degreeReq_id = (Integer) degreeRequirements.get("id");

            JSONObject degreeJSON = (JSONObject) degreeRequirements.get("degree");
            int degree_id = (Integer) degreeJSON.get("id");
            Degree degree = degreeRepository.findById(degree_id);
            String core = (String) degreeRequirements.get("core");

            if(ctr == 0 && degree != null){
                c.setProgram(degree.getProgram());
                courseRepository.save(c);
                ctr++;
            }

            DegreeRequirement cdr = null;

            if(degreeReq_id == 0)
                cdr = new DegreeRequirement();
            else {
                cdr = degreeRequirementRepository.findById(degreeReq_id);
                if(cdr != null && core.equals(cdr.getCore())) {
                    continue;
                }
                else{
                    cdr = new DegreeRequirement();
                }
            }

            if(core == null || degree == null)
                continue;

            cdr.setCore(core);
            cdr.setDegree(degree);
            cdr.setCourse(c);
            degreeRequirementRepository.save(cdr);
            list.add(cdr);
        }

        c.setDegreeRequirements(list);

        requestRepository.save(request);

        log.info("course saved: " + c);
        log.info("request saved: " + request);

        if(!alreadyInDossier)
            requestPackage.getRequests().add(request);

        return request.getId();
    }


    /**
     * Saves a course removal request to the database.
     *
     * @param courseJSON       Stringified course JSON received from client
     * @param courseExtrasJSON Stringified course JSON received from client
     * @param files            uploaded course outline
     * @param descriptions     file descriptions
     * @return True if course has been successfully added to database.
     * @throws JSONException
     */
    public int saveRemovalRequest(String courseJSON, String courseExtrasJSON, MultipartFile[] files, String descriptions) throws JSONException {

        JSONObject course = new JSONObject(courseJSON);
        JSONObject courseExtras = new JSONObject(courseExtrasJSON);

        int original_id = (Integer) course.get("id");

        if (original_id == 0)
            return 0;

        // Changed Course and Original Course
        Course original = courseRepository.findById(original_id);

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
            saveSupportingDocument(files, descriptions, "course", original.getId(), user_id);
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

        request.getRequestPackage().getRequests().remove(request);

        if(request.getTargetType() == 1){
            // TODO: change this to account for all sections
            section71709Repository.deleteById(request.getTargetId());
            requestRepository.delete(request);
            return true;
        }
        if(request.getRequestType() == 3){
            requestRepository.delete(request);
            generatePdf(user_id, package_id);
            return true;
        }

        courseRepository.deleteById(request.getTargetId());
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
     * @param descriptions
     *@param targetType dossier or course.
     * @param targetId   The designated package.
     * @param userId     user who uploaded the files.    @return The saved supporting document object.
     * @throws IOException
     */
    public void saveSupportingDocument(MultipartFile[] files, String descriptions, String targetType, int targetId, int userId) throws IOException, JSONException {

        HashMap<String, String> desc = new HashMap<>();

        if(descriptions != null && descriptions.length() != 0) {

            JSONArray file_desc = new JSONArray(descriptions);

            for(int i = 0; i < file_desc.length(); i++){

                JSONArray a = (JSONArray) file_desc.get(i);

                desc.put((String) a.get(0), (String) a.get(1));
            }
        }

        for (MultipartFile file : files) {

            log.info("add supporting document " + file.getOriginalFilename());

            SupportingDocument supportingDocument = new SupportingDocument();

            supportingDocument.setUserId(userId);
            supportingDocument.setTargetType(targetType);
            supportingDocument.setTargetId(targetId);
            supportingDocument.setFileName(file.getOriginalFilename());
            supportingDocument.setFileType(file.getContentType());
            supportingDocument.setFile(file.getBytes());
            supportingDocument.setFileDescription(desc.get(file.getOriginalFilename()));
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
    public void setRequisites(Course c, String pre, String co, String anti, String eq) {

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

            if (r.getTargetType() == 1) {
                finalizeSection70719Request(r);
                continue;
            }
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

    private void finalizeSection70719Request(Request r) {

        Section71709 original = section71709Repository.findById(r.getOriginalId());
        Section71709 changed = section71709Repository.findById(r.getTargetId());

        original.setSectionId(changed.getSectionId());
        original.setSectionTitle(changed.getSectionTitle());
        original.setFirstParagraph(changed.getFirstParagraph());

        if(!original.getFirstCore().equals(changed.getFirstCore())){
            overrideCore(original.getFirstCore(), changed.getFirstCore());
            original.setFirstCore(changed.getFirstCore());
        }

        if(!original.getSecondCore().equals(changed.getSecondCore())){
            overrideCore(original.getSecondCore(), changed.getSecondCore());
            original.setSecondCore(changed.getSecondCore());
        }

        section71709Repository.save(original);
        section71709Repository.delete(changed);
    }

    private void overrideCore(String presentCore, String proposedCore) {

        List<DegreeRequirement> degreeRequirements = degreeRequirementRepository.findByCore(presentCore);

        for(DegreeRequirement degreeRequirement : degreeRequirements){
            degreeRequirement.setCore(proposedCore);
            degreeRequirementRepository.save(degreeRequirement);
        }
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

    public SupportingDocument getSupportingDocument(int file_id) {

        return supportingDocumentsRepository.findById(file_id);
    }

    public List<SupportingDocument> getSupportingDocuments(int target_id, String target_type) {

        return supportingDocumentsRepository.findByTarget(target_type, target_id);
    }

    public boolean removeSupportingDocument(int file_id) {
        supportingDocumentsRepository.deleteById(file_id);
        return true;
    }

    public void processCoreRequests(JSONArray core_additions, JSONArray core_removals,
                                    String add_to_core, String remove_from_core, int user_id, int dossier_id) throws JSONException {

        RequestPackage requestPackage = requestPackageRepository.findById(dossier_id);
        User user = userRepository.findById(user_id);

        for(int i=0; i < core_additions.length(); i++)
            saveCourseCoreAdditionRequest(add_to_core, (int) core_additions.get(i), user, requestPackage);

        for(int i=0; i < core_removals.length(); i++)
            saveCourseCoreRemovalRequest(remove_from_core, (int) core_removals.get(i), user, requestPackage);

    }


    private void saveCourseCoreAdditionRequest(String add_to_core, int course_id, User user, RequestPackage requestPackage) {

        Course o = courseRepository.findById(course_id);
        Course c = new Course();

        c.setName(o.getName());
        c.setNumber(o.getNumber());
        c.setTitle(o.getTitle());
        c.setCredits(o.getCredits());
        c.setDescription(o.getDescription());
        c.setLevel(o.getLevel());
        c.setNote(o.getNote());
        c.setLabHours(o.getLabHours());
        c.setTutorialHours(o.getTutorialHours());
        c.setLectureHours(o.getLectureHours());
        c.setIsActive(0);

        courseRepository.save(c);

        for(Requisite requisite : o.getRequisites()){
            Requisite r = new Requisite();
            r.setCourse(c);
            r.setType(requisite.getType());
            r.setNumber(requisite.getNumber());
            r.setName(requisite.getName());
            r.setIsActive(0);
            requisiteRepository.save(r);
            c.getRequisites().add(r);
        }
        for(DegreeRequirement degreeRequirement : o.getDegreeRequirements()){
            DegreeRequirement dr = new DegreeRequirement();
            dr.setCourse(c);
            dr.setDegree(degreeRequirement.getDegree());
            dr.setCore(degreeRequirement.getCore());
            degreeRequirementRepository.save(dr);
            c.getDegreeRequirements().add(dr);
        }

        // Add new core to course
        DegreeRequirement dr = new DegreeRequirement();
        dr.setCore(add_to_core);
        int degree_id = degreeRequirementRepository.findDegreeByCore(add_to_core).get(0);
        dr.setDegree(degreeRepository.findById(degree_id));
        dr.setCourse(c);
        degreeRequirementRepository.save(dr);
        c.getDegreeRequirements().add(dr);

        courseRepository.save(c);

        Request request = new Request();
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId(o.getId());
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);
        request.setTitle(o.getName().toUpperCase() + o.getNumber() + "_add_to_" + add_to_core);
        requestRepository.save(request);
        requestPackage.getRequests().add(request);
    }


    private void saveCourseCoreRemovalRequest(String remove_from_core, int course_id, User user, RequestPackage requestPackage) {

        Course o = courseRepository.findById(course_id);
        Course c = new Course();

        c.setName(o.getName());
        c.setNumber(o.getNumber());
        c.setTitle(o.getTitle());
        c.setCredits(o.getCredits());
        c.setDescription(o.getDescription());
        c.setLevel(o.getLevel());
        c.setNote(o.getNote());
        c.setLabHours(o.getLabHours());
        c.setTutorialHours(o.getTutorialHours());
        c.setLectureHours(o.getLectureHours());
        c.setIsActive(0);

        courseRepository.save(c);

        for(Requisite requisite : o.getRequisites()){
            Requisite r = new Requisite();
            r.setCourse(c);
            r.setType(requisite.getType());
            r.setNumber(requisite.getNumber());
            r.setName(requisite.getName());
            r.setIsActive(0);
            requisiteRepository.save(r);
            c.getRequisites().add(r);
        }
        for(DegreeRequirement degreeRequirement : o.getDegreeRequirements()){

            if(degreeRequirement.getCore().equals(remove_from_core))
                continue;

            DegreeRequirement dr = new DegreeRequirement();
            dr.setCourse(c);
            dr.setDegree(degreeRequirement.getDegree());
            dr.setCore(degreeRequirement.getCore());
            degreeRequirementRepository.save(dr);
            c.getDegreeRequirements().add(dr);
        }

        courseRepository.save(c);

        Request request = new Request();
        request.setRequestType(2);
        request.setTargetType(2);
        request.setTargetId(c.getId());
        request.setOriginalId(o.getId());
        request.setTimestamp(new Timestamp(System.currentTimeMillis()));
        request.setUser(user);
        request.setRequestPackage(requestPackage);
        request.setTitle(o.getName().toUpperCase() + o.getNumber() + "_remove_from_" + remove_from_core);
        requestRepository.save(request);
        requestPackage.getRequests().add(request);
    }
}
