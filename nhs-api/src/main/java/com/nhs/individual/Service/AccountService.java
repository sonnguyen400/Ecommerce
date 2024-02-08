package com.nhs.individual.Service;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.User;
import com.nhs.individual.Repository.AccountRepository;
import com.nhs.individual.Repository.UserRepository;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;
    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public User insert(User user){
        findByUsername(user.getAccount().getUsername()).ifPresent((userAccount)->{
            throw new NonUniqueObjectException("User's account already exists",userAccount.getUsername());
        });
        return userRepository.save(user);
    }
    public Optional<Account> findById(int id){
        return repository.findById(id);
    }
    public Optional<Account> findByUsername(String username){
        return repository.findAccountByUsername(username);
    }
}
