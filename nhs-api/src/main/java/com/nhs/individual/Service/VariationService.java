package com.nhs.individual.Service;

import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.VariationRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class VariationService {
    @Autowired
    VariationRepository variationRepository;
    @Autowired
    CategoryService categoryService;
    public Variation create(int categoryId,Variation variation){
        return categoryService.findById(categoryId).map(category->{
            variation.setCategory(category);
            return variationRepository.save(variation);
        }).orElseThrow(()->new ResourceNotFoundException("Could not find category"));
    }
    public Variation updateById(int variationId,Variation variation){
        return variationRepository.save(findById(variationId).map(oldVariation->{
            oldVariation.setName(variation.getName());
            return variationRepository.save(ObjectUtils.merge(oldVariation,variation,Variation.class));
        }).orElseThrow(()->new ResourceNotFoundException("Could not find variation")));
    }
    public Optional<Variation> findById(int id){
        return variationRepository.findById(id);
    }
    public void deleteById(int id){
        variationRepository.deleteById(id);
    }
    public Collection<Variation> findAllByCategoryId(int categoryId){
        return variationRepository.findAllByCategory_Id(categoryId);
    }
}
