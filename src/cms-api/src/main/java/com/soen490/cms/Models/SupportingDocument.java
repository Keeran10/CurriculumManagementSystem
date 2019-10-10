package com.soen490.cms.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class SupportingDocument {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int id;

    @Getter @Setter
    private Timestamp timestamp;

    @Lob
    @Getter @Setter
    private byte[] document;

    @ManyToOne
    @JoinColumn(name = "request_id")
    @Getter @Setter
    private Request request;
}