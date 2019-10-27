package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
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

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
}