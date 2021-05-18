package com.matrix.freshmarket.service;


import com.matrix.freshmarket.dao.RegistrationDao;
import com.matrix.freshmarket.entity.ConfirmationToken;
import com.matrix.freshmarket.entity.Role;
import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.ConfirmationTokenRepository;
import com.matrix.freshmarket.repository.RoleRepository;
import com.matrix.freshmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService   {



    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String username){
        return  userRepository.findByEmail(username);
    }

    @Override
    @Transactional
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=findByUsername(username);
        if (username=="null"){
            throw new UsernameNotFoundException(String.format("User '%s' not found",username));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return  roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }


    public void updatePassword(User user,String password){
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
        String encodePassword=bc.encode(password);

        user.setPassword(encodePassword);
        userRepository.save(user);

    }


    public void saveUserRegister(RegistrationDao registrationDao) throws MessagingException {
        BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
        String encodePassword=bc.encode(registrationDao.getPassword());
        User newUser=new User();
             newUser.setEmail(registrationDao.getEmail());
             newUser.setRegTime(LocalDate.now());
             newUser.setPassword(encodePassword);
             newUser.setEnabled(false);
             newUser.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")  ));


             userRepository.saveAndFlush(newUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(newUser);
        confirmationTokenRepository.save(confirmationToken);


       MimeMessage message=mailSender.createMimeMessage();
       MimeMessageHelper help=new MimeMessageHelper(message,true);

       help.setFrom("freshmarket.message@gmail.com");
       help.setTo(registrationDao.getEmail());

       String mailSubject="Complete Registration !";
       String mailContent= "  <div  style=\"display : block; width: 90%;\">\n" +
               "  <div>\n" +
               "       <p style=\"font-size: 35px; text-align: center; color: forestgreen;\"><b>Fresh Market</b></p><br>\n" +
               "       <p style=\"text-align: center; color:black; font-size: 17px;\">Thank you for Registration and Welcome to Fresh Market family" +
               " You've signed up to bethe first to know about our exclusive offers, new products, and recipes.\n" +
               "                \"Confirm your account , please click button !\n" +
               "    </p>\n";
               mailContent+="<br><br><div style=\"margin-left: 40%;\">\n"+
                "<a href=\"http://localhost:8089/confirm-account?token=" + confirmationToken.getConfirmationToken()+"\">\n" +
                "            <button class=\"btn-dark\"  style=\" border: none; background-color: black; color: white; padding-top: 3%; padding-bottom: 3%; padding-left: 6%; padding-right: 6%;\" >Confirm account</button>\n" +
                "        </a>\n" +
                "        </div><br>\n" ;

                help.setSubject(mailSubject);
        help.setText(mailContent,true);


       mailSender.send(message);


   }

}
