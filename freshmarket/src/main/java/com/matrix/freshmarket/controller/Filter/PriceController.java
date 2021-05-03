package com.matrix.freshmarket.controller.Filter;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/product/filter/")
public class PriceController {

    @Autowired
    private ProductService productService;


    @GetMapping("{productType}/{min}/{max}")
    public ResponseEntity<Page<ProductEntity>> search(
            @PathVariable("min") String min, @PathVariable("max") String max,
            @PathVariable(value = "productType") String productType,
            @RequestParam(value = "page",
                    required = false,
                    defaultValue = "0")
                    Integer page, Model model) {
        Page<ProductEntity> products;
        try {
             products = productService.getProducts(page, productType, min, max);
            model.addAttribute("products", products);
            model.addAttribute("numbers", IntStream.range(0, products.getTotalPages()).toArray());

            return new ResponseEntity<Page<ProductEntity>>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Page<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }


    }


}
