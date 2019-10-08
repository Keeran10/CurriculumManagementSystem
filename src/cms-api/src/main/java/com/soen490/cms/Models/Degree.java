package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Degree {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private int level; // 1: bachelor, 2: master, 3: phd, etc ...

    @Getter @Setter
    private double credits;

    @ManyToOne
    @JoinColumn(name = "program_id")
    @Getter @Setter
    private Program program;

    @ManyToMany
    @JoinTable(
            name = "required_course",
            joinColumns = @JoinColumn(name = "degree_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Getter @Setter
    Collection<Course> requiredCourses;

    @ManyToMany
    @JoinTable(
            name = "elective_course",
            joinColumns = @JoinColumn(name = "degree_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Getter @Setter
    Collection<Course> electiveCourses;
}
