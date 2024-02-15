package com.nhs.individual.Service;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Repository.AddressRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository repository;
    public Address save(Integer id,Address address){
        return repository.save(repository.findById(id).map(oldAddress->{
            return ObjectUtils.merge(oldAddress,address,Address.class);
        }).orElseThrow(RuntimeException::new));
    }
}
