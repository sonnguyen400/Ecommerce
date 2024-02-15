package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Warehouse;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    WareHouseService wareHouseService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Collection<Warehouse> findAll(){
        return wareHouseService.findAll();
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Warehouse findById(@PathVariable(name = "id") Integer id){
        return wareHouseService.findById(id).orElseThrow(()->new ResourceNotFoundException("Warehouse not found"));
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Warehouse create(Warehouse wareHouse){
        return wareHouseService.create(wareHouse);
    }
}
