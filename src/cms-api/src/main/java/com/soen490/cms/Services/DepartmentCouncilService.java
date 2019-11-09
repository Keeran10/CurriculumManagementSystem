package com.soen490.cms.Services;

import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;

@Service
@Transactional
@Log4j2
public class DepartmentCouncilService implements ApprovingBody {

    @Autowired
    ApprovalPipelineRequestPackageRepository approvalPipelineRequestPackageRepository;

    @Autowired
    RequestPackageService requestPackageService;

    private HashMap<Integer, RequestPackage> requestPackages = new HashMap<>();

    @Override
    public RequestPackage sendPackage(int requestPackageId) {
        if(requestPackages.size() == 0) {
            populateMap();
        }
        RequestPackage requestPackage = requestPackages.get(requestPackageId);
        requestPackages.remove(requestPackageId);
        return requestPackage;
    }

    @Override
    public void receivePackage(RequestPackage requestPackage) {
        requestPackages.put(requestPackage.getId(), requestPackage);
    }

    private void populateMap() {
        Collection<ApprovalPipelineRequestPackage> approvalPipelineRequestPackages = approvalPipelineRequestPackageRepository.findAll();
        for(ApprovalPipelineRequestPackage approvalPipelineRequestPackage : approvalPipelineRequestPackages) {
            if(approvalPipelineRequestPackage.getPosition().equals("Department Council")) {
                int packageId = approvalPipelineRequestPackage.getRequestPackage().getId();
                requestPackages.put(packageId, requestPackageService.find((long)packageId));
            }
        }
    }
}
