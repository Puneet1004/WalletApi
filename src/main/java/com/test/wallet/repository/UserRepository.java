package com.test.wallet.repository;

import com.test.wallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByPhoneNumber(String phone);
}
