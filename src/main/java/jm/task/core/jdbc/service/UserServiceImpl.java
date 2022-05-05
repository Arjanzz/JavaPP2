package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws Exception {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws Exception {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() throws Exception {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() throws Exception {
        userDao.cleanUsersTable();
    }
}