package com.matrix.freshmarket.controller;

import com.matrix.freshmarket.entity.ContactEntity;
import com.matrix.freshmarket.repository.ContactRepository;
import org.cryptacular.io.ClassPathResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class SubscribeController {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private JavaMailSender mailSender;





    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object submitContact(HttpServletRequest request, Model model) throws MessagingException, IOException {
        String subscribe=request.getParameter("subscribe");


        MimeMessage message=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setFrom("freshmarket.message@gmail.com");
        helper.setTo(subscribe);





        String mailSubject="Fresh Market "+"  Has sent a message";

        String mailContent="<div>";
        mailContent +="<p style=\"font-size: 30px; text-align: center;color:black;\"><b>Welcome to Fresh Market family</b></p><br>"+ "\n";
        mailContent += "<p style=\"font-size: 20px;text-align:center;color:black;\">You've signed up to bethe first to know about our exclusive offers, new products, and recipes." +
                "You've signed up to bethe first to know about.</p>"+  "\n";
        mailContent +="</div>";
        mailContent +="<br><img src='cid:fre.png'>";
        helper.setSubject(mailSubject);
        helper.setText(mailContent,true);
        String path="C:/Users/Farhad/Desktop/Web-Spring/Matrix-Spring/freshmarket/src/main/resources/static/gallery/fre.png";
        FileSystemResource file = new FileSystemResource(new File(path));
        helper.addInline("fre.png", file);

        mailSender.send(message);


        return "redirect:http://localhost:8089" ;

    }
}
