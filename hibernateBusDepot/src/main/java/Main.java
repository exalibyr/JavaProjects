import dao.Factory;
import models.Bus;
import models.Driver;
import models.Route;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {

        inizData();

        Collection routes = Factory.getInstance().getRouteDAO().getAllRoutes();
        Iterator iterator = routes.iterator();
        System.out.println("========Все маршруты=========");
        while (iterator.hasNext()) {
            Route route = (Route) iterator.next();
            System.out.println("Маршрут : " + route.getName() + "  Номер маршрута : " + route.getNumber());
            Collection busses = Factory.getInstance().getBusDAO().getBusesByRoute(route);
            Iterator iterator2 = busses.iterator();
            while (iterator2.hasNext()) {
                Bus bus = (Bus) iterator2.next();
                System.out.println("Автобус № " + bus.getNumber());

            }
        }

        Collection busses = Factory.getInstance().getBusDAO().getAllBuses();
        iterator = busses.iterator();
        System.out.println("========Все автобусы=========");
        while (iterator.hasNext()) {
            Bus bus = (Bus) iterator.next();
            Collection drivers = Factory.getInstance().getDriverDAO().getDriversByBus(bus);
            Iterator iterator2 = drivers.iterator();
            System.out.println("Автобус № " + bus.getNumber());
            while (iterator2.hasNext()) {
                Driver driver = (Driver) iterator2.next();
                System.out.println("Имя : " + driver.getName() + "   Фамилия: " + driver.getSurname());

            }
        }

    }

    private static void inizData(){
        Route route = new Route();
        route.setName("Тушинская");
        route.setNumber("A");
        Route route1 = new Route();
        route1.setName("Сокол");
        route1.setNumber("B");
        Route route2 = new Route();
        route2.setName("Полежаевская");
        route2.setNumber("C");

        Bus bus = new Bus();
        bus.setNumber("155");
        Bus bus1 = new Bus();
        bus1.setNumber("86");
        Bus bus2 = new Bus();
        bus2.setNumber("691");
        Bus bus3 = new Bus();
        bus3.setNumber("T");
        Bus bus4 = new Bus();
        bus4.setNumber("397");

        Driver driver = new Driver();
        driver.setName("John");
        driver.setSurname("Depp");
        driver.setAge(35);
        Driver driver1 = new Driver();
        driver1.setName("Michael");
        driver1.setSurname("Spanser");
        driver1.setAge(27);
        Driver driver2 = new Driver();
        driver2.setName("Paul");
        driver2.setSurname("Smith");
        driver2.setAge(42);

        HashSet<Bus> buses = new HashSet<>();
        buses.add(bus3);
        route.setBuses(buses);

        HashSet<Bus> buses1 = new HashSet<>();
        buses1.add(bus2);
        buses1.add(bus4);
        route1.setBuses(buses1);

        HashSet<Bus> buses2 = new HashSet<>();
        buses2.add(bus);
        buses2.add(bus1);
        route2.setBuses(buses2);

        HashSet<Driver> drivers = new HashSet<>();
        drivers.add(driver);
        drivers.add(driver1);
        bus.setDrivers(drivers);

        HashSet<Driver> drivers1 = new HashSet<>();
        drivers1.add(driver2);
        bus1.setDrivers(drivers1);

        HashSet<Driver> drivers2 = new HashSet<>();
        drivers2.add(driver);
        drivers2.add(driver2);
        bus2.setDrivers(drivers2);

        HashSet<Driver> drivers3 = new HashSet<>();
        drivers3.add(driver2);
        drivers3.add(driver1);
        bus3.setDrivers(drivers3);

        HashSet<Driver> drivers4 = new HashSet<>(drivers3);
        drivers4.add(driver);
        bus4.setDrivers(drivers4);

        HashSet<Bus> buses3 = new HashSet<>();
        buses3.add(bus);
        buses3.add(bus2);
        buses3.add(bus4);
        driver.setBuses(buses3);

        HashSet<Bus> buses4 = new HashSet<>();
        buses4.add(bus);
        buses4.add(bus3);
        driver1.setBuses(buses4);

        HashSet<Bus> buses5 = new HashSet<>();
        buses5.add(bus1);
        buses5.add(bus2);
        buses5.add(bus3);
        driver2.setBuses(buses5);

        Factory.getRouteDAO().addRoute(route);
        Factory.getRouteDAO().addRoute(route1);
        Factory.getRouteDAO().addRoute(route2);

        Factory.getBusDAO().addBus(bus);
        Factory.getBusDAO().addBus(bus1);
        Factory.getBusDAO().addBus(bus2);
        Factory.getBusDAO().addBus(bus3);
        Factory.getBusDAO().addBus(bus4);

        Factory.getDriverDAO().addDriver(driver);
        Factory.getDriverDAO().addDriver(driver1);
        Factory.getDriverDAO().addDriver(driver2);
    }
}
