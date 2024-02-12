package com.nhs.individual.Controller;

import com.nhs.individual.Service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1/user/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public Collection<UserAddress> findAllByUserId(@RequestParam(value = "userid") Integer userId) {
//        return userAddressService.findAllByUserId(userId);
//    }
//    @RequestMapping(value="/",method = RequestMethod.POST)
//    public UserAddress create(@RequestBody UserAddress userAddress,
//                              @RequestParam(value = "userId", required = true) Integer userId) {
//        return userAddressService.create(userId, userAddress);
//    }
//    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
//    public UserAddress update(@RequestBody UserAddress userAddress,
//                              @PathVariable(value = "id", required = true) Integer id) {
//        return userAddressService.update(id, userAddress);
//    }
//    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
//    public void delete(@PathVariable(value = "id", required = true) Integer id) {
//        userAddressService.deleteById(id);
//    }
//    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
//    public void setDefault(@RequestBody UserAddress userAddress,
//                                    @RequestParam(value = "id", required = true) Integer id,
//                                    @RequestParam(value = "default", required = true,defaultValue = "false") Boolean setDefault){
//        userAddressService.setDefaultUserAddress(userAddress);
//    }
}
