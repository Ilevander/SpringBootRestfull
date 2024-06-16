package com.ilyass.admin.service.impl;

import com.ilyass.admin.dao.RoleDao;
import com.ilyass.admin.dao.UserDao;
import com.ilyass.admin.entity.Role;
import com.ilyass.admin.entity.User;
import com.ilyass.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RoleDao roleDao;

    public UserServiceImpl(UserDao userDao,RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public User loadUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User createUser(String email, String password) {
        return userDao.save(new User(email, password));
    }

    @Override
    public void assignRoleToUser(String email, String roleName) {
           User user = loadUserByEmail(email);
           Role role = roleDao.findByName(roleName);
           user.assignRoleToUser(role);
    }
}
