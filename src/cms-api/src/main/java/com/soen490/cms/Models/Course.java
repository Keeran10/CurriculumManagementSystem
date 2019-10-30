package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"requirements", "electives"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int number;

    private int level;

    private String title;

    private double credits;

    @Lob
    private String description;

    private double lectureHours;

    private double tutorialHours;

    private double labHours;

    private String note;

    @Lob
    private byte[] outline;

    private int isActive;

    @JsonManagedReference
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
}
