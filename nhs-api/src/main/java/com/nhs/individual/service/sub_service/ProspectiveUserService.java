package com.nhs.individual.service.sub_service;

import com.nhs.individual.repository.sub_repository.ProspectiveUserRepository;
import com.nhs.individual.views.Prospectiveuser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProspectiveUserService {
    ProspectiveUserRepository repository;
    public List<Prospectiveuser> findAll(){
        return repository.findAll();
    }
}
