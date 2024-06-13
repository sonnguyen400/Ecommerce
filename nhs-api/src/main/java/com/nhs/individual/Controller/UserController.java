package com.nhs.individual.Controller;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Service.UserService;
import com.nhs.individual.Specification.DynamicSearch;
import com.nhs.individual.Specification.UserSpecification;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @PreAuthorize("authentication.principal.role='ADMIN'")
    @RequestMapping(method=RequestMethod.GET)
    public List<User> findAllUser(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                  @RequestParam(name = "size",defaultValue = "10") Integer size,
                                  HttpServletRequest request) {
        List<UserSpecification> userSpecifications=new ArrayList<UserSpecification>();
        request.getParameterMap().forEach((key,value)->{
            String[] operationValue=value[0].split("[()]");
            if(operationValue.length==2) userSpecifications.add(new UserSpecification(new DynamicSearch(key,operationValue[0], DynamicSearch.Operator.valueOf(operationValue[1]))));
        });
        return userService.findAll(userSpecifications, PageRequest.of(page, size));
    }

}
