package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int number;

    @Getter @Setter
    private int level;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private double credits;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private double lectureHours;

    @Getter @Setter
    private double tutorialHours;

    @Getter @Setter
    private double labHours;

    @Lob
    @Getter @Setter
    private byte[] outline;

    @Getter @Setter
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @Getter @Setter
    private Program program;

    @OneToMany(mappedBy = "course")
    @Getter @Setter
    private Collection<Requisite> requisites;

    @ManyToMany(mappedBy = "requiredCourses")
    @Getter @Setter
    Collection<Degree> requirements;

    @ManyToMany(mappedBy = "electiveCourses")
    @Getter @Setter
    Collection<Degree> electives;
}
