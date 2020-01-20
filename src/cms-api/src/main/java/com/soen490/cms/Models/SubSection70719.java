package com.soen490.cms.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubSection70719 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String section_id; // i.e. "70.71.9"

    private String section_title; // "Degree Requirement for Beng ..."

    private String first_paragraph;

    private String first_core; // Engineering Core

    private String second_core; // Software Engineering Core


    @Transient
    List<Course> first_core_courses = new ArrayList<>(); // null since we don't have ENGR courses

    @Transient
    List<Course> second_core_courses = new ArrayList<>(); // retrieve from database before sending

}
