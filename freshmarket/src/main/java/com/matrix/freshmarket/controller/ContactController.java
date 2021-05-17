package com.matrix.freshmarket.controller;


import com.matrix.freshmarket.entity.ContactEntity;
import com.matrix.freshmarket.repository.ContactRepository;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class ContactController {

    @Autowired
    private ProductService productService;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/contact")
    public String contact( Model model) {
        model.addAttribute("title", "Contact");
        model.addAttribute("contact",new ContactEntity());
        return "Contact.html";
    }



    @RequestMapping(value = "/contact",method = RequestMethod.POST)
    public Object submitContact(@Valid @ModelAttribute("contact") ContactEntity contactEntity, BindingResult result,
                                HttpServletRequest request, Model model) throws MessagingException {
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        String comment=request.getParameter("comment");

        if (result.hasErrors()) {
            return "Contact.html";
        }



        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setFrom("freshmarket.message@gmail.com");
        helper.setTo("farhad771771@gmail.com");

        String mailSubject=firstName+"\n"+lastName +"\n"+"  Has sent a message";
        String mailContent= "<p><b>Sender Name : </b>"+firstName+"</p>"+ "\n";
        mailContent += "<p><b>Sender Last Name : </b>"+lastName+"</p>"+ "\n";
        mailContent += "<p><b>Sender E-mail : </b> "+email +"</p>"+  "\n";
        mailContent += "<p><b>Content :  </b>" +comment + "</p>"+ "\n";
        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);

        mailSender.send(message);
       model.addAttribute("success", "Thank you for contacting us . We'll get back to you shortly !");


        ContactEntity contact=new ContactEntity(firstName,lastName,email);
        contactRepository.saveAndFlush(contact);
        return "Contact.html";

    }



}
