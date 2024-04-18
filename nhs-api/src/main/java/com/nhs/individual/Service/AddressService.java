package com.nhs.individual.Service;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Repository.AddressRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository repository;
    public Address update(Integer id,Address address){
        return repository.save(repository.findById(id)
                .map(oldAddress-> ObjectUtils.merge(oldAddress,address,Address.class))
                .orElseThrow(RuntimeException::new));
    }
    public Address save(Address address){
        return repository.save(address);
    }
    public Optional<Address> findById(Integer id){
        return repository.findById(id);
    }
    public Collection<Address> findAll(){
        return repository.findAll();
    }
    public void deleteById(int id){
        repository.deleteById(id);
    }
}

