package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"programs", "calendar", "requestPackages"})
@EqualsAndHashCode(exclude = "calendar")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @JsonIgnoreProperties({"departments", "calendars"})
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @JsonIgnoreProperties({"department", "degrees", "courses"})
    @OneToMany(mappedBy = "department")
    private Collection<Program> programs;

    @JsonIgnoreProperties({"department", "requests", "supportingDocuments", "approvals"})
    @OneToMany(mappedBy = "department")
    private Collection<RequestPackage> requestPackages;

    @JsonBackReference
    @OneToOne(mappedBy = "department")
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
}