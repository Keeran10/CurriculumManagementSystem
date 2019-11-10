package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int number;

    private String title;

    private double credits;

    private String note;

    private int level;

    private double lectureHours;

    private double tutorialHours;

    private double labHours;

    @Lob
    private String description;

    @Lob
    private byte[] outline;

    private int isActive;

    @JsonIgnoreProperties({"courses", "degrees"})
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonManagedReference
    @OneToMany(mappedBy = "course")
    private List<Requisite> requisites = new ArrayList<>();

    @JsonIgnoreProperties("course")
    @OneToMany(mappedBy = "course")
    private List<DegreeRequirement> degreeRequirements = new ArrayList<>();

}
