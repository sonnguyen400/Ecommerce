package com.nhs.individual.Controller;

import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/user/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping( method = RequestMethod.GET)
    public Collection<UserAddress> findAllByUserId(@RequestParam(name = "user") Integer userId) {
        return userAddressService.findAllByUserId(userId);
    }
    @RequestMapping( method = RequestMethod.POST)
    public UserAddress createUserAddress(
            @RequestParam(name = "user") Integer user,
            @RequestBody UserAddress userAddress) {
        return userAddressService.create(user, userAddress);
    }


    @RequestMapping(value="/{id}/default",method = RequestMethod.GET)
    public UserAddress getById(@PathVariable Integer id,
                        @RequestParam(name = "user") Integer user) {
        return userAddressService.setDefaultAddress(user,id);
    }
    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public void deleteUserAddress(@PathVariable Integer id,
                                  @RequestParam(name = "user") Integer user) {
        userAddressService.deleteById(user,id);
    }

}
