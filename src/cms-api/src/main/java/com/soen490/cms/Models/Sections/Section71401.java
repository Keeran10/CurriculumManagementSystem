package com.soen490.cms.Models.Sections;

import com.soen490.cms.Models.Course;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Section71401 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // 71.40.1

    private String sectionTitle;

    private String introParagraph;

    private String engCore;

    private String mechCore;

    private String electivesDescription;

    private String firstOption; // aerospace

    private String secondOption; // design and manufacturing

    private String thirdOption; // systems and mechatronics

    private String fourthOption; // thermo-fluids and propulsion

    private String fifthOption; // vehicle systems

    private String sixthOption; // stress analysis

    @Transient
    List<Course> engCoreCourses = new ArrayList<>();

    @Transient
    List<Course> mechCoreCourses = new ArrayList<>();

    @Transient
    List<Course> firstOptionCourses = new ArrayList<>();

    @Transient
    List<Course> sectionOptionCourses = new ArrayList<>();

    @Transient
    List<Course> thirdOptionCourses = new ArrayList<>();

    @Transient
    List<Course> fourthOptionCourses = new ArrayList<>();

    @Transient
    List<Course> fifthOptionCourses = new ArrayList<>();

    @Transient
    List<Course> sixthOptionCourses = new ArrayList<>();
}
