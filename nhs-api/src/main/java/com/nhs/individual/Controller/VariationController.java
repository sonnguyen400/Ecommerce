package com.nhs.individual.Controller;

import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Domain.VariationOption;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Service.VariationOptionService;
import com.nhs.individual.Service.VariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/variation")
public class VariationController {
    @Autowired
    VariationService variationService;
    @Autowired
    VariationOptionService variationOptionService;
    @RequestMapping(value = "/{variation_id}", method = RequestMethod.GET)
    public Variation getById(@PathVariable(name = "variation_id") Integer variationId) {
        return variationService.findById(variationId).orElseThrow(()->new ResourceNotFoundException("Could not find variation"));
    }
    @RequestMapping(value = "/{variation_id}", method = RequestMethod.PUT)
    public Variation updateById(@PathVariable(name = "variation_id") Integer variationId,
                                @RequestBody Variation variation) {
        return variationService.updateById(variationId,variation);
    }
    @RequestMapping(value = "/{variation_id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(name = "variation_id") Integer variationId) {
        variationService.deleteById(variationId);
    }

    @RequestMapping(value = "/{variation_id}/option",method = RequestMethod.GET)
    public Collection<VariationOption> findAll(
            @PathVariable(name = "variation_id") Integer variationId
    ) {
        return variationOptionService.findAllByVariationId(variationId);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Variation create(@RequestBody Variation variation) {
        return variationService.create(variation.getCategory().getId(),variation);
    }

}
