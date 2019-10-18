package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Requisite {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int requisiteCourseId; // must be handled in repository call

    private int type; // 1: pre, 2: co, 3: anti, 4: equivalent

    private int isActive;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
