package com.nhs.individual.Controller;

import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Domain.UserAddressId;
import com.nhs.individual.Service.AddressService;
import com.nhs.individual.Service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private AddressService addressService;

    @RequestMapping( value="/{userId}/address",method = RequestMethod.GET)
    public Collection<UserAddress> findAllByUserId( @PathVariable(name = "userId") Integer userId) {
        return userAddressService.findAllByUserId(userId);
    }
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @PreAuthorize("#userAddress.user.id==authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public UserAddress createUserAddress( @RequestBody UserAddress userAddress) {
        return userAddressService.create(userAddress);
    }
    @RequestMapping(value="/{userId}/address/{id}/default",method = RequestMethod.PUT)
    @PreAuthorize("userId==authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public UserAddress getById(@PathVariable(name = "id") Integer id,
                        @PathVariable(name = "userId") Integer userId) {
        return userAddressService.setDefaultAddress(userId,id);
    }

    @RequestMapping(value="/{userId}/address/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("userId==authentication.principal.userId or hasRole('ROLE_ADMIN')")
    public UserAddressId deleteUserAddress(@PathVariable Integer id,
                                  @PathVariable(name = "userId") Integer userId) {
        return userAddressService.deleteById(new UserAddressId(userId,id));
    }

}
