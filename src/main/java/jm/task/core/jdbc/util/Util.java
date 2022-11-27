package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    static {
        Properties prop= new Properties();

        prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/new_schema");

        //You can use any database you want, I had it configured for Postgres
        prop.setProperty("dialect", "org.hibernate.dialect.MySQL8Dialect");

        prop.setProperty("hibernate.connection.username", "root");
        prop.setProperty("hibernate.connection.password", "rootroot");
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        prop.setProperty(Environment.SHOW_SQL, "true"); //If you wish to see the generated sql query
        prop.setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        prop.setProperty(Environment.HBM2DDL_AUTO, "drop-and-create");

        try{
            sessionFactory = new Configuration().addProperties(prop).addAnnotatedClass(User.class).buildSessionFactory();
        }catch (HibernateException e){
            System.out.println("FUCK" + e);
        }

    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String username = "root";
        String password = "rootroot";
        String url = "jdbc:mysql://localhost:3306/new_schema";
        return DriverManager.getConnection(url, username, password);
        }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;

    }

    }

