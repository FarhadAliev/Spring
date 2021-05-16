package com.matrix.freshmarket.service;


import com.matrix.freshmarket.dao.RegistrationDao;
import com.matrix.freshmarket.entity.Role;
import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.RoleRepository;
import com.matrix.freshmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService   {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


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


    public void saveUserRegister(RegistrationDao registrationDao) {

        User newUser=new User();
             newUser.setEmail(registrationDao.getEmail());
             newUser.setRegTime(LocalDate.now());
             newUser.setPassword(registrationDao.getPassword());
             newUser.setRoles(Arrays.asList(new Role("ROLE_USER")));


             userRepository.saveAndFlush(newUser);
    }






}
