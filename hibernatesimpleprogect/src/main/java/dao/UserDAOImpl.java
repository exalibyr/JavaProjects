package dao;

import models.Auto;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;
import dao.operations.*;

import javax.swing.*;
import java.util.List;


public class UserDAOImpl implements UserDAO {


    public User findById(int id) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        User user = null;
        try {
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return user;
        }
    }

    public void save(User user) {
        operationWithUser(user, SessionOperations::sessionOperationSave);
    }

    public void update(User user) {
        operationWithUser(user, SessionOperations::sessionOperationUpdate);
    }

    public void delete(User user) {
        operationWithUser(user, SessionOperations::sessionOperationDelete);
    }

    public Auto findAutoById(int id) {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Auto auto = null;
        try {
            Transaction transaction = session.beginTransaction();
            auto = session.get(Auto.class, id);
            transaction.commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return auto;
        }
    }

    public List<User> findAll() {
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        List<User> users = null;
        try {
            Transaction transaction = session.beginTransaction();
            users = ((List<User>) session.createQuery("from users").list());
            transaction.commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return users;
        }
    }

    private void operationWithUser(User user, Operation operation){
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            operation.doOperation(session, user);
            transaction.commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
        }
    }
}
