package com.excalibur.myBlog.repository;

import com.excalibur.myBlog.dao.VerificationData;
import org.springframework.data.repository.CrudRepository;

public interface VerificationDataRepository extends CrudRepository<VerificationData, String> {

//    VerificationData findByLoginPassword(String login, String password);

}
