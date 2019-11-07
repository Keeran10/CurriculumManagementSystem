package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
@EqualsAndHashCode(exclude = "department")
public class Calendar {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String sectionId;

    private String sectionTitle;

    @Lob
    private String body;

    private String sectionType; // faculty | faculty_list (profs) | courses | general

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
