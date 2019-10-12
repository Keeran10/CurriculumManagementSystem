package com.soen490.cms.Models;

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

    private int targetType; // 1: program, 2: course

    private int requestType; // 1: create, 2: update, 3: remove

    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "request")
    private Collection<SupportingDocument> supportingDocuments;

    @OneToMany(mappedBy = "request")
    private Collection<Approval> approvals;
}
