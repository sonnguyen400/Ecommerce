package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Category;
import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.CategoryService;
import com.nhs.individual.Service.VariationOptionService;
import com.nhs.individual.Service.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private VariationService variationService;
    @Autowired
    VariationOptionService variationOptionService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Iterable<Category> findAll(){
        return categoryService.findAll();
    }
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Category create(@RequestBody Category category){
        return categoryService.create(category);
    }
    @RequestMapping(value = "/{parent_id}",method = RequestMethod.POST)
    public Category createChild(@RequestBody Category category,
                                @PathVariable(name = "parent_id") Integer id){
        return categoryService.addChild(id,category);
    }
    @RequestMapping(value = "/{category_id}",method = RequestMethod.GET)
    public Category findById(@PathVariable(name = "category_id") Integer id){
        return categoryService.findById(id).orElseThrow(()->  new ResourceNotFoundException("Could not find category with id: "+id));
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


    //Variation methods

    @RequestMapping(value = "/{category_id}/variation",method = RequestMethod.POST)
    public Variation createVariation(
            @RequestBody Variation variation,
            @PathVariable(name = "category_id",required = true) Integer categoryId){
        return variationService.create(categoryId,variation);
    }
    @RequestMapping(value = "/{category_id}/variation/{variation_id}",method = RequestMethod.GET)
    public Variation getById(@PathVariable(name = "category_id",required = false) Integer categoryId,
                             @PathVariable(name = "variation_id") Integer variationId){
        return variationService.findById(variationId).orElseThrow(()->new ResourceNotFoundException("Could not find variation"));
    }
    @RequestMapping(value = "/variation/{variation_id}",method = RequestMethod.PUT)
    public Variation updateById(@PathVariable(name = "variation_id",required = false) Integer variationId,
                                @RequestBody Variation variation){
        return variationService.updateById(variationId,variation);
    }
    @RequestMapping(value = "/variation/{variation_id}",method = RequestMethod.DELETE)
    public void deleteVariationById(@PathVariable(name = "variation_id") Integer variationId){
        variationService.deleteById(variationId);
    }

}
