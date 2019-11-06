package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@ToString(exclude= {"degree", "course"})
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

    @NotFound(action = NotFoundAction.IGNORE)
    private String core;
}
