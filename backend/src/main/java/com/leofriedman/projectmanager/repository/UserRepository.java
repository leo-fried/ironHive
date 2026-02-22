package com.leofriedman.projectmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.leofriedman.projectmanager.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    User findByHashedId(String hashedId);
}