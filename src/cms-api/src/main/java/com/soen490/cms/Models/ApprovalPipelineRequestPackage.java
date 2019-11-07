package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@IdClass(ApprovalPipelineRequestPackage.RequestPackagePipelineId.class)
public class ApprovalPipelineRequestPackage implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "pipeline_id")
    private ApprovalPipeline approvalPipeline;

    @Id
    @ManyToOne
    @JoinColumn(name = "package_id")
    private RequestPackage requestPackage;

    private String position;

    public static class RequestPackagePipelineId implements Serializable {
        private Integer approvalPipeline;
        private Integer requestPackage;

        public RequestPackagePipelineId() { }

        public RequestPackagePipelineId(Integer approvalPipeline, Integer requestPackage) {
            this.approvalPipeline = approvalPipeline;
            this.requestPackage = requestPackage;
        }
    }
}
