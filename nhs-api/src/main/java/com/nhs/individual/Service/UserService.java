package com.nhs.individual.Service;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressService service;
    public Optional<User> findById(int id){
        return userRepository.findById(id);
    }
    public User create(User user){
        return userRepository.save(user);
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }



}
