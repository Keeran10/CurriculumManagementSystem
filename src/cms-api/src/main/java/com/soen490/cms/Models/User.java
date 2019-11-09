package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString(exclude= {"requests", "approvals"})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    private String firstName;

    private String lastName;

    private String userType; // values = ("admin" | "user" | "senate" | "dcc" | "departmentCouncil" | "apc" | "fcc" | "ugsc"

    private String email;

    private String password;

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Request> requests = new ArrayList<>();

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<Approval> approvals = new ArrayList<>();

    @JsonIgnoreProperties("user")
    @OneToMany(mappedBy = "user")
    private List<SupportingDocument> supportingDocuments = new ArrayList<>();
}
