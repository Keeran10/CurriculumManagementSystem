package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString(exclude= {"degrees", "courses"})
public class Program {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Lob
    private String description;

    private int isActive;

    @JsonIgnoreProperties({"programs", "calendars", "requestPackages"})
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @OneToMany(mappedBy = "program")
    private List<Degree> degrees = new ArrayList<>();

    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @OneToMany(mappedBy = "program")
    private List<Course> courses = new ArrayList<>();
}