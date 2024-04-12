package com.nhs.individual.Service;

import com.nhs.individual.Domain.*;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.ProductItemRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductItemService {
    @Autowired
    ProductItemRepository productItemRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    VariationService variationService;
    public ProductItem create(Integer productId, ProductItem productItem){

        return productService.findById(productId).map(product -> {
            productItem.setProduct_(product);
            Category category=new Category();
            category.setId(product.getCategoryId());


            Collection<Variation> variations=variationService.findAllByCategoryId(product.getCategoryId());
            Collection<Variation> merge_variations=productItem.getOptions()
                    .stream()
                    .map(VariationOption::getVariation)
                    .filter(variation -> {
                        return variations.stream().noneMatch(variation1 -> variation1.getName().equals(variation.getName()));
                    }).map(variation ->{
                        variation.setCategory(category);
                        return variation;
                    }).toList();
                    variations.addAll(variationService.saveAll(merge_variations));
            mergeVariationOption(productItem.getOptions(),variations);

            productItem.getOptions().forEach(option -> {
                option.getVariation().setCategory(category);
            });
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
    private void mergeVariationOption(Collection<VariationOption> options_merge,Collection<Variation> variations){
        options_merge.forEach(option->{
            variations.forEach(variation -> {
                if(variation.getName().trim().equalsIgnoreCase(option.getVariation().getName().trim())){
                    option.getVariation().setId(variation.getId());
//                    if(variation.getOptions()!=null&&variation.getOptions().size()>0){
//                        variation.getOptions().forEach(variationOption -> {
//                            if(variationOption.getValue().trim().equalsIgnoreCase(option.getValue().trim())) {
//                                option.setId(variationOption.getId());
//                            }
//                        });
//                    }

                }


            });
        });


    }

}
