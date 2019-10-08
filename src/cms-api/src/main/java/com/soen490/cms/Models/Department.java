package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Department {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @Getter @Setter
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    @Getter @Setter
    private Collection<Program> programs;
}