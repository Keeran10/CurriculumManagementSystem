package com.soen490.cms.Controllers;

import com.soen490.cms.Models.User;
import com.soen490.cms.Services.AdminService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/register_user")
    public boolean registerUser(@RequestParam User user) {
        return adminService.saveUser(user);
    }
}
