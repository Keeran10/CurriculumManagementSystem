package com.soen490.cms.Models.Sections;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Section70712 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // i.e. "70.71.9"

    private String sectionTitle; // "Degree Requirement for Beng ..."

    @Lob
    private String firstParagraph;

    private String firstCore; //Computer Science Core
    private String secondCore; //Complementary Core
    private String thirdCore; // Computer Science Electives

    @Lob
    private String secondParagraph;

    private String fourthCore; //Mathematics Electives
    @Lob
    private String thirdParagraph;

    private String fifthCore; // General Electives
    @Lob
    private String fourthParagraph;

    private String sixthCore; //  General Program
    @Lob
    private String fifthParagraph;

    private String seventhCore; // Computer Games Option
    @Lob
    private String sixthParagraph;

    private String eightCore; // Computer Games Electives
    @Lob
    private String seventhParagraph;

    private String ninthCore; // Web Services and Applications Option
    @Lob
    private String eightParagraph;

    private String tenthCore; // Web Services and Applications Electives
    @Lob
    private String ninthParagraph;
    private String eleventhCore; // Computer Systems Option
    @Lob
    private String tenthParagraph;

    private String twelfthCore; // Computer Systems Electives
    @Lob
    private String eleventhParagraph;

    private String thirteenthCore; // Software Systems Option
    @Lob
    private String twelfthParagraph;

    private String fourteenthCore; // Software Systems Core
    private String fifteenthCore; //  Information Systems Option
    @Lob
    private String thirteenthParagraph;

    private String sixteenthCore; // Information Systems Electives
    @Lob
    private String fourteenthParagraph;

    private String seventeenthCore; // Computer Applications Option
    @Lob
    private String fifteenthParagraph;

    private String eighteenthCore; // Computation Arts Option
    @Lob
    private String sixteenthParagraph;

    private String nineteenthCore; // Mathematics and Statistics Option
    @Lob
    private String seventeenthParagraph;
}
