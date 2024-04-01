package com.nhs.individual.Service;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.ProductItemRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class ProductItemService {
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    ProductService productService;
    public ProductItem create(Integer productId, ProductItem productItem){
        return productService.findById(productId).map(product -> {
            productItem.setProduct_(product);
            return productItemRepository.save(productItem);
        }).orElseThrow(()->new ResourceNotFoundException("product with id"+productId+" not found"));
    }
    public Product saveAll(Integer productId, Collection<ProductItem> productItems){
        return productService.findById(productId).map(product -> {
            product.setProductItems(productItems);
            productItems.forEach(productItem ->{
                productItem.setProduct_(product);
                productItemRepository.save(productItem);
            });
            return product;
        }).orElseThrow(()->new ResourceNotFoundException("product with id"+productId+" not found"));
    }
    public Optional<ProductItem> findById(int id){
        return productItemRepository.findById(id);
    }
    public Collection<ProductItem> findAllByProductId(Integer productId){
        return productService.findById(productId).map(Product::getProductItems).orElse(Collections.emptyList());
    }
    public void deleteById(int id){
        productItemRepository.deleteById(id);
    }
    public ProductItem update(Integer id,ProductItem productItem){
        return productItemRepository.save(findById(id).map(oldProductItem-> ObjectUtils.merge(oldProductItem,productItem, ProductItem.class)).orElseThrow(()->new ResourceNotFoundException("Product item with id " + id+" not found")));
    }

}
