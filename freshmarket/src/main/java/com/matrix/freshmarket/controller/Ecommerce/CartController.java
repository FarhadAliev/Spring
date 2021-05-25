package com.matrix.freshmarket.controller.Ecommerce;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.global.GlobalData;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    ProductService productService;


    @GetMapping("/addToCart/{productName}/{productCount}")
    public ResponseEntity<List<ProductEntity>> addToCart(@PathVariable String productName, @PathVariable String productCount, Model model){

        System.out.println(productCount);
        System.out.println(productName);

        ProductEntity product=productService.findByName(productName);
        GlobalData.cart.add(product);

        int cartSize=GlobalData.cart.size();
        List<ProductEntity> cart;



        try {
            model.addAttribute("img",product.getProductImg());
            cart=GlobalData.cart;
            return new ResponseEntity<List<ProductEntity>>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }



    }
//
//    @GetMapping("/cart")
//    public String cartGet(Model model){
//
//
//    return "redirect: / ";
//    }
}
