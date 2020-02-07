// MIT License

// Copyright (c) 2019 teamCMS

// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:

// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.

// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package com.soen490.cms.Models.Sections;

import com.soen490.cms.Models.Course;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Section71709 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // i.e. "70.71.9"

    private String sectionTitle; // "Degree Requirement for Beng ..."

    @Lob
    private String firstParagraph;


    private String firstCore; // Engineering Core
    private String secondCore; // Software Engineering Core
    private String thirdCore; // Computer Science Group
    private String fourthCore; // Basic and Natural Science Courses

    @Lob
    private String secondParagraph;

    private String fifthCore; // General Program

    @Lob
    private String thirdParagraph;
    private String sixthCore; // Computer Games (CG) Option
    private String seventhCore; // Web Services and Applications (WSA) Option
    private String eighthCore; // Real-Time, Embedded, and Avionics Software (REA) Option
    private String ninthCore; // Electives

    private int isActive;

    @Transient
    List<Course> firstCoreCourses = new ArrayList<>(); // null since we don't have ENGR courses

    @Transient
    List<Course> secondCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> thirdCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> fourthCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> fifthCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> sixthCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> seventhCoreCourses = new ArrayList<>(); // retrieve from database before sending

    @Transient
    List<Course> eightCoreCourses = new ArrayList<>(); // retrieve from database before sending

}
