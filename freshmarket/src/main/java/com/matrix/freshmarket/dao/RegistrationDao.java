package com.matrix.freshmarket.dao;


import com.matrix.freshmarket.validation.ValidEmail;
import com.matrix.freshmarket.validation.ValidPassword;

import javax.validation.constraints.NotNull;

public class RegistrationDao {

    @ValidEmail
    @NotNull
    private  String email;
    @NotNull
    @ValidPassword
    private String password;
    @NotNull
    private  String matchingPassword;


    public RegistrationDao(String email, String password, String matchingPassword) {
        this.email = email;
        this.password = password;
        this.matchingPassword = matchingPassword;
    }

    public RegistrationDao() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}
