package dao;

import models.Bus;
import models.Driver;
import models.Route;

import java.util.Collection;

public interface BusDAO {

    void updateBus(Bus bus);
    void deleteBus(Bus bus);
    void addBus(Bus bus);
    Bus getBusById(Long id);
    Collection getAllBuses();
    Collection getBusesByDriver(Driver driver);
    Collection getBusesByRoute(Route route);

}
