package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@ToString(exclude= {"requests", "approvals"})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    private String firstName;

    private String lastName;

    private String userType;

    private String email;

    private String password;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user")
    private Collection<Request> requests;

    @JsonIgnoreProperties({"user"})
    @OneToMany(mappedBy = "user")
    private Collection<Approval> approvals;
}
