package com.ilyass.admin;

import com.ilyass.admin.dao.*;
import com.ilyass.admin.utility.OperationUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootRestfullApplication implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private InstructorDao instructorDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RoleDao roleDao;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestfullApplication.class, args);
    }
    /**
     * To Test Operation Utilities for All Entities
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
//        OperationUtility.usersOperations(userDao);
//        OperationUtility.rolesOperations(roleDao);
//        OperationUtility.assignRolesToUser(userDao,roleDao);
//        OperationUtility.instructorsOperations(userDao,instructorDao,roleDao);
//        OperationUtility.studentOperations(userDao,studentDao,roleDao);
        OperationUtility.activityOperations(activityDao,instructorDao,studentDao);
    }
}
