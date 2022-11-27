package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createNativeQuery("CREATE TABLE IF NOT EXISTS users(id bigint not null auto_increment unique, name CHAR(10) not null, lastName CHAR(10) not null, age middleint not null, primary key(id))", User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createNativeQuery("DROP TABLE IF EXISTS users", User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createNativeQuery("insert into users(name, lastName, age) values((?), (?), (?))", User.class);
            query.setParameter(1, name);
            query.setParameter(2, lastName);
            query.setParameter(3, age);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<User> query = session.createNativeQuery("delete from users where id = (?)", User.class);
            query.setParameter(1, id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createNativeQuery("select * from users", User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }


        @Override
        public void cleanUsersTable () {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                Query<User> query = session.createNativeQuery("truncate users", User.class);
                query.executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                System.out.println(e);
            }
        }
    }

