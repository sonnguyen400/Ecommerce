package com.nhs.individual.Service;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Repository.UserAddressRepository;
import com.nhs.individual.Repository.UserRepository;
import com.nhs.individual.Utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    AddressService service;
    public User findById(int id){
        return repository.findById(id).orElseGet(User::new);
    }
    public User insert(User user){
        return repository.save(user);
    }
    public Collection<Address> findAddressByUserid(Integer id){
        return userAddressRepository.findAllByUser_id(id).stream().map(UserAddress::getAddress).toList();
    }
    public Address insertUserAddress(Address address,User user){
        UserAddress userAddress=new UserAddress();
        userAddress.setUser(user);
        userAddress.setAddress(address);
        return userAddressRepository.save(userAddress).getAddress();
    }
    public void deleteUserAddressById(Integer id){
        userAddressRepository.deleteById(id);
    }

}
