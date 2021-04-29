package com.matrix.freshmarket.controller.Filter;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductPriceController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "filter/{min}/{max}", method = RequestMethod.GET,
            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = {"Accept=application/json"})
    public ResponseEntity<List<ProductEntity>> search(
            @PathVariable("min") String min, @PathVariable("max") String max
    ) {

        if (min == "$0" || max == "$100") {
             return new ResponseEntity<List<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }
            try {
                List<ProductEntity> products = productService.getProducts(min, max);
                return new ResponseEntity<List<ProductEntity>>(products, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<List<ProductEntity>>(HttpStatus.BAD_REQUEST);
            }
        }








}
