package services;

import models.Auto;
import models.User;

import java.util.List;

public interface UserService {

    User findUser(int id);

    Auto findAuto(int id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

//    List<User> findAllUsers();
}
