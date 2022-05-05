package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(80), lastName VARCHAR(80), age TINYINT);";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            String sql = "DROP TABLE IF EXISTS users;";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws Exception {
        Session session = Util.getSessionFactory().openSession();
        User user = new User(name, lastName, age);
        try {
            session.getTransaction().begin();
            session.save(user);
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            throw new Exception();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) throws Exception {
        Session session = Util.getSessionFactory().openSession();
        try {
            User user = session.get(User.class, id);
            session.getTransaction().begin();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            throw new Exception();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        Session session = Util.getSessionFactory().openSession();
        List<User> users;
        try {
            session.getTransaction().begin();
            users = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            throw new Exception();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() throws Exception {
        Session session = Util.getSessionFactory().openSession();
        try {
            session.getTransaction().begin();
            Query query = session.createSQLQuery("DELETE FROM users");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction().getStatus().canRollback()) {
                session.getTransaction().rollback();
            }
            throw new Exception();
        } finally {
            session.close();
        }
    }
}