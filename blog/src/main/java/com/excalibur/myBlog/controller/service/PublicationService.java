package com.excalibur.myBlog.controller.service;

import com.excalibur.myBlog.dao.Publication;
import com.excalibur.myBlog.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.excalibur.myBlog.repository.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;

    public void saveNewPublication(Publication publication){
        publicationRepository.save(publication);
    }

    public void deletePublication(Publication publication){
        publicationRepository.delete(publication);
    }

    public List<Publication> findPublicationsByTitle(String title){
        return publicationRepository.findByTitle(title);
    }

    public List<Publication> findPublicationsByUser(User user){
        return publicationRepository.findByUser(user);
    }

    public Iterable<Publication> findAllPublications(){
        return publicationRepository.findAll();
    }

    public void deletePublicationById(Integer id){
        publicationRepository.deleteById(id);
    }
}
