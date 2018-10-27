package com.excalibur.myBlog.repository;

import com.excalibur.myBlog.dao.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByName(String name);

    List<User> findBySurname(String surname);
}
