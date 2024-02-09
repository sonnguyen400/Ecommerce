package com.nhs.individual.Service;

import com.nhs.individual.Domain.VariationOption;
import com.nhs.individual.Repository.VariationOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariationOptionService {
    @Autowired
    VariationOptionRepository variationOptionRepository;
    public VariationOption create(VariationOption variationOption){
        return variationOptionRepository.save(variationOption);
    }
    public Optional<VariationOption> findById(int id){
        return variationOptionRepository.findById(id);
    }
    public void deleteById(int id){
        variationOptionRepository.deleteById(id);
    }
    public VariationOption update(Integer id,VariationOption variationOption) throws ChangeSetPersister.NotFoundException {
        return variationOptionRepository.save(findById(id).map(oldVariationOption->{
            oldVariationOption.setValue(variationOption.getValue());
            return oldVariationOption;
        }).orElseThrow(ChangeSetPersister.NotFoundException::new));
    }
}
