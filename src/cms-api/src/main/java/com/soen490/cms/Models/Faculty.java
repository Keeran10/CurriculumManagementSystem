package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"departments", "calendars"})
public class Faculty {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "faculty")
    private Collection<Department> departments;

    @JsonBackReference
    @OneToMany(mappedBy = "faculty")
    private Collection<Calendar> calendars;
}
