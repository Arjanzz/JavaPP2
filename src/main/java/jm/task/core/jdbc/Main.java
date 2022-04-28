package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vladimir", "Pushkin", (byte) 120);
        userService.saveUser("Vladimir", "Putin", (byte) 666);
        userService.saveUser("Alena", "Trubinskaya", (byte) 20);
        userService.saveUser("Masha", "Maria", (byte) 24);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();


        // реализуйте алгоритм здесь
    }
}
