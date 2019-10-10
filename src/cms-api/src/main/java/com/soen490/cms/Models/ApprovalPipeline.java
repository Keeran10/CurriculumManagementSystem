package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApprovalPipeline {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private int SENATE;

    @Getter @Setter
    private int APC;

    @Getter @Setter
    private int DCC;

    @Getter @Setter
    private int FCC;

    @Getter @Setter
    private int UGDC;

    @Getter @Setter
    private int GDC;
}
