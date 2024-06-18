package com.nhs.individual.controller;

import com.nhs.individual.constant.AccountProvider;
import com.nhs.individual.constant.AccountRole;
import com.nhs.individual.dto.AccountDto;
import com.nhs.individual.Domain.User;
import com.nhs.individual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Integer id) {
        userService.deleteById(id);
    }
    @RequestMapping(value = "/{id}",method=RequestMethod.PUT)
    public User update(@PathVariable(value = "id") Integer id, @RequestBody User user) {
        user.setId(id);
        return userService.save(user);
    }
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @RequestMapping(method=RequestMethod.GET)
//    public List<User> findAllUser(@RequestParam(name = "page", defaultValue = "0") Integer page,
//                                  @RequestParam(name = "size",defaultValue = "10") Integer size,
//                                  HttpServletRequest request) {
//        List<UserSpecification> userSpecifications=new ArrayList<UserSpecification>();
//        request.getParameterMap().forEach((key,value)->{
//            String[] operationValue=value[0].split("[()]");
//            if(operationValue.length==2) userSpecifications.add(new UserSpecification(new DynamicSearch(key,operationValue[0], DynamicSearch.Operator.valueOf(operationValue[1]))));
//        });
//        return userService.findAll(userSpecifications, PageRequest.of(page, size));
//    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUser(@RequestParam Map<String,String> propertiesMap){
        Integer page= 0;
        Integer size=10;
        AccountRole role=null;
        AccountProvider provider=null;
        propertiesMap.remove("size");
        try{
            if(propertiesMap.get("page")!=null) page=Integer.parseInt(propertiesMap.get("page"));
            if(propertiesMap.get("size")!=null) size=Integer.parseInt(propertiesMap.get("size"));
            if(propertiesMap.get("role")!=null) role=AccountRole.valueOf(propertiesMap.get("role"));
            if(propertiesMap.get("provider")!=null) provider=AccountProvider.valueOf(propertiesMap.get("provider"));
        }catch (NumberFormatException ignored){
            throw new IllegalArgumentException("Invalid type argument");
        }finally {
            propertiesMap.remove("page");
            propertiesMap.remove("size");
            propertiesMap.remove("role");
            propertiesMap.remove("provider");
        }
        String name=propertiesMap.get("name");propertiesMap.remove("name");
        String address=propertiesMap.get("address");propertiesMap.remove("address");
        return userService.findAll(page,size,name,address,role,provider,propertiesMap).stream().map(user->{
            user.setAccount(new AccountDto(user.getAccount()));
            return user;
        }).toList();
    }

}
