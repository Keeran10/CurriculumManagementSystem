package com.soen490.cms.Services;

import com.soen490.cms.Models.Request;
import com.soen490.cms.Repositories.RequestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
@Log4j2

public class RequestService {

    @Autowired
    RequestRepository requestRepo;

    public Request find(Long requestId) {
        log.info("find supporting document with id " + requestId);
        return requestRepo.findByRequestId(requestId);
    }
}
