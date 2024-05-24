package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Domain.Warehouse;
import com.nhs.individual.Domain.WarehouseItem;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.ProductService;
import com.nhs.individual.Service.WareHouseItemService;
import com.nhs.individual.Service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    ProductService productService;
    @Autowired
    WareHouseItemService wareHouseItemService;
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Warehouse> findAll(){
        return wareHouseService.findAll();
    }
    @RequestMapping(value = "/{warehouse_id}",method = RequestMethod.GET)
    public Warehouse findById(@PathVariable(name = "warehouse_id") Integer id){
        return wareHouseService.findById(id).orElseThrow(()->new ResourceNotFoundException("Warehouse not found"));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Warehouse create(@RequestBody  Warehouse wareHouse){
        return wareHouseService.create(wareHouse);
    }
    @RequestMapping(value = "/{warehouse_id}",method = RequestMethod.PUT)
    public Warehouse update(@PathVariable(name = "warehouse_id") Integer id, Warehouse wareHouse){
        return wareHouseService.update(id,wareHouse);
    }
    @RequestMapping(value = "/{warehouse_id}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "warehouse_id") Integer id){
        wareHouseService.deleteById(id);
    }
    //Product accessories
    @RequestMapping(value="/{warehouse_id}/product",method = RequestMethod.GET)
    public Collection<Product> findAllProduct(@PathVariable(name = "warehouse_id") Integer id){
        return productService.findAllByWarehouseId(id);
    }
    //Product Item In warehouse
    @RequestMapping(value ="/{warehouse_id}/item/{id}",method = RequestMethod.GET)
    public WarehouseItem findItemByWarehouseId(@PathVariable(name = "warehouse_id")Integer warehouseId,
                                                           @PathVariable(name = "id") Integer id){
        return wareHouseItemService.findByItemIdAndWarehouseId(id,warehouseId).orElseThrow(
                ()->new ResourceNotFoundException("Warehouse item not found")
        );
    }
    @RequestMapping(value = "/{warehouseId}/item/{id}", method = RequestMethod.POST)
    public WarehouseItem createItem(@PathVariable(name = "warehouseId") Integer warehouseId,
                                    @PathVariable(name = "id") Integer id,
                                    @RequestBody WarehouseItem warehouseItem){
        return wareHouseItemService.importNewItem(warehouseId,id,warehouseItem);
    }
    @RequestMapping(value = "/{warehouseId}/item/{id}", method = RequestMethod.PUT)
    public void updateItem(@PathVariable(name = "warehouseId") Integer warehouseId,
                                    @PathVariable(name = "id") Integer id,
                                    @RequestBody WarehouseItem warehouseItem){
        wareHouseItemService.update(warehouseId, id,warehouseItem);
    }
    @RequestMapping(value = "/{warehouseId}/item/{id}",method = RequestMethod.DELETE)
    public void deleteItem(@PathVariable(name = "warehouseId") Integer warehouseId,
                                    @PathVariable(name = "id") Integer id){
        wareHouseItemService.deleteItemFromWarehouse(id,warehouseId);
    }

}
