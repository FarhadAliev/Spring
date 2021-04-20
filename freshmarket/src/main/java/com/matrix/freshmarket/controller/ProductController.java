package com.matrix.freshmarket.controller;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {


    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }



     @RequestMapping( value="/slider", method = RequestMethod.GET)
     public List<ProductEntity> slider(){
        List<ProductEntity> productSlider =
                productService.findNotNulltop4();


        return productSlider;
    }
}
