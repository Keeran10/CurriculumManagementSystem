package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class SupportingDocument {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Timestamp timestamp;

    @Lob
    private byte[] document;

    @JsonIgnoreProperties({"supportingDocuments", "requests", "approvals"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"supportingDocuments", "requests", "approvals"})
    @ManyToOne
    @JoinColumn(name = "package_id")
    private RequestPackage requestPackage;
}