package com.nhs.individual.service;

import com.nhs.individual.Domain.EmbeddedId.ProductItemInWarehouseId;
import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Domain.Warehouse;
import com.nhs.individual.Domain.WarehouseItem;
import com.nhs.individual.exception.ResourceNotFoundException;
import com.nhs.individual.repository.WarehouseItemRepository;
import com.nhs.individual.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WareHouseItemService {
    @Autowired
    WarehouseItemRepository repository;
    public Optional<WarehouseItem> findByItemIdAndWarehouseId(Integer itemId, Integer warehouseId){
        return repository.findById(new ProductItemInWarehouseId(itemId,warehouseId));
    }
    public void deleteItemFromWarehouse(Integer itemId, Integer warehouseId){
        repository.deleteById(new ProductItemInWarehouseId(itemId,warehouseId));
    }
    public void update(Integer itemId, Integer warehouseId,WarehouseItem warehouseItem){
        ProductItemInWarehouseId id=new ProductItemInWarehouseId(itemId,warehouseId);
        warehouseItem.setId(id);
        repository.findById(id)
                .map(oldItem-> repository.save(ObjectUtils.merge(oldItem,warehouseItem,WarehouseItem.class)))
                .orElseThrow(()->new ResourceNotFoundException("Product Item not found"));
    }
    public WarehouseItem importNewItem(Integer warehouseId,Integer itemId,WarehouseItem warehouseItem){
        warehouseItem.setId(new ProductItemInWarehouseId(itemId,warehouseId));
        ProductItem productItem=new ProductItem();
        productItem.setId(itemId);
        warehouseItem.setProductItem(productItem);
        Warehouse warehouse=new Warehouse();
        warehouse.setId(warehouseId);
        warehouseItem.setWarehouse(warehouse);
        WarehouseItem warehouseItem1=repository.save(warehouseItem);
        return warehouseItem1;
    }

}
