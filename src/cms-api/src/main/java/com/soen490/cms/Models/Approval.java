package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Approval {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private String feedback;

    @Getter @Setter
    private int status; // 1: not_viewed, 2: in_review, 3: change_requested, 4: rejected, 5: approved

    @Getter @Setter
    private int isLocked; // 0: no, 1: yes

    @Getter @Setter
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "request_id")
    @Getter @Setter
    private Request request;
}
