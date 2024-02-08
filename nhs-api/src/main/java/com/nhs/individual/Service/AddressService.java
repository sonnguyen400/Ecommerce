package com.nhs.individual.Service;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Repository.AddressRepository;
import com.nhs.individual.Utils.ObjectUtils;
import io.rsocket.exceptions.InvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
public class AddressService {
    @Autowired
    AddressRepository repository;
    public Address save(Integer id,Address address){
        return repository.save(repository.findById(id).map(oldAddress->{
            return new ObjectUtils<Address>().merge(oldAddress,address,Address.class);
        }).orElseThrow(RuntimeException::new));
    }
}
