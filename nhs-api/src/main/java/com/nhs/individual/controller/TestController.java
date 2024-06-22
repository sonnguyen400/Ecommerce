package com.nhs.individual.controller;

import com.nhs.individual.domain.ProductItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/productItems",method = RequestMethod.POST)
    public List<ProductItem> postItems(@RequestPart(name="images")MultipartFile images,
                             @RequestPart(name = "product") List<ProductItem> productItems
    ) {
        return productItems;
    }
//    @RequestMapping(value = "/products",method = RequestMethod.GET)
//    public List<Product> getProducts(
//            HttpServletRequest request,
//            @RequestParam(name = "category") Integer category
//
//
//    ){
//        Map<String,String> stringMap = new HashMap<String,String>();
//        request.getParameterMap().forEach((key, value) -> stringMap.put(key, value[0]));
//        return productDAO.findByVariation(stringMap,category);
//    }

//    @RequestMapping(value = "/products",method = RequestMethod.GET)
//    public List<Product> getProducts(
//            HttpServletRequest request,
//            @RequestParam(name = "category",required = false) Integer category,
//            @RequestParam(name ="priceMax",required = false) BigDecimal priceMax,
//            @RequestParam(name = "priceMin",required = false) BigDecimal priceMin,
//            @RequestParam(name = "page",defaultValue = "0",required = false) Integer page,
//            @RequestParam(name = "size",defaultValue = "20",required = false) Integer size,
//            @RequestParam(name = "options",required = false) List<Integer> optionsId){
//        BigDecimal[] options = null;
//        if(priceMin!=null && priceMax!=null){
//            options=new BigDecimal[]{priceMin, priceMax};
//        }
//        return productDAO.findByVariation(category,optionsId,options,page,size);
//    }
}
