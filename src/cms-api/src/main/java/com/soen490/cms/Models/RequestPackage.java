package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class RequestPackage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private byte[] pdfFile;

    @JsonIgnoreProperties({"requestPackages", "users"})
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private List<Request> requests = new ArrayList<>();

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private List<SupportingDocument> supportingDocuments = new ArrayList<>();

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private List<Approval> approvals = new ArrayList<>();

    @OneToMany(mappedBy =  "requestPackage")
    private List<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages = new ArrayList<>();
}
