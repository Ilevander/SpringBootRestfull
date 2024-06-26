package com.ilyass.admin.dao;

import com.ilyass.admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
}