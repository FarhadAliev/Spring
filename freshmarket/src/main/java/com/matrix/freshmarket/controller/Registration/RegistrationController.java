package com.matrix.freshmarket.controller.Registration;

import com.matrix.freshmarket.dao.RegistrationDao;
import com.matrix.freshmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
                           HttpServletRequest request, Model model){

        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String matchingPassword=request.getParameter("matchingPassword");
        System.out.println(email);
        System.out.println(password);
        System.out.println(matchingPassword);
        System.out.println( LocalDate.now());


        if (result.hasErrors()) {
            return "signUpWithEmail";
        }

       userService.saveUserRegister(registrationDao);


        return  "signUpWithEmail";
    }


}
