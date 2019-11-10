package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Data
@ToString(exclude= {"degree", "course"})
public class DegreeRequirement{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String core;

    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
