package com.soen490.cms.Services;

import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    // todo: password encryption to be implemented in release 2
    public User authenticate(String email, String password) {

        log.info("login attempt: " + email);

        User user = userRepository.findByCredentials(email, password);

        return user;
    }
}
