package com.soen490.cms.Services;

import com.soen490.cms.Models.Department;
import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.DepartmentRepository;
import com.soen490.cms.Repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public boolean saveUser(User user) {
        log.info("registering new user with id " + user.getId());
        userRepository.save(user);
        return true;
    }
}
