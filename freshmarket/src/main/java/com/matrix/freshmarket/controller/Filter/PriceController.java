package com.matrix.freshmarket.controller.Filter;

import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/filter/")
public class ProductPriceController {

    @Autowired
    private ProductService productService;


//    @RequestMapping(value = "filter/{min}/{max}", method = RequestMethod.GET,
//            produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
//            headers = {"Accept=application/json"})
    @GetMapping("{productType}/{min}/{max}")
    public ResponseEntity<Page<ProductEntity>> search(
            @PathVariable("min") String min, @PathVariable("max") String max,
            @PathVariable(value = "productType") String productType,
            @RequestParam(value = "page",
                    required = false,
                    defaultValue = "0")
                    Integer page,
            @RequestParam(name = "property", defaultValue = "all") String property
    ) {
        try {
            Page<ProductEntity> products = productService.getProducts(page, productType, min, max);
            return new ResponseEntity<Page<ProductEntity>>(products, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Page<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }
    }


}
