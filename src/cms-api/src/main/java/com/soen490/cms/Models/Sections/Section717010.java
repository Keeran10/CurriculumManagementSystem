package com.soen490.cms.Models.Sections;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Section717010 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // i.e. "70.71.9"

    private String sectionTitle; // "Degree Requirement for Beng ..."

    @Lob
    private String firstParagraph;

    private int isActive;

}
