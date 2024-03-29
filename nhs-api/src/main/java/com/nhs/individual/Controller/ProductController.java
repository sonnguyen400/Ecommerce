package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.ProductItemService;
import com.nhs.individual.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductItemService productItemService;
    @RequestMapping( method = RequestMethod.GET)
    public Collection<Product> getAllProduct(){
        return productService.findAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable(name = "id") Integer id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Product updateProduct(@PathVariable(name = "id") Integer id,
                                 @RequestBody Product product) {
        return productService.update(id, product);
    }
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable(name = "id") Integer id) {
        productService.delete(id);
    }



    //Product item
    @RequestMapping(value = "/{product_id}/item",method = RequestMethod.GET)
    public Collection<ProductItem> getAllByProduct(@PathVariable(name = "product_id") Integer productId){
        return productItemService.findAllByProductId(productId);
    }
    @RequestMapping(value = "/{product_id}/item/{item_id}",method = RequestMethod.GET)
    public ProductItem getProductItem(@PathVariable(name = "product_id") Integer productId,
                                           @PathVariable(name = "item_id") Integer itemId) {
        return productItemService.findById(itemId).orElseThrow(()->new ResourceNotFoundException("product item not found"));
    }
    @RequestMapping(value = "/{product_id}/item",method = RequestMethod.POST)
    public Product createAllByProduct(@PathVariable(name = "product_id") Integer productId,
                                          @RequestBody List<ProductItem> productItem){
        return productItemService.saveAll(productId,productItem);
    }
    @RequestMapping(value = "/{product_id}/item/{item_id}",method = RequestMethod.PUT)
    public ProductItem updateProductItem(@PathVariable(name = "product_id",required = false) Integer productId,
                                          @PathVariable(name = "item_id") Integer itemId,
                                          @RequestBody ProductItem productItem){
        return productItemService.update(itemId,productItem);
    }
    @RequestMapping(value = "/{product_id}/item/{item_id}",method = RequestMethod.DELETE)
    public void deleteProductItemById(@PathVariable(name = "product_id",required = false) Integer productId,
                                         @PathVariable(name = "item_id") Integer itemId,
                                         @RequestBody ProductItem productItem){
        productItemService.deleteById(itemId);
    }

}
