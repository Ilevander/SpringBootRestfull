package com.ilyass.admin.service;

import com.ilyass.admin.entity.User;

public interface UserService {
    User loadUserByEmail(String email);
    User createUser(String email , String password);
    void assignRoleToUser(String email,String roleName);
}
