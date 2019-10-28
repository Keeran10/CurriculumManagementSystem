package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"degrees", "courses"})
public class Program {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String description;

    private int isActive;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @JsonBackReference
    @OneToMany(mappedBy = "program")
    private Collection<Degree> degrees;

    @JsonBackReference
    @OneToMany(mappedBy = "program")
    private Collection<Course> courses;
}