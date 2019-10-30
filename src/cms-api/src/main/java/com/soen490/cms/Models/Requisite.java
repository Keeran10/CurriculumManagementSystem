package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = "course")
public class Requisite {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private int number;

    private String type;

    private int isActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
