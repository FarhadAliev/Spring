package com.matrix.freshmarket.controller.Filter;
import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.stream.IntStream;

@Controller
public class ShopProductController {


    private ProductService productService;

    @Autowired
    public ShopProductController(ProductService productService) {
        this.productService = productService;
    }



    @RequestMapping (value = "/shop", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = {"Accept=application/json"})
    public String shopPage(@RequestParam(name = "direction", defaultValue = "DESC") String direction,
                           @RequestParam(name = "property", defaultValue = "all") String property,
                           @RequestParam(name = "sort",defaultValue = "Sort by") String sort,
                           @RequestParam(value = "page",
                                   required = false,
                                   defaultValue = "0")
                                   Integer page,

                           Model model) {

        System.out.println(sort);
        Page<ProductEntity> productPage =
                productService.getProduct(page, direction, property,sort);

        model.addAttribute("property", property);
        model.addAttribute("products", productPage);
        model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());

        return "FreshMarketShop-1";
    }





}
