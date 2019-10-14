package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Program {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private int isActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonManagedReference
    @OneToMany(mappedBy = "program")
    private Collection<Degree> degrees;

    @JsonManagedReference
    @OneToMany(mappedBy = "program")
    private Collection<Course> courses;
}