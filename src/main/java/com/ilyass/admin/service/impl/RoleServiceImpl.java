package com.ilyass.admin.service.impl;

import com.ilyass.admin.dao.RoleDao;
import com.ilyass.admin.entity.Role;
import com.ilyass.admin.service.RoleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    //DI By Constructor As parameter
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role createRole(String roleName) {
          return roleDao.save(new Role(roleName));
    }
}
