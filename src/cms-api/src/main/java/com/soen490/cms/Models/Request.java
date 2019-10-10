package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
public class Request {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private int targetId;

    @Getter @Setter
    private int targetType; // 1: program, 2: course

    @Getter @Setter
    private int requestType; // 1: create, 2: update, 3: remove

    @Getter @Setter
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @OneToMany(mappedBy = "request")
    @Getter @Setter
    private Collection<SupportingDocument> supportingDocuments;

    @OneToMany(mappedBy = "request")
    @Getter @Setter
    private Collection<Approval> approvals;
}
