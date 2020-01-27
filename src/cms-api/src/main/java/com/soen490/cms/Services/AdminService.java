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

    @Autowired
    DepartmentRepository departmentRepository;

    public boolean saveUser(User user) {
        //log.info("registering new user with id " + user.getId());
        userRepository.save(user);
        return true;
    }

    public boolean saveUser(String first_name, String last_name, String user_type, String email, String password, int department_id) {
        log.info("registering new user: " + first_name + " " + last_name);
        User user = new User();
        Department department = departmentRepository.findById(department_id);
        user.setUserType(user_type);
        user.setDepartment(department);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return true;
    }
}
