package com.nhs.individual.Repository;

import com.nhs.individual.Domain.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Integer> {
    public Collection<UserAddress> findAllByUser_id(Integer userid);
    public Optional<UserAddress> findAllByUser_idAndAddress_id(Integer userId,Integer addressId);
}
