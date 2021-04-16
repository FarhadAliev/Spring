package com.matrix.freshmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class TitleController {


    @GetMapping("/")
    public String homePage( Model model) {
        model.addAttribute("title", "Home");
        return "FreshMarket";
    }

    @GetMapping("/about")
    public String aboutPage( Model model) {
        model.addAttribute("title", "About");
        return "About";
    }


    @GetMapping("/contact")
    public String contactPage( Model model) {
        model.addAttribute("title", "Contact");
        return "Contact";
    }

    @GetMapping("/password")
    public String createPasswordPage( Model model) {
        model.addAttribute("title", "Contact");
        return "CreateNewPassword";
    }

    @GetMapping("/faq")
    public String faqPage( Model model) {
        model.addAttribute("title", "Faq");
        return "Faq";
    }

    @GetMapping("/shop_1")
    public String shopPage_1( Model model) {
        model.addAttribute("title", "Shop");
        return "FreshMarketShop-1";
    }

    @GetMapping("/shop_2")
    public String shopPage_2( Model model) {
        model.addAttribute("title", "Shop");
        return "FreshMarketShop-2";
    }

    @GetMapping("/login")
    public String loginPage( Model model) {
        model.addAttribute("title", "Login");
        return "LogIn";
    }

    @GetMapping("/loginWithEmail")
    public String loginWithEmailPage( Model model) {
        model.addAttribute("title", "Login With Email");
        return "LogInWithEmail";
    }

    @GetMapping("/policy")
    public String policyPage( Model model) {
        model.addAttribute("title", "Policy");
        return "Policy";
    }

    @GetMapping("/productInfo")
    public String productInfoPage( Model model) {
        model.addAttribute("title", "Product Info");
        return "ProductsInfo";
    }

    @GetMapping("/shipping")
    public String shippingPage( Model model) {
        model.addAttribute("title", "Shipping");
        return "Shipping";
    }

    @GetMapping("/signUp")
    public String signUpPage( Model model) {
        model.addAttribute("title", "Sign Up");
        return "SignUp";
    }

    @GetMapping("/signUpWithEmail")
    public String signUpWithEmailPage( Model model) {
        model.addAttribute("title", "Sign Up With Email");
        return "SignUpWithEmail";
    }

    @GetMapping("/viewCart")
    public String viewCartPage( Model model) {
        model.addAttribute("title", "View Cart");
        return "ViewCart";
    }
}
