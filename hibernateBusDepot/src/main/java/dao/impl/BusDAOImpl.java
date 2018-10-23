package dao.impl;

import dao.BusDAO;
import dao.impl.templates.Operation;
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

public class BusDAOImpl implements BusDAO {

    @Override
    public void updateBus(Bus bus) {
        SessionOperation.makeSessionTransaction(bus, OperationFactory::update);
    }

    @Override
    public void deleteBus(Bus bus) {
        SessionOperation.makeSessionTransaction(bus, OperationFactory::delete);
    }

    @Override
    public void addBus(Bus bus) {
        SessionOperation.makeSessionTransaction(bus, OperationFactory::add);
    }

    @Override
    public Bus getBusById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Bus bus = null;
        try{
            session.beginTransaction();
            bus = session.load(Bus.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return bus;
        }
    }

    @Override
    public Collection getAllBuses() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bus> buses = null;
        try{
            session.beginTransaction();
            buses = (List<Bus>) session.createQuery("from Bus ").list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return buses;
        }
    }

    @Override
    public Collection getBusesByDriver(Driver driver) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bus> buses = null;
        try{
            session.beginTransaction();
            Long driverId = driver.getId();
            Query query = session.createQuery(
                    " select b "
                            + " from Bus b INNER JOIN b.drivers driver"
                            + " where driver.id = :driverId "
            )
                    .setParameter("driverId", driverId);
            buses = ((List<Bus>) query.list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return buses;
        }
    }

    @Override
    public Collection getBusesByRoute(Route route) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bus> buses = null;
        try{
            session.beginTransaction();
            Long routeId = route.getId();
            Query query = session.createQuery("from Bus where routeId = :routeIdParam")
                    .setParameter("routeIdParam", routeId);
            buses = ((List<Bus>) query.list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return buses;
        }
    }
}
