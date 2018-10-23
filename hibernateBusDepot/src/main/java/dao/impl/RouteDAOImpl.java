package dao.impl;

import dao.RouteDAO;
import dao.impl.templates.OperationFactory;
import dao.impl.templates.SessionOperation;
import models.Bus;
import models.Driver;
import models.Route;
import org.hibernate.Session;
import utils.HibernateSessionFactoryUtil;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class RouteDAOImpl implements RouteDAO {
    @Override
    public void updateRoute(Route route) {
        SessionOperation.makeSessionTransaction(route, OperationFactory::update);
    }

    @Override
    public void deleteRoute(Route route) {
        SessionOperation.makeSessionTransaction(route, OperationFactory::delete);
    }

    @Override
    public void addRoute(Route route) {
        SessionOperation.makeSessionTransaction(route, OperationFactory::add);
    }

    @Override
    public Route getRouteById(Long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Route route = null;
        try{
            session.beginTransaction();
            route = session.load(Route.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return route;
        }
    }

    @Override
    public Collection getAllRoutes() {
        List<Route> routes = null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            routes = ((List<Route>) session.createQuery("from Route ").list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return routes;
        }
    }

    @Override
    public Route getRouteByBus(Bus bus) {
        Route route = null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Long routeId = bus.getRouteId();
            route = session.load(Route.class, routeId);
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return route;
        }
    }

    @Override
    public Collection getRoutesByDriver(Driver driver) {
        List<Route> routes = null;
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Long driverId = driver.getId();
            routes = ((List<Route>) session.createQuery(
                    "select r " +
                            "from Route r, Bus b inner join b.drivers driver " +
                            "where driver.id = :driverId " +
                            "and r.id in b.routeId"
            ).setParameter("driverId", driverId).list());
            session.getTransaction().commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally {
            session.close();
            return routes;
        }
    }
}
