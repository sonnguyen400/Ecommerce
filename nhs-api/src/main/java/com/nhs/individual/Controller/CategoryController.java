package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Category;
import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.CategoryService;
import com.nhs.individual.Service.ProductService;
import com.nhs.individual.Service.VariationOptionService;
import com.nhs.individual.Service.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    VariationOptionService variationOptionService;
    @Autowired
    ProductService productService;
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Category> findAll(){
        return categoryService.findAll();
    }
//    @RequestMapping(value ="/{parent_id}",method = RequestMethod.GET)
//    public Collection<Category> findAllByParentId(@PathVariable(name = "parent_id") Integer id){
//        return categoryService.findAllByParentId(id);
//    }
    @RequestMapping(value = "/{category_id}",method = RequestMethod.GET)
    public Category findById(@PathVariable(name = "category_id") Integer id){
        return categoryService.findById(id).orElseThrow(()->  new ResourceNotFoundException("Could not find category with id: "+id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }
    @RequestMapping(value = "/{parent_id}",method = RequestMethod.POST)
    public Category createChild(@RequestBody Category category,
                                @PathVariable(name = "parent_id") Integer id){
        return categoryService.addChild(id,category);
    }

    @RequestMapping(value = "/{category_id}",method = RequestMethod.PUT)
    public Category updateById(@RequestBody Category category,
            @PathVariable(name = "category_id") Integer id){
        return categoryService.updateCategory(id,category);
    }
    @RequestMapping(value = "/{category_id}",method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "category_id") Integer id){
        categoryService.deleteById(id);
    }
    @RequestMapping(value = "/{category_id}/empty",method = RequestMethod.DELETE)
    public void deleteByParentId(@PathVariable(name = "category_id") Integer id){
        categoryService.deleteAllByParentId(id);
    }

    //Category-products
    @RequestMapping(value = "/{category_id}/product", method = RequestMethod.GET)
    public Collection<Product> getProductByCategory(@PathVariable(name = "category_id") Integer id) {
        return productService.findAllByCategoryId(id);
    }
    @RequestMapping(value="/{category_id}/product",method = RequestMethod.POST)
    public Product createProductInCategory(@PathVariable(name = "category_id") Integer categoryId,
                                           @RequestBody Product product){
        Category category=new Category();
        category.setId(categoryId);
        product.setCategory(category);
        return productService.create(product);
    }




}
