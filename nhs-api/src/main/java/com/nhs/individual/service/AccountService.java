package com.nhs.individual.service;

import com.nhs.individual.constant.AccountProvider;
import com.nhs.individual.constant.AccountRole;
import com.nhs.individual.Domain.Account;
import com.nhs.individual.Domain.Role;
import com.nhs.individual.repository.AccountRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;
    BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public Account create(Account account){
        Role role=new Role();
        role.setId(AccountRole.USER.id);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setProvider(AccountProvider.SYSTEM);
        findByUsername(account.getUsername()).ifPresent((account1)->{
            throw new NonUniqueObjectException("Account's username already exists",account.getUsername());
        });

        account.setRoles(List.of(role));
        return repository.save(account);
    }
    public Optional<Account> findById(int id){
        return repository.findById(id);
    }
    public Optional<Account> findByUsername(String username){
        return repository.findAccountByUsername(username);
    }
}
