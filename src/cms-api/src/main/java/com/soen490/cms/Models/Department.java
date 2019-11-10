package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString(exclude= {"programs", "calendar", "requestPackages", "users"})
@EqualsAndHashCode(exclude = "calendar")
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @JsonIgnoreProperties({"departments", "calendars"})
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @JsonIgnoreProperties("department")
    @OneToMany(mappedBy = "department")
    private List<User> users = new ArrayList<>();

    @JsonIgnoreProperties({"department", "degrees", "courses"})
    @OneToMany(mappedBy = "department")
    private List<Program> programs = new ArrayList<>();

    @JsonIgnoreProperties({"department", "requests", "supportingDocuments", "approvals"})
    @OneToMany(mappedBy = "department")
    private List<RequestPackage> requestPackages = new ArrayList<>();

    @JsonBackReference
    @OneToOne(mappedBy = "department")
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;
}