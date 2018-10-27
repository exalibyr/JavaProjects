package com.excalibur.myBlog.controller.service;

import com.excalibur.myBlog.dao.User;
import com.excalibur.myBlog.dao.VerificationData;
import com.excalibur.myBlog.repository.VerificationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.excalibur.myBlog.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerNewUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public List<User> findUsersByName(String name){
        return userRepository.findByName(name);
    }

    public List<User> findUsersBySurname(String surname){
        return userRepository.findBySurname(surname);
    }

    public Optional<User> findUserById(Integer userId){
        return userRepository.findById(userId);
    }



}
