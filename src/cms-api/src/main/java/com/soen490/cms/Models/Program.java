package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Program {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Column @Getter @Setter
    private String name;

    @Column @Getter @Setter
    private String description;

    @Column @Getter @Setter
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @Getter @Setter
    private Department department;

    @OneToMany(mappedBy = "program")
    @Getter @Setter
    private Collection<Degree> degrees;

    @OneToMany(mappedBy = "program")
    @Getter @Setter
    private Collection<Course> courses;
}