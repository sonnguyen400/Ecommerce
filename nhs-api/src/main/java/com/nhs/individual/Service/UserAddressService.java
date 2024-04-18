package com.nhs.individual.Service;

import com.nhs.individual.Domain.Address;
import com.nhs.individual.Domain.UserAddress;
import com.nhs.individual.Domain.UserAddressId;
import com.nhs.individual.Repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserAddressService {
    @Autowired
    UserAddressRepository userAddressRepository;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;
    public Collection<UserAddress> findAllByUserId(Integer id) {
        return userAddressRepository.findAllByUser_id(id);
    }

    @Transactional
    public UserAddress create(Integer userId,UserAddress userAddress) {
        Address address=addressService.save(userAddress.getAddress());
        userAddressRepository.deSelectDefaultAddress(userId);
        return userService.findById(userId).map((user)->{
            UserAddressId userAddressId=new UserAddressId(userId,address.getId());
            userAddress.setId(userAddressId);
            userAddress.setUser(user);
            userAddress.setAddress(address);
            return userAddressRepository.save(userAddress);
        }).orElseThrow(()->new RuntimeException("Could not find user"));
    }
    public Optional<UserAddress> findByUseridAndAddressid(Integer userId,Integer addressid) {
        return userAddressRepository.findAllByUser_idAndAddress_id(userId,addressid);
    }
    public UserAddress update(Integer id,UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }
    public void deleteById(Integer userId,Integer addressid) {
        userAddressRepository.deleteById(new UserAddressId(userId,addressid));
    }
    public void setDefaultUserAddress(UserAddress userAddress){
        List<UserAddress> userAddressList= findAllByUserId(userAddress.getId().getUserId()).stream().peek(address->{
            address.setIsDefault(address.getId().equals(userAddress.getId()));
        }).toList();
        userAddressRepository.saveAll(userAddressList);
    }
    public void setBusinessAddress(Integer userAddressId){
        userAddressRepository.updateBusinessAddress(userAddressId);
    }
    public UserAddress setDefaultAddress(Integer userId,Integer  addressId){
        return  userAddressRepository.updateDefaultAddressByAddressId(userId,addressId);
    }
}
