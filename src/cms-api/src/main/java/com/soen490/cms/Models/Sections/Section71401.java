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
public class Section71401 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String sectionId; // 71.40.1

    private String sectionTitle;

    private String introParagraph;

    private int isActive = 1;

    private String engCore;

    private String mechCore;

    private String electivesHeader;

    private String electivesDescription;

    private String firstOption; // aerospace

    private String secondOption; // design and manufacturing

    private String thirdOption; // systems and mechatronics

    private String fourthOption; // thermo-fluids and propulsion

    private String fifthOption; // vehicle systems

    private String sixthOption; // stress analysis

    @Transient
    List<Course> engCoreCourses = new ArrayList<>();

    @Transient
    List<Course> mechCoreCourses = new ArrayList<>();

    @Transient
    List<Course> firstOptionCourses = new ArrayList<>();

    @Transient
    List<Course> sectionOptionCourses = new ArrayList<>();

    @Transient
    List<Course> thirdOptionCourses = new ArrayList<>();

    @Transient
    List<Course> fourthOptionCourses = new ArrayList<>();

    @Transient
    List<Course> fifthOptionCourses = new ArrayList<>();

    @Transient
    List<Course> sixthOptionCourses = new ArrayList<>();
}
