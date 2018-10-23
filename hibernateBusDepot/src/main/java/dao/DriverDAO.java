package dao;

import models.Bus;
import models.Driver;
import models.Route;

import java.util.Collection;

public interface DriverDAO {

    void updateDriver(Driver driver);
    void deleteDriver(Driver driver);
    void addDriver(Driver driver);
    Driver getDriverById(Long id);
    Collection getAllDrivers();
    Collection getDriversByRoute(Route route);
    Collection getDriversByBus(Bus bus);

}
