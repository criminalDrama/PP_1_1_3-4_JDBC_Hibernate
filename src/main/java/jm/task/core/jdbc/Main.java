package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args)  {

        UserServiceImpl ud = new UserServiceImpl();
        ud.dropUsersTable();
        ud.createUsersTable();
        ud.saveUser("Akex", "dfgdfg", (byte) 3);
        ud.saveUser("Akex", "dfgdfg", (byte) 3);
        System.out.println(ud.getAllUsers());
        ud.cleanUsersTable();

    }
}
