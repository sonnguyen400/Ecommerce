package com.nhs.individual.Service;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Repository.UserRepository;
import com.nhs.individual.Specification.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public User save(User user){
        return userRepository.save(user);
    }
    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
    public User findByAccountId(Integer accountId){
        return userRepository.findByAccount_id(accountId);
    }
    public List<User> findAll(int page,int size){
        return userRepository.findAll(PageRequest.of(page, size)).getContent();
    }
    public List<User> findAll(List<UserSpecification> specifications, PageRequest pageRequest){
        Specification<User> user=Specification.where(specifications.get(0));
        for(int i=1;i<specifications.size();i++){
            user.or(specifications.get(i));
        }
        return userRepository.findAll(user,pageRequest).getContent();
    }


}
