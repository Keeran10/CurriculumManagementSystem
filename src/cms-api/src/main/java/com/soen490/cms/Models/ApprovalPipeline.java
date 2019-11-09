package com.soen490.cms.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class ApprovalPipeline {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int departmentCurriculumCommittee;

    private int departmentCouncil;

    private int undergraduateStudiesCommittee; // Associate Dean Academic Programs Under Graduate Studies Committee

    private int facultyCouncil;

    private int APC;

    private int senate;

    @OneToMany(mappedBy = "approvalPipeline")
    Collection<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(o.getClass() != this.getClass() || o == null) return false;

        ApprovalPipeline approvalPipeline = (ApprovalPipeline)o;

        if(this.departmentCurriculumCommittee != approvalPipeline.departmentCurriculumCommittee
            || this.departmentCouncil != approvalPipeline.departmentCouncil
            || this.undergraduateStudiesCommittee != approvalPipeline.undergraduateStudiesCommittee
            || this.facultyCouncil != approvalPipeline.facultyCouncil
            || this.APC != approvalPipeline.APC
            || this.senate != approvalPipeline.senate) {
            return false;
        } else {
            return true;
        }
    }
}
