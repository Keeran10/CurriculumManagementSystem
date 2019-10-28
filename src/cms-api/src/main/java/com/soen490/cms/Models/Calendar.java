package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String sectionId;

    private String sectionTitle;

    private String body;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String sectionType; // faculty | faculty_list (profs) | courses | general
}
