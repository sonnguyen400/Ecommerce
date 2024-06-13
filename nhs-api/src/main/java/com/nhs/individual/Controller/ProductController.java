package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.CloudinaryService;
import com.nhs.individual.Service.ProductItemService;
import com.nhs.individual.Service.ProductService;
import com.nhs.individual.Specification.DynamicSearch;
import com.nhs.individual.Specification.ProductSpecification;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    CloudinaryService cloudinaryService;
    @RequestMapping( method = RequestMethod.GET)
    public Collection<Product> getAllProduct(
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "size",defaultValue = "10") Integer size
    ){
        Pageable pageable=PageRequest.of(page,size);
        return productService.findAll(pageable);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable(name = "id") Integer id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    @RequestMapping(value = "/filter",method = RequestMethod.GET)
    public List<Product> getProducts(
            @RequestParam(name = "category",required = false) Integer category,
            @RequestParam(name ="priceMax",required = false) BigDecimal priceMax,
            @RequestParam(name = "priceMin",required = false) BigDecimal priceMin,
            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
            @RequestParam(name = "size",defaultValue = "20",required = false) Integer size,
            @RequestParam(name = "options",required = false) List<Integer> optionsId,
            @RequestParam(name="name",required = false) String name){
        BigDecimal[] options = null;
        if(priceMin!=null && priceMax!=null){
            options=new BigDecimal[]{priceMin, priceMax};
        }
        return productService.findProductAdvance(name,category,optionsId,options,page,size);
    }
    @RequestMapping(value="/custom",method = RequestMethod.GET)
    public List<Product> getProductCustom(HttpServletRequest request,
                                          @RequestParam(name ="page",defaultValue = "0") Integer page,
                                          @RequestParam(name = "size",defaultValue = "20") Integer size){
        ArrayList<ProductSpecification> list=new ArrayList<>();
        request.getParameterMap()
                .forEach((key,value)->{
                    String[] extract=value[0].split("[()]");
                    if(extract.length==2) {
                        list.add(new ProductSpecification(new DynamicSearch(key, extract[1], DynamicSearch.Operator.valueOf(extract[0]))));
                    }
                });
        Pageable pageable= (Pageable) PageRequest.of(page,size);
        return productService.findAll(list,pageable);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Product createProduct(
            @RequestPart("image") MultipartFile image ,
            Product product) {
        product.setPicture((String) cloudinaryService.upload(image).get("url"));
        return productService.create(product);
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
    @RequestMapping(value = "/{product_id}/items",method = RequestMethod.POST)
    public Product createAllByProduct(@PathVariable(name = "product_id") Integer productId,
                                          @RequestBody List<ProductItem> productItem){
        return productItemService.saveAll(productId,productItem);
    }

    @RequestMapping(value = "/{product_id}/item",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductItem addProductVariation(@PathVariable(name = "product_id") Integer productId,
                                           @RequestPart(name="image",required = false) MultipartFile image,
                                           @RequestPart(name="productItem") ProductItem item){
        if(image!=null&&!image.isEmpty()){
            item.setPicture((String) cloudinaryService.upload(image).get("url"));
        }
        System.out.println(item);
        return productItemService.create(productId,item);
    }

    @RequestMapping(value = "/{product_id}/item/{item_id}",method = RequestMethod.PUT)
    public ProductItem updateProductItem(@PathVariable(name = "product_id",required = false) Integer productId,
                                          @PathVariable(name = "item_id") Integer itemId,
                                          @RequestBody ProductItem productItem){
        return productItemService.update(itemId,productItem);
    }
    @RequestMapping(value = "/{product_id}/item/{item_id}",method = RequestMethod.DELETE)
    public void deleteProductItemById(@PathVariable(name = "product_id",required = false) Integer productId,
                                         @PathVariable(name = "item_id") Integer itemId){
        productItemService.deleteById(itemId);
    }

}
