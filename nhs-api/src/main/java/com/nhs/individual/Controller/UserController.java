package com.nhs.individual.Controller;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@RequestBody User user) {
        return userService.save(user);
    }
    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        userService.deleteById(id);
    }
    @RequestMapping(value = "/{id}",method=RequestMethod.PUT)
    public User update(@PathVariable(value = "id") Integer id, @RequestBody User user) {
        user.setId(id);
        return userService.save(user);
    }
}
