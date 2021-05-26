package com.matrix.freshmarket.config;

import com.matrix.freshmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;



    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/","/contact**","/about",
                        "/faq","/policy","/shipping","/product**","/signUpWithEmail**","/loginWithEmail**").permitAll()
                .antMatchers("/admin/products","/addNewProduct","/editProduct/**","/shop/**","/cart/buy/**","/viewCart/**").hasRole("ADMIN")
                .antMatchers("/cart/viewCart/**","/cart/remove/**","/cart/update").hasRole("ADMIN")
                .antMatchers("/viewCart/**","/shop/**","/cart/**","/cart/viewCart/**","/cart/remove/**","/cart/update","/cart/buy/**").hasRole("USER")
                .and()
                .formLogin()
                .loginPage("/loginWithEmail").permitAll()
                .and()
                .logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;
    }




}