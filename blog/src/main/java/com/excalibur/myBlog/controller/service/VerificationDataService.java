package com.excalibur.myBlog.controller.service;

import com.excalibur.myBlog.dao.VerificationData;
import org.springframework.beans.factory.annotation.Autowired;
import com.excalibur.myBlog.repository.VerificationDataRepository;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.Optional;

@Service
public class VerificationDataService {

    @Autowired
    private VerificationDataRepository verificationDataRepository;

//    public VerificationData checkUser(String login, String password){
//        return verificationDataRepository.findByLoginPassword(login, password);
//    }

    public Optional<VerificationData> verifyUserLogin(String login){
        return verificationDataRepository.findById(login);
    }


}
