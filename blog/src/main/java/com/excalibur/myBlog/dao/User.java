package com.excalibur.myBlog.dao;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_data", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_id_seq")
    @SequenceGenerator(name = "user_data_id_seq", sequenceName = "user_data_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "about")
    private String about;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Publication> publications;

    @OneToOne(optional = false, mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationData verificationData;

    public User() {
    }

    public User(String name, String surname, String about) {
        this.name = name;
        this.surname = surname;
        this.about = about;
    }

    public void addPublication(Publication publication){
        if(publications == null){
            publications = new ArrayList<>();
        }
        publications.add(publication);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public VerificationData getVerificationData() {
        return verificationData;
    }

    public void setVerificationData(VerificationData verificationData) {
        this.verificationData = verificationData;
    }
}
