package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        List<User> users = new ArrayList<>();
        users.add(new User("Alex", "Vasya", (byte) 15));
        users.add(new User("Vanya", "Sivurov", (byte) 23));
        users.add(new User("Ura", "Shtramyan", (byte) 24));
        users.add(new User("Legenda", "Alim", (byte) 22));
        UserServiceImpl us = new UserServiceImpl();
        us.createUsersTable();
        Iterator iterator = users.iterator();
        while (iterator.hasNext()){
            User user = (User) iterator.next();
            us.saveUser(user.getName(), user.getLastName(), user.getAge());
        }
        System.out.println(us.getAllUsers());
//        us.cleanUsersTable();
//        us.dropUsersTable();
    }
}
