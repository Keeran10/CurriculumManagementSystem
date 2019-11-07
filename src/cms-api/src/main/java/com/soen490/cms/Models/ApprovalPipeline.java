package com.soen490.cms.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class ApprovalPipeline {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int departmentCurriculumCommittee;

    private int departmentCouncil;

    private int undergraduateStudiesCommittee; //

    private int facultyCouncil;

    private int APC;

    private int senate;

    private int schoolOfGraduateStudies;

    private int graduateStudiesCommittee;

    @OneToMany(mappedBy = "approvalPipeline")
    Collection<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages;
}
