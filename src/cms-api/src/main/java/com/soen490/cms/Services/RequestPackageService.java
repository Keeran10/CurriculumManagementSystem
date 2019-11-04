package com.soen490.cms.Services;

import com.soen490.cms.Models.RequestPackage;
import com.soen490.cms.Repositories.RequestPackageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2

public class RequestPackageService {

    @Autowired
    RequestPackageRepository packageRepo;

    public RequestPackage find(Long packageId) {
        log.info("find supporting document with id " + packageId);
        return packageRepo.findById(packageId);
    }
}
