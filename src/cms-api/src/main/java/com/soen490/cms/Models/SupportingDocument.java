package com.soen490.cms.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class SupportingDocument {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Timestamp timestamp;

    @Lob
    private byte[] document;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
}