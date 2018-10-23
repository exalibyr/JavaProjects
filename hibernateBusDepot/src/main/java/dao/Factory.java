package dao;

import dao.impl.BusDAOImpl;
import dao.impl.DriverDAOImpl;
import dao.impl.RouteDAOImpl;

//singleton and factory patterns
public class Factory {

    private static BusDAO busDAO;
    private static DriverDAO driverDAO;
    private static RouteDAO routeDAO;
    private static Factory instance;

    static {
        busDAO = null;
        driverDAO = null;
        routeDAO = null;
        instance = null;
    }

    public static synchronized Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }

    public static synchronized BusDAO getBusDAO() {
        if(busDAO == null){
            busDAO = new BusDAOImpl();
        }
        return busDAO;
    }

    public static synchronized DriverDAO getDriverDAO() {
        if(driverDAO == null){
            driverDAO = new DriverDAOImpl();
        }
        return driverDAO;
    }

    public static synchronized RouteDAO getRouteDAO() {
        if(routeDAO == null){
            routeDAO = new RouteDAOImpl();
        }
        return routeDAO;
    }
}
