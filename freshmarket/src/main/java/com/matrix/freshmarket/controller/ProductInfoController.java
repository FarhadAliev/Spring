package com.matrix.freshmarket.controller;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductInfoController {

    private ProductService productService;

    @Autowired
    public ProductInfoController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/product",method = RequestMethod.GET)
        public String productInfo(@RequestParam(name = "productName") String productName, Model model){


        List<ProductEntity> product=productService.findbyName(productName);
        model.addAttribute("product",product);
        model.addAttribute("productName",productName);

        return "ProductsInfo.html";
    }
}
