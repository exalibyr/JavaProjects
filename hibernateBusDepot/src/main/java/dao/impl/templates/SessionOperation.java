package dao.impl.templates;

import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;

public class SessionOperation {

    public static void makeSessionTransaction(Object object, Operation operation){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            operation.doOperation(session, object);
            session.getTransaction().commit();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
        }
    }
}
