package com.nhs.individual.Controller;

import com.nhs.individual.DAO.DAOImp.ProductDAO;
import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ProductDAO productDAO;
    @RequestMapping(value = "/productItems",method = RequestMethod.POST)
    public List<ProductItem> postItems(@RequestPart(name="images")MultipartFile images,
                             @RequestPart(name = "product") List<ProductItem> productItems
    ) {
        return productItems;
    }
    @RequestMapping(value = "/products",method = RequestMethod.GET)
    public List<Product> getProducts(
            HttpServletRequest request,
            @RequestParam(name = "category") Integer category


    ){
        Map<String,String> stringMap = new HashMap<String,String>();
        request.getParameterMap().forEach((key, value) -> stringMap.put(key, value[0]));
        return productDAO.findByVariation(stringMap,category);
    }
}
