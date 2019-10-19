package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Data
public class Request {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int targetId;

    private int originalId; // original target Id if its a edit requests

    private int targetType; // 1: program, 2: course

    private int requestType; // 1: create, 2: update, 3: remove

    private Timestamp timestamp;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package requestPackage; // package is a reserved keyword

    @JsonManagedReference
    @OneToMany(mappedBy = "request")
    private Collection<SupportingDocument> supportingDocuments;

    @JsonManagedReference
    @OneToMany(mappedBy = "request")
    private Collection<Approval> approvals;
}
