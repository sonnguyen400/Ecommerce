package com.nhs.individual.Service;

import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Domain.Warehouse;
import com.nhs.individual.Repository.WareHouseRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WareHouseService {
    @Autowired
    WareHouseRepository wareHouseRepository;
    private static final ObjectUtils<Warehouse> objectUtils=new ObjectUtils<Warehouse>();
    public Warehouse create(Warehouse wareHouse){
        return wareHouseRepository.save(wareHouse);
    };
    public Optional<Warehouse> findById(int id){
        return wareHouseRepository.findById(id);
    };
    public void deleteById(int id){
        wareHouseRepository.deleteById(id);
    };
    public Warehouse update(Integer id,Warehouse wareHouse) throws ChangeSetPersister.NotFoundException {
        return wareHouseRepository.save(findById(id).map(oldWareHouse->{
            return objectUtils.merge(oldWareHouse,wareHouse, Warehouse.class);
        }).orElseThrow(ChangeSetPersister.NotFoundException::new));
    };
    public void transport(ProductItem productItem,Warehouse origin,Warehouse destination,int qty){

    }
}
