package com.nhs.individual.Service;

import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserAddressService {
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    UserService userService;
    public Collection<UserAddress> findAllByUserId(Integer id) {
        return userAddressRepository.findAllByUser_id(id);
    }
    public UserAddress create(Integer userId,UserAddress userAddress) {
        return userService.findById(userId).map(user->{
            userAddress.setUser(user);
            return userAddressRepository.save(userAddress);
        }).orElseThrow(()->new RuntimeException("Could not find user"));
    }
    public Optional<UserAddress> findByUseridAndAddressid(Integer userId,Integer addressid) {
        return userAddressRepository.findAllByUser_idAndAddress_id(userId,addressid);
    }
    public UserAddress update(Integer id,UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }
    public void deleteById(Integer id) {
        userAddressRepository.deleteById(id);
    }
    public void setDefaultUserAddress(UserAddress userAddress){
        List<UserAddress> userAddressList= findAllByUserId(userAddress.getId().getUserId()).stream().map(address->{
            if(address.getId().equals( userAddress.getId())){
                address.setIsDefault(true);
            }else{
                address.setIsDefault(false);
            }
            return  address;
        }).toList();
        userAddressRepository.saveAll(userAddressList);
    }
}
