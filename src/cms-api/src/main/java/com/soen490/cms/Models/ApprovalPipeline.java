package com.soen490.cms.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ApprovalPipeline {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int SENATE;

    private int APC;

    private int DCC;

    private int FCC;

    private int UGDC;

    private int GDC;
}
