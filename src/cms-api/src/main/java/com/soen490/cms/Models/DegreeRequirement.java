package com.soen490.cms.Models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
public class DegreeRequirement implements Serializable{

    @Id
    @ManyToOne
    @JoinColumn(name = "degree_id")
    private Degree degree;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String core;
}
