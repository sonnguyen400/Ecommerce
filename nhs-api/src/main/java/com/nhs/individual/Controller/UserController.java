package com.nhs.individual.Controller;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Repository.UserRepository;
import com.nhs.individual.Service.UserAddressService;
import com.nhs.individual.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userService.insert(user);
    }

}
