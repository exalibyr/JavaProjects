package com.excalibur.myBlog.form;

import javax.validation.constraints.Size;

public class ProfileUpdatingForm {

    @Size(max = 20, min = 1)
    private String name;

    @Size(max = 30, min = 1)
    private String surname;

    @Size(max = 100)
    private String about;

    private String avatarUrl;

    public ProfileUpdatingForm() {
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
