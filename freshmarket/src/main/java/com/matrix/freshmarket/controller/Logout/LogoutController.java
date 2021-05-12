package com.matrix.freshmarket.controller.Logout;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {


    @RequestMapping("/logout")
    public String logout(){
        return "/";
    }


}
