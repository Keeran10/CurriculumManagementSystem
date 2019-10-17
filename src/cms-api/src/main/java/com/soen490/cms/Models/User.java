package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    private String firstName;

    private String lastName;

    private String userType;

    // Login credentials - email should be used as username since CMS will actively use emails for notification
    private String email;

    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Collection<Request> requests;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Collection<Approval> approvals;

    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private Collection<Package> packages;
}
