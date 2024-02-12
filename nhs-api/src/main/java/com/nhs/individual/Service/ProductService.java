package com.nhs.individual.Service;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Repository.ProductRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;
    public Product create(Product product){
        return repository.save(product);
    }
    public Iterable<Product> findAll(){
        return repository.findAll();
    }
    public Optional<Product> findById(Integer id){
        return repository.findById(id);
    }
    public Product update(Integer id,Product product){
        return findById(id).map(oldProduct->{
            return ObjectUtils.merge(oldProduct,product,Product.class);
        }).orElseThrow(()->new RuntimeException("Product not found"));
    }
    public void delete(Integer id){
        repository.deleteById(id);
    }
}
