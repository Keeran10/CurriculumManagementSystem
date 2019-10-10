package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Requisite {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private int requisiteCourseId; // must be handled in repository call

    @Getter @Setter
    private int type; // 1: pre, 2: co, 3: anti, 4: equivalent

    @Getter @Setter
    private int isActive;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @Getter @Setter
    private Course course;
}
