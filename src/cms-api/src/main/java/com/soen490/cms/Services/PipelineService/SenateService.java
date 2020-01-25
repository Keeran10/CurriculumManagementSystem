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
package com.soen490.cms.Services.PipelineService;

import com.soen490.cms.Models.ApprovalPipelineRequestPackage;
import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Repositories.ApprovalPipelineRequestPackageRepository;
import com.soen490.cms.Repositories.RequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;

@Service
@Transactional
@Log4j2
public class SenateService implements ApprovingBody {

    @Autowired
    ApprovalPipelineRequestPackageRepository approvalPipelineRequestPackageRepository;

    @Autowired
    RequestPackageRepository requestPackageRepository;

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
            if(approvalPipelineRequestPackage.getPosition().equals("Senate")) {
                int packageId = approvalPipelineRequestPackage.getRequestPackage().getId();
                requestPackages.put(packageId, requestPackageRepository.findById(packageId));
            }
        }
    }
}
