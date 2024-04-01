package com.nhs.individual.Service;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.ProductRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;

    public Product save(Product product){
        return productRepository.save(product);
    }
    public Product create(Product product){
        if(product.getCategory()==null) throw new IllegalArgumentException("Product must be dependent on a category");
        Integer categoryId = product.getCategory().getId();
        return categoryService.findById(categoryId).map(category->{
            product.setCategory(category);
            if(product.getProductItems()!=null){
                product.getProductItems().forEach((productItem -> productItem.setProduct_(product)));
            }
            return productRepository.save(product);
        }).orElseThrow(()->new ResourceNotFoundException("Category with id " + categoryId+" not found"));
    }
    public Collection<Product> findAll(){
        return productRepository.findAll();
    }
    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }
    public Collection<Product> findAllByCategoryId(Integer categoryId){
        return productRepository.findAllByCategory_id(categoryId);
    }
    public Collection<Product> findAllByWarehouseId(Integer warehouseId){
        return productRepository.findAllByWarehouseId(warehouseId);
    }
    public Product update(Integer id,Product product){
        return productRepository.findById(id).map(oldProduct-> ObjectUtils.merge(oldProduct,product,Product.class)).orElseThrow(()->new RuntimeException("Product not found"));
    }
    public void delete(Integer id){
        productRepository.deleteById(id);
    }
}
