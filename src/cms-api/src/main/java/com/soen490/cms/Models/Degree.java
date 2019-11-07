package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"degreeRequirements", "program"})
public class Degree {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int level; // 1: bachelor, 2: master, 3: phd, etc ...

    private double credits;

    @JsonIgnoreProperties({"degrees", "courses"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnoreProperties("degree")
    @OneToMany(mappedBy = "degree")
    Collection<DegreeRequirement> degreeRequirements;
}
