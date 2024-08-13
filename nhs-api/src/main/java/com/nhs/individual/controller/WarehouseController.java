package com.nhs.individual.controller;

import com.nhs.individual.domain.Product;
import com.nhs.individual.domain.Warehouse;
import com.nhs.individual.domain.WarehouseItem;
import com.nhs.individual.exception.ResourceNotFoundException;
import com.nhs.individual.service.CloudinaryService;
import com.nhs.individual.service.ProductService;
import com.nhs.individual.service.WareHouseItemService;
import com.nhs.individual.service.WareHouseService;
import com.nhs.individual.workbook.WarehouseItemXLSX;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
@PreAuthorize("hasAuthority('ADMIN')")
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
    @RequestMapping(value = "/{warehouse_id}/importXLSX",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Collection<WarehouseItem> importGoods(@RequestPart(name = "file") MultipartFile file) throws IOException {
        return wareHouseItemService.importGoods(WarehouseItemXLSX.read(file.getInputStream()));
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
