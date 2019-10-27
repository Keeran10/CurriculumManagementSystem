package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Faculty {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty")
    private Collection<Department> departments;

    @JsonManagedReference
    @OneToMany(mappedBy = "faculty")
    private Collection<Calendar> calendars;
}
