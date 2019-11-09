package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@ToString(exclude= {"requestPackage"})
public class Request {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int targetId;

    private int originalId; // original target Id if its a edit requests

    private int targetType; // 1: program, 2: course

    private int requestType; // 1: create, 2: update, 3: remove

    private String rationale;

    private String resourceImplications;

    private Timestamp timestamp;

    @JsonIgnoreProperties({"supportingDocuments", "approvals", "requests"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"supportingDocuments", "approvals", "requests"})
    @ManyToOne
    @JoinColumn(name = "package_id")
    private RequestPackage requestPackage; // package is a reserved keyword

}
