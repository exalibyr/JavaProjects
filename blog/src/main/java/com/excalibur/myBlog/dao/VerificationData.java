package com.excalibur.myBlog.dao;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "user_validation_data", schema = "public")
public class VerificationData {

    @Id
    @Column(name = "user_login", nullable = false, unique = true)
    private String login;

    @Column(name = "user_password", nullable = false)
    private String password;

    @OneToOne(optional = false)
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true, nullable = false, updatable = false)
    private User user;

    public VerificationData() {
    }

    public VerificationData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
