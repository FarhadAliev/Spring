package com.matrix.freshmarket.controller;

import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.UserRepository;
import com.matrix.freshmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


@Controller
public class PasswordController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UserRepository repository;

    @Autowired
    UserService userService;

    @GetMapping("/CreatePassword")
    public String createPassword() {
        return "CreateNewPassword";
    }

    @GetMapping("/CreatePasswordWithEmailLink")
    public String createPasswordVerifyCode() {
        return "CreatePasswordWithEmailLink";
    }



    @RequestMapping(value = "/authToEmail",method = RequestMethod.POST)
    public String createPasswordPage(HttpServletRequest request, Model model) throws MessagingException {

        String email=request.getParameter("email");

        User existingUser = repository.findByEmail(email);

        if (existingUser != null) {



            MimeMessage message=mailSender.createMimeMessage();
            MimeMessageHelper help=new MimeMessageHelper(message,true);

            help.setFrom("freshmarket.message@gmail.com");
            help.setTo(email);

            String mailSubject="You can reset password by this verify code";
            String mailContent= "  <div  style=\"display : block; width: 90%;\">\n" +
                    "  <div>\n" +
                    "       <p style=\"font-size: 35px; text-align: center; color: forestgreen;\"><b>Fresh Market</b></p><br>\n" +
                    "       <p style=\"text-align: center; color:black; font-size: 17px;\">Hello ! We've received a request to reset the password for Fresh Market account ." +
                    " No changes have been made  to your  account yet . You can reset your password  by clicking the link below :\n" +
                    "    </p>\n" +
                    "      \n" +
                    "        <br>\n" +
                    "       \n" +
                    "          <div style=\"margin-left: 38%;\">\n" +
                    "              <a href=\"http://localhost:8089/CreatePasswordWithEmailLink/?email="+email+"\">\n" +
                    "            <button class=\"btn-dark\"  style=\" border: none; background-color: black; color: white; padding-top: 3%; padding-bottom: 3%; padding-left: 6%; padding-right: 6%;\" >Reset your password</button>\n" +
                    "        </a>\n" +
                    "        </div><br>\n" +
                    "       <p style=\"text-align: center; color:black; font-size: 17px;\"> If you did not request a new password " +
                    " immediately by replying to this email. \n" +
                    "    </p>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <br>\n" +
                    "    \n" +
                    "    </div>";

            help.setSubject(mailSubject);
            help.setText(mailContent,true);


            mailSender.send(message);
            model.addAttribute("successful", "We have sent a reset password link to your email . Please check . ");
            return "CreateNewPassword";
        }

        model.addAttribute("message", "Email is not  found");

        return "CreateNewPassword";


    }

    @RequestMapping("/CreatePasswordWithEmailLink/")
    public String udpdatePassword(Model model,@RequestParam(value = "email",defaultValue = "email") String email) {

        model.addAttribute("email",email);


     return "/CreatePasswordWithEmailLink";

    }



    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user=repository.findByEmail(email);
           userService.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");


        return "/CreatePasswordWithEmailLink";
    }


}
