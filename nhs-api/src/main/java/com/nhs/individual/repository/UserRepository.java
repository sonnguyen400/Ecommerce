package com.nhs.individual.repository;

import com.nhs.individual.domain.Account;
import com.nhs.individual.domain.User;
import io.micrometer.common.lang.NonNull;
import io.micrometer.common.lang.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from user where id in (select user_id from account  where id=?1) ",nativeQuery = true)
    User findByAccount_id(int accountId);
    @NonNull
    Page<User> findAll(@Nullable Specification<User> specification, @NonNull Pageable pageable);
    List<User> findAllByEmailOrPhoneNumber(String email, String phoneNumber);
}
