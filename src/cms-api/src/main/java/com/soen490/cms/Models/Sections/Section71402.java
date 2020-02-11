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
public class Section71402 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int isActive = 1;

    private String sectionId;

    private String sectionTitle; // Section 71.40.2

    private String introParagraph;

    private String firstCore;

    private String secondCore;

    private String scienceHeader;

    private String scienceDescription;

    private String scienceCoreHeader;

    private String electivesHeader;

    private String electivesDescription;

    @Transient
    List<Course> firstCoreCourses = new ArrayList<>();

    @Transient
    List<Course> secondCoreCourses = new ArrayList<>();

    @Transient
    List<Course> scienceCoreCourses = new ArrayList<>();

    @Transient
    List<Course> electiveCourses = new ArrayList<>();
}
