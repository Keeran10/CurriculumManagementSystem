package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class RequestPackage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private byte[] pdfFile;

    @JsonIgnoreProperties("requestPackages")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private Collection<Request> requests = new ArrayList<>();

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private Collection<SupportingDocument> supportingDocuments = new ArrayList<>();

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy = "requestPackage")
    private Collection<Approval> approvals = new ArrayList<>();
}
