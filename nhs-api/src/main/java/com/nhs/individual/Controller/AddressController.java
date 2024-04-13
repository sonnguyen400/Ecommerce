package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    @Autowired
    AddressService addressService;
    @RequestMapping(method = RequestMethod.POST)
    public Address createAddress(@RequestBody Address address){
        return addressService.create(address);
    }
    @RequestMapping(value = "/{id}",method=RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable(name = "id",required = false) Integer id){
        if(id!=null) return ResponseEntity.ok(addressService.findById(id).orElseThrow(()->  new ResourceNotFoundException("Could not find address with id: "+id)));
        return ResponseEntity.ok(addressService.findAll());
    }


}
