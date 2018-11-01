package com.excalibur.myBlog.repository;

import com.excalibur.myBlog.dao.VerificationData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VerificationDataRepository extends CrudRepository<VerificationData, String> {

    Optional<VerificationData> findByLoginAndPassword(String login, String password);

}
