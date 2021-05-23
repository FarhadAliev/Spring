package com.matrix.freshmarket.controller.AdminPage;


import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.entity.User;
import com.matrix.freshmarket.repository.ProductRepository;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;


@Controller
@RequestMapping
public class AdminPageController {

    private ProductService productService;

    @Autowired
    public AdminPageController(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    ProductRepository productRepository;




    @RequestMapping (value = "/admin/products", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = {"Accept=application/json"})
    public String productPageAdmin(@RequestParam(name = "direction", defaultValue = "DESC") String direction,
                           @RequestParam(name = "property", defaultValue = "all") String property,
                           @RequestParam(name = "sort", defaultValue = "sort") String sort,
                           @RequestParam(name = "min", defaultValue = "$0") String min,
                           @RequestParam(name = "max", defaultValue = "$100") String max,
                                   @RequestParam(name = "message", defaultValue = "") String message,
                           @RequestParam(value = "page",
                                   required = false,
                                   defaultValue = "0")
                                   Integer page,
                           Model model) {


        if(!min.equals("$0") || !max.equals("$100")){

            Page<ProductEntity> productPage =
                    productService.getProductPrice(page, direction, property,min,max,sort);
            if (!message.equals("")) {
                model.addAttribute("success", message);
            }
            model.addAttribute("min",min);
            model.addAttribute("max",max);
            model.addAttribute("sort", sort);
            model.addAttribute("property", property);
            model.addAttribute("products", productPage);
            model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        }else if(!sort.equals("sort")){
            Page<ProductEntity> productPage =
                    productService.getProductSortDropDown(page, direction, property,min,max,sort);
            if (!message.equals("")) {
                model.addAttribute("success", message);
            }
            model.addAttribute("min",min);
            model.addAttribute("max",max);
            model.addAttribute("sort", sort);
            model.addAttribute("property", property);
            model.addAttribute("products", productPage);
            model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        } else {

            Page<ProductEntity> productPage =
                    productService.getProduct(page, direction, property,sort);
            if (!message.equals("")) {
                model.addAttribute("success", message);
            }
            model.addAttribute("min",min);
            model.addAttribute("max",max);
            model.addAttribute("sort", sort);
            model.addAttribute("property", property);
            model.addAttribute("products", productPage);
            model.addAttribute("numbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        }
        return "AdminPage.html";
    }





    @GetMapping("/addNewProduct")
    public String redirectToNewProductPage() {
        return "AddProduct";
    }



    @PostMapping(value = "/addNewProduct")
    public String createProduct(HttpServletRequest request , Model model){



        String productName=request.getParameter("Name");
        String category=request.getParameter("Category");
        String price=request.getParameter("Price");
        String special=request.getParameter("Discount");
        String img=request.getParameter("Image");
        String productInfo=request.getParameter("Product Information");
        String productIngredients=request.getParameter("Product Ingredients");

        BigDecimal productPrice = new BigDecimal(price);
        BigDecimal specialPrice;
        if (special.equals("")){
            specialPrice=null;
        }else {
          specialPrice = new BigDecimal(special);
        }

        ProductEntity product=new ProductEntity(productName,productPrice,specialPrice,category,img,productInfo,productIngredients);

        model.addAttribute("success","New product successfully added to Database");
        productRepository.save(product);


        return "AddProduct";
    }

    @GetMapping("/editProduct/{productId}")
    public String redirectToEditProductPage(
            @RequestParam(name = "message", defaultValue = "") String message,
            @PathVariable Long productId,
            Model model
    ) {
        ProductEntity product = productService.getProduct(productId);
        model.addAttribute("product", product);
        if (!message.equals("")) {
            model.addAttribute("success", message);
        }
        return "EditProduct";
    }

    @RequestMapping("/saveProduct")
    public String saveProduct(HttpServletRequest request) {
        String id=request.getParameter("id");
        String productName=request.getParameter("Name");
        String category=request.getParameter("Category");
        String price=request.getParameter("Price");
        String specialPrice=request.getParameter("Discount");
        String img=request.getParameter("Image");
        String productInformation=request.getParameter("Product Information");
        String ingredients=request.getParameter("Product Ingredients");

      productService.findProductAndSave(productName,
              category,price,specialPrice,img,
              productInformation,ingredients);
        long num = Long.parseLong(id);
        String message="The data was changed and written to the database";
        return "redirect:/editProduct/"+num+"/?message="+message;
    }

    @RequestMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable Long productId,Model model) {
        productService.deleteProduct(productId);
        String message="Product changes deleted from Database";
        return "redirect:/admin/products?message="+message;
    }

}
