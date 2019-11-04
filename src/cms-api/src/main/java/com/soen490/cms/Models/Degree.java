package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Degree {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int level; // 1: bachelor, 2: master, 3: phd, etc ...

    private double credits;
    
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    @JsonManagedReference
    @JsonIgnoreProperties("degree")
    @OneToMany(mappedBy = "degree")
    Collection<DegreeRequirement> degreeRequirements;
}
