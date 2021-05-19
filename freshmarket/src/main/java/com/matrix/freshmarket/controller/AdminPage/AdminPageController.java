package com.matrix.freshmarket.controller.AdminPage;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping
public class AdminPageController {

    private ProductService productService;

    @Autowired
    public AdminPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/admin/products")
    public String findAll(Model model) {
        List<ProductEntity> products = productService.findAll();
        model.addAttribute("products", products);

        return "AdminPg";
    }

    @GetMapping("/addNewProduct")
    public String redirectToNewProductPage() {
        return "AddProduct";
    }

    @GetMapping("/editProduct/{productId}")
    public String redirectToEditProductPage(
            @PathVariable Long productId,
            Model model
    ) {
        ProductEntity product = productService.getProduct(productId);
        model.addAttribute("product", product);

        return "EditProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(ProductEntity productEntity) {
        productService.saveProduct(productEntity);

        return "redirect:/admin/products/";
    }

    @GetMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);

        return "redirect:/admin/products/";
    }
}
