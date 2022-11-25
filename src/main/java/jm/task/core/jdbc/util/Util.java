package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "root";
        String password = "rootroot";
        String url = "jdbc:mysql://localhost:3306/new_schema";
        return DriverManager.getConnection(url, username, password);
        }

    }

