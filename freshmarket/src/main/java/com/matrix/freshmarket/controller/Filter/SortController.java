package com.matrix.freshmarket.controller.Filter;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sort")
public class SortController {


    @Autowired
    private ProductService productService;


    @GetMapping("/{productType}/{min}/{max}/{sort}")
    public ResponseEntity<Page<ProductEntity>> searchAscDesc(
            @PathVariable("min") String min, @PathVariable("max") String max,
            @PathVariable(value = "productType") String productType,
            @PathVariable(value = "sort") String sort,
            @RequestParam(value = "page",
                    required = false,
                    defaultValue = "0")
                    Integer page, Model model) {

        Page<ProductEntity> products;

        try {
            products = productService.getProductSort(page, productType, min, max,sort);
            return new ResponseEntity<Page<ProductEntity>>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Page<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }


    }





//    @GetMapping("{sort}")
//    public ResponseEntity<Page<ProductEntity>> search(
//            @PathVariable("sort") String sort) {
//        Page<ProductEntity> products;
//


//        try {
//            return new ResponseEntity<Page<ProductEntity>>(products, HttpStatus.OK);
//        } catch (Exception e) {
//           return new ResponseEntity<Page<ProductEntity>>(HttpStatus.BAD_REQUEST);
//        }


//    }










}
