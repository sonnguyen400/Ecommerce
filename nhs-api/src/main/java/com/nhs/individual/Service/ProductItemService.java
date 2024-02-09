package com.nhs.individual.Service;

import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Repository.ProductItemRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductItemService {
    @Autowired
    ProductItemRepository productItemRepository;
    private static ObjectUtils<ProductItem> objectUtils=new ObjectUtils<ProductItem>();
    public ProductItem create(ProductItem productItem){
        return productItemRepository.save(productItem);
    }
    public Optional<ProductItem> findById(int id){
        return productItemRepository.findById(id);
    }
    public void deleteById(int id){
        productItemRepository.deleteById(id);
    }
    public ProductItem update(Integer id,ProductItem productItem) throws ChangeSetPersister.NotFoundException {
        return productItemRepository.save(findById(id).map(oldProductItem->{
            return objectUtils.merge(oldProductItem,productItem, ProductItem.class);
        }).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
