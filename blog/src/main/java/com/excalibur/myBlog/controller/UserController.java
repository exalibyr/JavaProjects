package com.excalibur.myBlog.controller;

import com.excalibur.myBlog.controller.service.PublicationService;
import com.excalibur.myBlog.controller.service.UserService;
import com.excalibur.myBlog.controller.service.VerificationDataService;
import com.excalibur.myBlog.dao.Publication;
import com.excalibur.myBlog.dao.User;
import com.excalibur.myBlog.dao.VerificationData;
import com.excalibur.myBlog.form.PublicationForm;
import com.excalibur.myBlog.form.RegistrationForm;
import com.excalibur.myBlog.form.VerificationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController{



    @Autowired
    private UserService userService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private VerificationDataService verificationDataService;

    @GetMapping(value = "/sign-in")
    public String showSingInForm(VerificationForm verificationForm){
        return "sign-in";
    }

    @PostMapping(value = "/sign-in")
    public String verifyUser(@Valid VerificationForm verificationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign-in";
        }
        else{
            Optional<VerificationData> verificationDataOptional =
                    verificationDataService.verifyUserLogin(verificationForm.getUserLogin());
            if(verificationDataOptional.isPresent()){
                VerificationData verificationData = verificationDataOptional.get();
                if(verificationData.getPassword().equals(verificationForm.getUserPassword())){
                    return "redirect:/user/id=" + verificationData.getUser().getId();
                }
                else {
                    return "sign-in";
                }
            }
            else {
                return "sign-in";
            }
//            VerificationData verificationData = verificationDataService.checkUser(verificationForm.getUserLogin(),
//                    verificationForm.getUserPassword());
//            if(verificationData == null){
//                return "sign-in";
//            }
//            else {
//                return "redirect:/main-page";
//            }
        }
    }

    @GetMapping(value = "/sign-up")
    public String showRegistrationForm(RegistrationForm registrationForm){
        return "sign-up";
    }

    @PostMapping(value = "/sign-up")
    public String tryToRegisterUser( @Valid RegistrationForm registrationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "sign-up";
        }
        else{
            User newUser = registrationForm.getUser();
            VerificationData userVerificationData = registrationForm.getValidationData();
            newUser.setVerificationData(userVerificationData);
            userVerificationData.setUser(newUser);
            userService.registerNewUser(newUser);
            return "redirect:/registration-success/id=" + newUser.getId();
        }
    }

    @GetMapping(value = "/registration-success/id={userId}")
    public String registrationSuccess( @PathVariable(name = "userId") Integer userId, Model model){
        Optional<User> userOptional = userService.findUserById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "registration-success";
        }
        else {
            return "error-page";
        }
    }


    @GetMapping(value = "/user/id={userId}")
    public String showHomePage(@PathVariable(name = "userId") Integer userId, Model model){
        Optional<User> userOptional = userService.findUserById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("user", user);
            List<Publication> publications = publicationService.findPublicationsByUser(user);
            model.addAttribute("publications", publications);
            return "home-page";
        }
        else {
            return "error-page";
        }
    }

    @GetMapping(value = "/user/id={userId}/createPublication")
    public String getPublicationForm( @PathVariable(name = "userId") Integer userId,
                                    PublicationForm publicationForm,
                                    Model model){
        model.addAttribute("userId", userId);
        return "createPublication";
    }

    @PostMapping(value = "/user/id={userId}/createPublication")
    public String createPublication( @PathVariable(name = "userId") Integer userId,
                                     @Valid PublicationForm publicationForm,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/user/id=" + userId + "/createPublication";
        }
        else {
            Optional<User> userOptional = userService.findUserById(userId);
            if(userOptional.isPresent()){
                //get user from db
                User user = userOptional.get();
                //create new publication instance to insert into db
                Publication newPublication = publicationForm.getPublication();
                newPublication.setDateTime(ZonedDateTime.now());
                newPublication.setUser(user);
                //add new publication to db
                publicationService.saveNewPublication(newPublication);
                return "redirect:/user/id=" + userId;
            }
            else {
                return "error-page";
            }
        }
    }

    @GetMapping(value = "/user/id={userId}/findUsers")
    public String getFindUsersForm( @PathVariable(name = "userId") Integer userId,
                                  Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("userInfo", new User());
        return "findUsers";
    }

    @PostMapping(value = "/user/id={userId}/findUsers")
    public String findUsers(@PathVariable(name = "userId") Integer userId,
                           @ModelAttribute(name = "userInfo") User userInfo)
    {
        return "redirect:/user/id=" + userId + "/showUsers"
                + userInfo.getName() + "," + userInfo.getSurname();
    }

    @GetMapping(value = "/user/id={userId}/showUsers{name},{surname}")
    public String showUsers( @PathVariable(name = "userId") Integer userId,
                             @PathVariable(name = "name") String name,
                             @PathVariable(name = "surname") String surname,
                             Model model){
        List<User> users = null;
        if(!name.isEmpty()){
            if(!surname.isEmpty()){
//                users = userService.findUsersByNameSurname(name, surname);
            }
            else {
                users = userService.findUsersByName(name);
            }
        }
        else {
            if(!surname.isEmpty()){
                users = userService.findUsersBySurname(surname);
            }
        }
        model.addAttribute("userId", userId);
        model.addAttribute("users", users);
        return "showUsers";
    }

    @GetMapping(value = "/user/id={userId}/showUserPage/id={id}")
    public String showUserPage( @PathVariable(name = "userId") Integer userId,
                                @PathVariable(name = "id") Integer id,
                                Model model){
        Optional<User> userOptional = userService.findUserById(id);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("userId", userId);
            model.addAttribute("user", user);
            List<Publication> publications = publicationService.findPublicationsByUser(user);
            model.addAttribute("publications", publications);
            return "showUserPage";
        }
        else {
            return "error-page";
        }
    }

}
