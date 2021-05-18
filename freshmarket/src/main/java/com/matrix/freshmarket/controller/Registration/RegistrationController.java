package com.matrix.freshmarket.controller.Registration;

import com.matrix.freshmarket.dao.RegistrationDao;
import com.matrix.freshmarket.entity.ConfirmationToken;
import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.ConfirmationTokenRepository;
import com.matrix.freshmarket.repository.UserRepository;
import com.matrix.freshmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;


@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;


    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;



    @GetMapping("/signUpWithEmail")
    public String signUpWithEmailPage( Model model) {
        model.addAttribute("title", "Sign Up With Email");
        model.addAttribute("user",new RegistrationDao());
        return "SignUpWithEmail";
    }





    @PostMapping(value = "/signUpWithEmail")
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

       userService.saveUserRegister(registrationDao);
        model.addAttribute("success", "Verification email has sent to   "+registrationDao.getEmail()+"!");

        return  "signUpWithEmail";

    }








    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = repository.findByEmail(token.getUser().getEmail());
            user.setEnabled(true);
            repository.save(user);
            model.addAttribute("success","Congratulations ! Your account has been activated and email is verified.");
        }
        else
        {
            model.addAttribute("error","The link is invalid or broken!");

        }

        return "RegisterMessage";
    }














}
