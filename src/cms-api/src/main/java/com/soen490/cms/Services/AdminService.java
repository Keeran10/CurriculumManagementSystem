package com.soen490.cms.Services;

import com.soen490.cms.Models.Department;
import com.soen490.cms.Models.User;
import com.soen490.cms.Repositories.DepartmentRepository;
import com.soen490.cms.Repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<User> getAllUsers(){
        log.info("findAllUsers");

        return userRepository.findAll();
    }

    public boolean updateUsers(List<User> updated_users){
        for(User u : updated_users){
            User user = userRepository.findById(u.getId());
            if(user == null){
                log.error("User not found. id = " + u.getId());
                return false;
            }
            user.setFirstName(u.getFirstName());
            user.setLastName(u.getLastName());
            user.setUserType(u.getUserType());
            user.setEmail(u.getEmail());
            user.setPassword(u.getPassword());
            userRepository.save(user);
            log.info("User saved. id=" + user.getId());
        }
        return true;
    }
}
