package com.soen490.cms.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Data
@IdClass(ApprovalPipelineRequestPackage.RequestPackagePipelineId.class)
public class ApprovalPipelineRequestPackage implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "pipeline_id")
    private ApprovalPipeline approvalPipeline;

    @JsonIgnoreProperties({"requests", "approvalPipelineRequestPackages"})
    @Id
    @ManyToOne
    @JoinColumn(name = "package_id")
    private RequestPackage requestPackage;

    @Audited
    private String position;

    @Audited(targetAuditMode = NOT_AUDITED)
    @JsonIgnoreProperties({"supportingDocuments", "requests", "approvals", "approvalPipelineRequestPackages"})
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
