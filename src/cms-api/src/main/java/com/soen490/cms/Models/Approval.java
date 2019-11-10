package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Approval {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String feedback;

    private int status; // 1: not_viewed, 2: in_review, 3: change_requested, 4: rejected, 5: approved

    private int isLocked; // 0: no, 1: yes

    private Timestamp timestamp;

    @JsonIgnoreProperties({"supportingDocuments", "approvals", "requests"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"supportingDocuments", "approvals", "requests"})
    @ManyToOne
    @JoinColumn(name = "package_id")
    private RequestPackage requestPackage;
}
