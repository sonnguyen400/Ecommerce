package com.nhs.individual.Repository;

import com.nhs.individual.Domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {
    public Collection<UserAddress> findAllByUser_id(Integer userid);
}
