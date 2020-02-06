package com.soen490.cms.Models.Sections;

import com.soen490.cms.Models.Course;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Section71705 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // i.e. "70.71.9"

    private String sectionTitle; // "Degree Requirement for Beng ..."

    private String firstCore; // Engineering Core

    @Transient
    List<Course> firstCoreCourses = new ArrayList<>(); // null since we don't have ENGR courses

    private String firstParagraph;

    private int isActive;

}
