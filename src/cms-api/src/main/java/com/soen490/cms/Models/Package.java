package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Package {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnoreProperties({"packages"})
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonManagedReference
    @OneToMany(mappedBy = "requestPackage")
    private Collection<Request> requests;

    @JsonManagedReference
    @OneToMany(mappedBy = "aPackage")
    private Collection<SupportingDocument> supportingDocuments;

    @JsonManagedReference
    @OneToMany(mappedBy = "aPackage")
    private Collection<Approval> approvals;
}
