package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Domain.ProductItem;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
}
