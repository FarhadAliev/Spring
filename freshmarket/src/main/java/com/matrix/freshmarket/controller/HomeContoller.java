package com.matrix.freshmarket.controller;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.global.GlobalData;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/")
public class HomeContoller {

        private ProductService productService;

        @Autowired
        public HomeContoller(ProductService productService) {
            this.productService = productService;
        }

    @GetMapping("/")
    public String homePage(Model model ,@RequestParam(value = "count",defaultValue = "0") int count){
        model.addAttribute("title", "Home");

        List<ProductEntity> productsFirst =
                productService.findtop8();
        model.addAttribute("firstLine",productsFirst);
        System.out.println(GlobalData.cart.size());
        model.addAttribute("count",GlobalData.cart.size());

        return "FreshMarket";
    }






    @GetMapping("/about")
    public String aboutPage( Model model) {
        model.addAttribute("title", "About");
        model.addAttribute("count",GlobalData.cart.size());
        return "About";
    }



    @GetMapping("/faq")
    public String faqPage( Model model) {
        model.addAttribute("title", "Faq");
        model.addAttribute("count",GlobalData.cart.size());
        return "Faq";
    }




    @GetMapping("/policy")
    public String policyPage( Model model) {
        model.addAttribute("title", "Policy");
        model.addAttribute("count",GlobalData.cart.size());
        return "Policy";
    }


    @GetMapping("/shipping")
    public String shippingPage( Model model) {
        model.addAttribute("title", "Shipping");
        model.addAttribute("count",GlobalData.cart.size());
        return "Shipping";
    }








    @GetMapping("/viewCart")
    public String viewCartPage( Model model) {
        model.addAttribute("title", "View Cart");
        model.addAttribute("count",GlobalData.cart.size());
        return "ViewCart";
    }



}
