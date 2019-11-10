package com.soen490.cms.Services;

import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    // todo: password encryption to be implemented in release 2
    public User authenticate(String email, String password) {

        User user = userRepository.findByCredentials(email, password);

        return user;
    }
}
