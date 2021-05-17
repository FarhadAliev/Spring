package com.matrix.freshmarket.controller.Registration;

import com.matrix.freshmarket.dao.RegistrationDao;
import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class RegistrationController {

    @Autowired
    UserService userService;



    @GetMapping("/signUpWithEmail")
    public String signUpWithEmailPage( Model model) {
        model.addAttribute("title", "Sign Up With Email");
        model.addAttribute("user",new RegistrationDao());
        return "SignUpWithEmail";
    }





    @RequestMapping(value = "/signUpWithEmail",method = RequestMethod.POST)
    public Object register(@Valid @ModelAttribute("user") RegistrationDao registrationDao, BindingResult result,
                           HttpServletRequest request, Model model) throws MessagingException {

        String password=registrationDao.getPassword();
        String passwordMatching=registrationDao.getMatchingPassword();


        User existing = userService.findByUsername(registrationDao.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if(!password.equals(passwordMatching)){
            result.rejectValue("matchingPassword", null, "Passwords don't match");
        }

        if (result.hasErrors()) {
            return "signUpWithEmail";
        }


       userService.sendRegistrationMessage(registrationDao);
       userService.saveUserRegister(registrationDao);
        model.addAttribute("success", "Thank you for your registration!");

        return  "signUpWithEmail";
    }


}
