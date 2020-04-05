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
package com.soen490.cms.Controllers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soen490.cms.Models.User;
import com.soen490.cms.Services.AdminService;
import com.soen490.cms.Services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


@RestController
@CrossOrigin(origins = ControllerConfiguration.ENDPOINT_URL)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AdminService adminService;

    // todo: to be refactored into POST and password encrypted
    @GetMapping(value = "/login")
    public User login(@RequestParam String email, String password){

        return authenticationService.authenticate(email, password);
    }
/*
    @PostMapping(value = "/register_user")
    public boolean registerUser(@RequestParam User user) {
        return adminService.saveUser(user);
    }
*/
    @PostMapping(value = "/register_user")
    public boolean registerUser(@RequestParam String first_name, @RequestParam String last_name, @RequestParam String user_type,
                                @RequestParam String email, @RequestParam String password, @RequestParam int department_id) {
        return adminService.saveUser(first_name, last_name, user_type, email, password, department_id);
    }

    @GetMapping(value = "/get_users")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @PostMapping(value = "/update_users")
    public boolean updateUsers(@RequestParam String updated_users){
        ObjectMapper mapper = new ObjectMapper();
        try{
            List<User> updatedUsersList = Arrays.asList(mapper.readValue(updated_users, User[].class));
            return adminService.updateUsers(updatedUsersList);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
