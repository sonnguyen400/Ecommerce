package com.nhs.individual.Service;

import com.nhs.individual.Domain.Account;
import com.nhs.individual.Repository.AccountRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public Account create(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        findByUsername(account.getUsername()).ifPresent((account1)->{
            throw new NonUniqueObjectException("Account's username already exists",account.getUsername());
        });
        return repository.save(account);
    }
    public Optional<Account> findById(int id){
        return repository.findById(id);
    }
    public Optional<Account> findByUsername(String username){
        return repository.findAccountByUsername(username);
    }
}
