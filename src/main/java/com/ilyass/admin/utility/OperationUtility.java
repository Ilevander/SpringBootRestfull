//package com.ilyass.admin.utility;
//
//import com.ilyass.admin.dao.*;
//import com.ilyass.admin.entity.*;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//import java.util.Optional;
//
//public class OperationUtility {
//    /**
//     * USER OPERATIONS
//     * @param userDao
//     */
//    public static void usersOperations(UserDao userDao) {
//        createUsers(userDao);
//        //updateUser(userDao);
//        //deleteUser(userDao);
//        //fetchUsers(userDao);
//    }
//
//    /**
//     * ROLE OPERATIONS
//     * @param roleDao
//     */
//    public static void rolesOperations(RoleDao roleDao) {
//        createRoles(roleDao);
//        //updateRoles(roleDao);
//        //deleteRole(roleDao);
//        //fetchRole(roleDao);
//    }
//
//    /**
//     * INSTRUCTOR OPERATIONS
//     * @param instructorDao
//     */
//    public static void instructorsOperations(UserDao userDao,InstructorDao instructorDao,RoleDao roleDao) {
//        createInstructors(userDao,instructorDao,roleDao);
//        //updateInstructor(instructorDao);
//        //removeInstructor(instructorDao);
//        //fetchInstructors(instructorDao);
//    }
//
//    /**
//     * STUDENT OPERATIONS
//     * @param studentDao
//     */
//    public static void studentOperations(UserDao userDao , StudentDao studentDao , RoleDao roleDao) {
//        createStudent(userDao , studentDao , roleDao);
//        //updateStudent(studentDao);
//        //removeStudent(studentDao);
//        //fetchStudents(studentDao);
//    }
//
//    /**
//     * ACTIVITY OPERATIONS
//     * @param activityDao
//     */
//    public static void activityOperations(ActivityDao activityDao, InstructorDao instructorDao, StudentDao studentDao) {
////        createActivities(activityDao,instructorDao);
////        updateActivities(activityDao);
////        deleteActivity(activityDao);
////        fetchActivities(activityDao);
////        assignStudentsToActivities(activityDao, studentDao);
//        fetchActivitiesForStudent(activityDao);
//    }
//
//
//    // IMPLEMENTATION AND PROCESS OF OPERATIONS
//
//    private static void createActivities(ActivityDao activityDao, InstructorDao instructorDao) {
//        Instructor instructor = instructorDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor Not Found"));
//
//        Activity activity1 = new Activity("Hibernate", "Fall 2024", "Database", "Group A", "Introduction to Hibernate", "7 Hours", instructor);
//        activityDao.save(activity1);
//
//        Activity activity2 = new Activity("Spring Boot", "Spring 2024", "Software Development", "Group B", "Master Spring Boot From Zero To Hero", "12 Hours", instructor);
//        activityDao.save(activity2);
//    }
//
//
//    private static void updateActivities(ActivityDao activityDao) {
//        Activity activity = activityDao.findById(1L).orElseThrow(()->new EntityNotFoundException("activity Not Found"));
//        activity.setActivityDuration("24 Hours");
//        activityDao.save(activity);
//    }
//
//    private static void deleteActivity(ActivityDao activityDao) {
//        activityDao.deleteById(2L);
//    }
//
//    private static void fetchActivities(ActivityDao activityDao) {
//        activityDao.findAll().forEach(activity -> System.out.println(activity.toString()));
//    }
//
//    private static void assignStudentsToActivities(ActivityDao activityDao, StudentDao studentDao) {
//        Optional<Student> student1 = studentDao.findById(1L);
//        Optional<Student> student2 = studentDao.findById(2L);
//        Activity activity = activityDao.findById(1L).orElseThrow(()->new EntityNotFoundException("Activity Not Found"));
//
//        student1.ifPresent(activity::assignStudentToActivity);
//        student2.ifPresent(activity::assignStudentToActivity);
//        activityDao.save(activity);
//    }
//
//    private static void fetchActivitiesForStudent(ActivityDao activityDao) {
//        activityDao.getActivitiesByStudentId(1L).forEach(activity -> System.out.println(activity.toString()));
//    }
//
//    private static void createStudent(UserDao userDao, StudentDao studentDao, RoleDao roleDao) {
//        Role role = roleDao.findByName("Student");
//        if (role == null) throw new EntityNotFoundException("Role not found");
//
//        User user1 = new User("stdUser1@gmail.com","pass1");
//        userDao.save(user1);
//        user1.assignRoleToUser(role);
//        Student student1 = new Student("student1FN","student1LN","master",user1);
//        studentDao.save(student1);
//
//        User user2 = new User("stdUser2@gmail.com","pass2");
//        userDao.save(user2);
//        user2.assignRoleToUser(role);
//        Student student2 = new Student("student2FN","student2LN","Phd",user2);
//        studentDao.save(student2);
//
//        User user3 = new User("stdUser3@gmail.com","pass3");
//        userDao.save(user3);
//        user3.assignRoleToUser(role);
//        Student student3 = new Student("student3FN","student3LN","BTS",user3);
//        studentDao.save(student3);
//    }
//
//    private static void updateStudent(StudentDao studentDao) {
//        Student student = studentDao.findById(2L).orElseThrow(()->new EntityNotFoundException("Student not found"));
//        student.setFirstName("updatedStdFN");
//        student.setLastName("updatedStdLN");
//        studentDao.save(student);
//    }
//
//    private static void removeStudent(StudentDao studentDao) {
//        studentDao.deleteById(1L);
//    }
//
//    private static void fetchStudents(StudentDao studentDao) {
//        studentDao.findAll().forEach(student -> System.out.println(student.toString()));
//    }
//
//    private static void fetchInstructors(InstructorDao instructorDao) {
//        instructorDao.findAll().forEach(instructor -> System.out.println(instructor.toString()));
//    }
//
//    private static void removeInstructor(InstructorDao instructorDao) {
//        instructorDao.deleteById(2L);
//    }
//
//    private static void updateInstructor(InstructorDao instructorDao) {
//        Instructor instructor = instructorDao.findById(1L).orElseThrow(() -> new EntityNotFoundException("Instructor not found"));
//        instructor.setSummary(("Certified Instructor"));
//        instructorDao.save(instructor);
//    }
//
//    private static void createInstructors(UserDao userDao, InstructorDao instructorDao, RoleDao roleDao) {
//        Role role = roleDao.findByName("Instructor");
//        if (role == null) throw new EntityNotFoundException("Role not found");
//
//        User user1 = new User("InstructorUser1@gmail.com","pass1");
//        user1.assignRoleToUser(role);
//        userDao.save(user1);
//        Instructor instructor1 = new Instructor("Instructor1FN","Instructor1LN","Experienced Instructor",user1);
//        instructorDao.save(instructor1);
//
//        User user2 = new User("InstructorUser2@gmail.com","pass2");
//        user2.assignRoleToUser(role);
//        userDao.save(user2);
//        Instructor instructor2 = new Instructor("Instructor2FN","Instructor2LN","Senior Instructor",user2);
//        instructorDao.save(instructor2);
//
//        User user3 = new User("InstructorUser3@gmail.com","pass3");
//        user3.assignRoleToUser(role);
//        userDao.save(user3);
//        Instructor instructor3 = new Instructor("Instructor3FN","Instructor3LN","Senior Instructor",user3);
//        instructorDao.save(instructor3);
//    }
//
//    private static void fetchRole(RoleDao roleDao) {
//        roleDao.findAll().forEach(role-> System.out.println(role.toString()));
//    }
//
//    public static void assignRolesToUser(UserDao userDao, RoleDao roleDao) {
//        Role role = roleDao.findByName("Admin");
//        if (role == null) throw new EntityNotFoundException("Role Not Found");
//        List<User> users = userDao.findAll();
//        users.forEach(user -> {
//            user.assignRoleToUser(role);
//            userDao.save(user);
//        });
//    }
//
//    private static void deleteRole(RoleDao roleDao) {
//        roleDao.deleteById(2L);
//    }
//
//    private static void updateRoles(RoleDao roleDao) {
//        Role role = roleDao.findById(1L).orElseThrow(()->new EntityNotFoundException("Role not found"));
//        role.setName("NewAdmin");
//        roleDao.save(role);
//    }
//
//    private static void createRoles(RoleDao roleDao) {
//        Role role1 = new Role("Admin");
//        roleDao.save(role1);
//        Role role2 = new Role("Instructor");
//        roleDao.save(role2);
//        Role role3 = new Role("Student");
//        roleDao.save(role3);
//    }
//
//
//    private static void createUsers(UserDao userDao) {
//        User user1 = new User("user1@gmail.com","pass1");
//        userDao.save(user1);
//        User user2 = new User("user2@gmail.com","pass2");
//        userDao.save(user2);
//        User user3 = new User("user3@gmail.com","pass3");
//        userDao.save(user3);
//        User user4 = new User("user4@gmail.com","pass4");
//        userDao.save(user4);
//    }
//
//    private static void updateUser(UserDao userDao) {
//        User user = userDao.findById(2L).orElseThrow(()->new EntityNotFoundException("User not found"));
//        user.setEmail("newEmail@gmail.com");
//        userDao.save(user);
//    }
//
//    private static void deleteUser(UserDao userDao) {
//        User user = userDao.findById(3L).orElseThrow(()->new EntityNotFoundException("User not found"));
//        userDao.delete(user);
//        userDao.deleteById(3L);
//    }
//
//    private static void fetchUsers(UserDao userDao) {
//        userDao.findAll().forEach(user->System.out.println(user.toString()));
//    }
//}
