package com.matrix.freshmarket.controller.Ecommerce;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/addToCart")
public class AddToCartController {


    @Autowired
    private ProductService productService;

//    @GetMapping("/add")
//    public ResponseEntity<ProductEntity> addToCart(@RequestParam String val,
//                                                   @RequestParam String name,
//                                                   HttpServletResponse response){
//
//        System.out.println(val);
//        System.out.println(name);
//
//        Cookie cookie = new Cookie(val,name);
//        cookie.setMaxAge(24 * 60 * 60);
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
//        cookie.setPath("/");
//
//        response.addCookie(cookie);
//
//       ProductEntity products;
//       products = productService.findByName(name);
//
//        return new ResponseEntity<ProductEntity>(products, HttpStatus.OK);
//
//    }
}
