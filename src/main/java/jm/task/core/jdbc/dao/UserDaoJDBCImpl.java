package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl  implements UserDao  {
    Connection con;

    {
        try {
            con = Util.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl()  {

    }

    public void createUsersTable()  {
        try(Statement statement = con.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users(id bigint not null auto_increment, name CHAR(10) not null, lastName CHAR(10) not null, age middleint not null, primary key(id))");
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void dropUsersTable() {
        try(Statement statement = con.createStatement()){
        statement.executeUpdate("DROP TABLE IF EXISTS Users");}
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public void saveUser(String name, String lastName, byte age)  {
        String sql = "insert into users(name, lastName, age) values((?), (?), (?))";
        try(PreparedStatement pS = con.prepareStatement(sql)){
        pS.setString(1, name);
        pS.setString(2, lastName);
        pS.setByte(3, age);
        pS.executeUpdate();
        System.out.println("Юзер с именем -  " + name +  " добавлен в базу.");}
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public void removeUserById(long id)  {
        String sql = "delete from users where id = (?)";
        try(PreparedStatement pS = con.prepareStatement(sql)){
        pS.setLong(1, id);}
        catch (SQLException e){
            System.out.println(e);
        }
    }

    public List<User> getAllUsers()  {
        List<User> userList = new ArrayList<>();
        try(Statement statement = con.createStatement()){
        ResultSet rs = statement.executeQuery("select * from users");
        while (rs.next()){
            User user = new User();
            user.setName(rs.getString(2));
            user.setLastName(rs.getString(3));
            user.setAge(rs.getByte(4));
            userList.add(user);
        }
        return userList;
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

    public void cleanUsersTable() {
        try(Statement statement = con.createStatement()){
        statement.executeUpdate("truncate users");}
        catch (SQLException e){
            System.out.println(e);}
    }
}
