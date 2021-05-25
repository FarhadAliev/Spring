package com.matrix.freshmarket.controller.Ecommerce;


import com.matrix.freshmarket.Global.GlobalData;
import com.matrix.freshmarket.dao.Cart;
import com.matrix.freshmarket.entity.ProductEntity;
import com.matrix.freshmarket.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {


    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/viewCart", method = RequestMethod.GET)
    public String cart(ModelMap modelMap, HttpSession session) {
        modelMap.addAttribute("count", GlobalData.cart.size());
        modelMap.put("total", total(session));
        return "ViewCart.html";
    }

    @RequestMapping(value = "/buy/{id}", method = RequestMethod.GET)
    public String buy(@PathVariable("id") long id,
                      HttpSession session) {


        if (session.getAttribute("cart") == null) {
            List<Cart> cart = new ArrayList<Cart>();
            cart.add(new Cart(productService.getProduct(id), 1));
            session.setAttribute("cart", cart);
        } else {
            List<Cart> cart = (List<Cart>) session.getAttribute("cart");
            long index = isExist(id, cart);
            if (index == -1) {
                cart.add(new Cart(productService.getProduct(id), 1));
            } else {
                Long quantity = cart.get((int) index).getQuantity() + 1;
                cart.get((int) index).setQuantity(quantity);

            }
            session.setAttribute("cart", cart);
        }

        return "redirect:/shop";
    }


    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String remove(@PathVariable("id") long id,
                         HttpSession session) {
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
        int index = (int) isExist(id, cart);
        System.out.println(index);
        cart.remove(index);
        session.setAttribute("cart", cart);

        return "redirect:/cart/viewCart";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request,
                         HttpSession session) {
        String[] quantities = request.getParameterValues("quantity");
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
        for (int i = 0; i < cart.size(); i++) {
            cart.get(i).setQuantity(Integer.parseInt(quantities[i]));
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart/viewCart";
    }


    private long isExist(Long id, List<Cart> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getProductEntity().getId() == id) {
                return i;
            }

        }
        return -1;
    }

    private double total(HttpSession session) {
        List<Cart> cart = (List<Cart>) session.getAttribute("cart");
        double s = 0;
        for (Cart cartSumm : cart) {
            s += cartSumm.getQuantity() * cartSumm.getProductEntity().getProductPrice().doubleValue();
        }
        double num = Math.round(s);
        return num;
    }


    @RequestMapping("/addToCart/{productName}")
    public ResponseEntity<List<ProductEntity>> addToCart(@PathVariable String productName, Model model) {



        System.out.println(GlobalData.cart.size());
        ProductEntity product = productService.findByName(productName);

            GlobalData.cart.add(product);


        List<ProductEntity> cart;


        try {

            cart = GlobalData.cart;
            return new ResponseEntity<List<ProductEntity>>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<ProductEntity>>(HttpStatus.BAD_REQUEST);
        }


    }





}

