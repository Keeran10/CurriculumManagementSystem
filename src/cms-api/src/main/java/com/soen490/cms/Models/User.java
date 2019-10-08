package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String userType;

    // Login credentials - email should be used as username since CMS will actively use emails for notification
    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Collection<Request> requests;

    @OneToMany(mappedBy = "user")
    @Getter @Setter
    private Collection<Approval> approvals;
}
