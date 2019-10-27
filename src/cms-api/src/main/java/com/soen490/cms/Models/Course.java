package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int number;

    private int level;

    private String title;

    private double credits;

    private String description;

    private double lectureHours;

    private double tutorialHours;

    private double labHours;

    private String note;

    @Lob
    private byte[] outline;

    private int isActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private Collection<Requisite> requisites;

    @JsonBackReference
    @ManyToMany(mappedBy = "requiredCourses")
    Collection<Degree> requirements;

    @JsonBackReference
    @ManyToMany(mappedBy = "electiveCourses")
    Collection<Degree> electives;

    @Transient
    ArrayList<String> prerequisites;

    @Transient
    ArrayList<String> corequisites;

    @Transient
    ArrayList<String> antirequisites;

    @Transient
    ArrayList<String> equivalent;

    public Course(){
        prerequisites = new ArrayList<>();
        corequisites = new ArrayList<>();
        antirequisites = new ArrayList<>();
        equivalent = new ArrayList<>();
    }
}
