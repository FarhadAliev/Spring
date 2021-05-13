package com.matrix.freshmarket.controller.Login;

import com.matrix.freshmarket.entity.User;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class Login {


    @GetMapping("/loginWithEmail")
    public String loginWithEmailPage(Model model) {

        model.addAttribute("title", "Login With Email");
        return "LogInWithEmail";
    }
}
