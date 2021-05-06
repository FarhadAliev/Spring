package com.matrix.freshmarket.controller;


import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ContactController {

    @Autowired
    private ProductService productService;

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/contact")
    public String contact( Model model) {
        model.addAttribute("title", "Contact");
        return "Contact.html";
    }


    @PostMapping("/contact")
    public String submitContact(HttpServletRequest request) throws MessagingException {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        String comment=request.getParameter("comment");


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setFrom("farhad.aliev2020@gmail.com");
        helper.setTo("farhad771771@gmail.com");

        String mailSubject=firstName+"\n"+lastName +"\n"+"  Has sent a message";
        String mailContent= "<p><b>Sender Name : </b>"+firstName+"</p>"+ "\n";
        mailContent += "<p><b>Sender Last Name : </b>"+lastName+"</p>"+ "\n";
        mailContent += "<p><b>Sender E-mail : </b> "+email +"</p>"+  "\n";
        mailContent += "<p><b>Content :  </b>" +comment + "</p>"+ "\n";
        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);

        mailSender.send(message);




        return "Contact.html";
    }



}
