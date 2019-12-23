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
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class RequestPackage {

    @Audited
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Audited
    @Lob
    private byte[] pdfFile;

    @JsonIgnoreProperties({"requestPackages", "users"})
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Audited
    @JsonIgnoreProperties("requestPackage")
    @OneToMany(mappedBy = "requestPackage")
    private List<Request> requests = new ArrayList<>();

    @Audited
    @JsonIgnoreProperties("requestPackage")
    @OneToMany(mappedBy = "requestPackage")
    private List<SupportingDocument> supportingDocuments = new ArrayList<>();

    @JsonIgnoreProperties("requestPackage")
    @OneToMany(mappedBy = "requestPackage")
    private List<Approval> approvals = new ArrayList<>();

    @JsonIgnoreProperties("requestPackages")
    @OneToMany(mappedBy =  "requestPackage")
    private List<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages = new ArrayList<>();
}
