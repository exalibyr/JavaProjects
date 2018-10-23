package dao;

import models.Auto;
import models.User;

import java.util.List;

public interface UserDAO {

    User findById(int id);

    void save(User  user);

    void update(User user);

    void delete(User user);

    Auto findAutoById(int id);

//    List<User> findAll();


}
