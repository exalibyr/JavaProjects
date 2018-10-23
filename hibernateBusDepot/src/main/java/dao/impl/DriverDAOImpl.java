package dao.impl;
import dao.DriverDAO;
import dao.impl.templates.OperationFactory;
import dao.impl.templates.SessionOperation;
import models.Bus;
import models.Driver;
import models.Route;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {


    @Override
    public void updateDriver(Driver driver) {
        SessionOperation.makeSessionTransaction(driver, OperationFactory::update);
    }

    @Override
    public void deleteDriver(Driver driver) {
        SessionOperation.makeSessionTransaction(driver, OperationFactory::delete);
    }

    @Override
    public void addDriver(Driver driver) {
        SessionOperation.makeSessionTransaction(driver, OperationFactory::add);
    }

    @Override
    public Driver getDriverById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Driver driver = null;
        try{
            session.beginTransaction();
            driver = session.load(Driver.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return driver;
        }
    }

    @Override
    public Collection getAllDrivers() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Driver> drivers = null;
        try{
            session.beginTransaction();
            drivers = (List<Driver>) session.createQuery("from Driver ").list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return drivers;
        }
    }

    @Override
    public Collection getDriversByRoute(Route route) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Driver> drivers = null;
        try{
            session.beginTransaction();
            Long routeId = route.getId();
            Query query = session.createQuery(
                    " select d "
                            + " from Bus b, Driver d INNER JOIN d.buses bus"
                            + " where bus.routeId in :routeId"
            )
                    .setParameter("routeId", routeId);
            drivers = ((List<Driver>) query.list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return drivers;
        }
    }

    @Override
    public Collection getDriversByBus(Bus bus) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Driver> drivers = null;
        try{
            session.beginTransaction();
            Long busId = bus.getId();
            Query query = session.createQuery(
                    " select d "
                    + " from Driver d INNER JOIN d.buses bus"
                    + " where bus.id = :busId "
            )
                    .setParameter("busId", busId);
            drivers = ((List<Driver>) query.list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return drivers;
        }
    }
}
