package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class DegreeRequirement implements Serializable{

    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @Id
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @JsonIgnoreProperties({"program", "degreeRequirements"})
    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String core;
}
