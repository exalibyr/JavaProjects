package dao;

import models.Bus;
import models.Driver;
import models.Route;

import java.util.Collection;

public interface RouteDAO {

    void updateRoute(Route route);
    void deleteRoute(Route route);
    void addRoute(Route route);
    Route getRouteById(Long id);
    Collection getAllRoutes();
    Route getRouteByBus(Bus bus);
    Collection getRoutesByDriver(Driver driver);

}
