package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
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

    @JsonIgnoreProperties({"faculty"})
    @OneToMany(mappedBy = "faculty")
    private Collection<Department> departments;

    @JsonIgnoreProperties({"faculty"})
    @OneToMany(mappedBy = "faculty")
    private Collection<Calendar> calendars;
}
