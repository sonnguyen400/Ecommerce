package com.nhs.individual.Service;

import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Repository.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariationService {
    @Autowired
    VariationRepository variationRepository;
    public Variation create(Variation variation){
        return variationRepository.save(variation);
    }
    public Optional<Variation> findById(int id){
        return variationRepository.findById(id);
    }
    public void deleteById(int id){
        variationRepository.deleteById(id);
    }
    public Variation update(Integer id,Variation variation) throws ChangeSetPersister.NotFoundException {
        return variationRepository.save(findById(id).map(oldVariation->{
            oldVariation.setName(variation.getName());
            return oldVariation;
        }).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
