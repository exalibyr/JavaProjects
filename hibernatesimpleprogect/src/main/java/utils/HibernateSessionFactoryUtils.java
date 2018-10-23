package utils;

import models.Auto;
import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.swing.*;


public class HibernateSessionFactoryUtils {

    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtils(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            try{
                Configuration configuration = new Configuration();
                configuration
                        .configure()
                        .addAnnotatedClass(User.class)
                        .addAnnotatedClass(Auto.class);
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
                registryBuilder.applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                throw new ExceptionInInitializerError(e);
            }
        }
        return sessionFactory;
    }


}
