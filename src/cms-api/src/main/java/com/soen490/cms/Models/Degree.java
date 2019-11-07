package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonIgnoreProperties("degree")
    @OneToMany(mappedBy = "degree")
    private List<DegreeRequirement> degreeRequirements = new ArrayList<>();
}
