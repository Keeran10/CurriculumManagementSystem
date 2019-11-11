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

package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
public class ApprovalPipeline {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int departmentCurriculumCommittee;

    private int departmentCouncil;

    private int undergraduateStudiesCommittee; // Associate Dean Academic Programs Under Graduate Studies Committee

    private int facultyCouncil;

    private int APC;

    private int senate;

    @JsonIgnoreProperties("approvalPipeline")
    @OneToMany(mappedBy = "approvalPipeline")
    Collection<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages;

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;

        if(o.getClass() != this.getClass() || o == null) return false;

        ApprovalPipeline approvalPipeline = (ApprovalPipeline)o;

        if(this.departmentCurriculumCommittee != approvalPipeline.departmentCurriculumCommittee
            || this.departmentCouncil != approvalPipeline.departmentCouncil
            || this.undergraduateStudiesCommittee != approvalPipeline.undergraduateStudiesCommittee
            || this.facultyCouncil != approvalPipeline.facultyCouncil
            || this.APC != approvalPipeline.APC
            || this.senate != approvalPipeline.senate) {
            return false;
        } else {
            return true;
        }
    }
}
