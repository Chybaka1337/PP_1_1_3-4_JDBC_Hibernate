package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceHibernateImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) throws Exception {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceHibernateImpl();
        userService.createUsersTable();
        userService.saveUser("Зоммер", "Андрей", (byte) 22);
        userService.saveUser("Лев", "Толстой", (byte) 82);
        userService.saveUser("Райан", "Гослинг", (byte) 43);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
