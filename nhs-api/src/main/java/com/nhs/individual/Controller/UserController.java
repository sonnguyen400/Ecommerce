package com.nhs.individual.Controller;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Repository.UserRepository;
import com.nhs.individual.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    private User register(@RequestBody User user) {
        return userService.insert(user);
    }

}
