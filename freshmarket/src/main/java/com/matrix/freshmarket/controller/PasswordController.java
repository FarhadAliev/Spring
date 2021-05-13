package com.matrix.freshmarket.controller;

import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.UserRepository;
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

    @GetMapping("/CreatePassword")
    public String createPassword() {
        return "CreateNewPassword";
    }

    @GetMapping("/CreatePasswordWithVerifyCode")
    public String createPasswordVerifyCode() {
        return "CreatePasswordWithVerifyCode";
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
                    "       <p style=\"font-size: 30px; text-align: center; color: forestgreen;\"><b>Fresh Market</b></p><br>\n" +
                    "        <p style=\"text-align: center;\">Hello</p>\n" +
                    "       <p style=\"text-align: center;\">We've received a request to reset the password for Fresh Market account\n" +
                    "    with email .</p>\n" +
                    "    <p style=\"text-align: center;\">No changes have been made  to your  account yet .</p>\n" +
                    "        <br><p style=\"text-align: center;\">You can reset password by this verify code : </p>\n" +
                    "      \n" +
                    "        <br>\n" +
                    "       \n" +
                    "          <div style=\"margin-left: 42%;\">\n" +
                    "              <a href=\"http://localhost:8089/CreatePasswordWithVerifyCode/?email="+email+"\">\n" +
                    "            <button class=\"btn-dark\"  style=\" border: none; background-color: black; color: white; padding-top: 3%; padding-bottom: 3%; padding-left: 6%; padding-right: 6%;\" >Verify email</button>\n" +
                    "        </a>\n" +
                    "        </div>\n" +
                    "        </div>\n" +
                    "\n" +
                    "        <br>\n" +
                    "    \n" +
                    "    </div>";

            help.setSubject(mailSubject);
            help.setText(mailContent,true);


            mailSender.send(message);
            return "FreshMarket";
        }

        model.addAttribute("message", "Email is not  found");

        return "CreateNewPassword";


    }

    @RequestMapping("/CreatePasswordWithVerifyCode/")
    public String udpdatePassword(Model model,@RequestParam(value = "email",defaultValue = "email") String email) {
     model.addAttribute("email",email);


     return "/CreatePasswordWithVerifyCode";

    }




    //        User emailUser=repository.findByEmail(email);

//        String password=request.getParameter("password");
//        String confirmPassword=request.getParameter("confirmPassword");
//
//
//        System.out.println(email);
//        System.out.println(password);
}
