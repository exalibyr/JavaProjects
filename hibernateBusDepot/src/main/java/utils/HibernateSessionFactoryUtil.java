package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;

public class HibernateSessionFactoryUtil {

    private static final SessionFactory sessionFactory;

    static {

        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Configuration error", JOptionPane.ERROR_MESSAGE);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
