package com.soen490.cms.Controllers;

import com.soen490.cms.Models.User;
import com.soen490.cms.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
public class HomeController {

    @Autowired
    private AuthenticationService authenticationService;

    // todo: to be refactored into POST and password encrypted
    @GetMapping(value = "/login")
    public User login(@RequestParam String email, String password){

        return authenticationService.authenticate(email, password);
    }

}
