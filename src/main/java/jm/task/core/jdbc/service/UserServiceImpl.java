package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl ud = new UserDaoJDBCImpl();
    public void createUsersTable() {
        ud.createUsersTable();
    }

    public void dropUsersTable() {
        ud.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        ud.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        ud.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return ud.getAllUsers();
    }

    public void cleanUsersTable() {
            ud.cleanUsersTable();
    }
}
