package com.nazarov.shop.web.controller;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProductController {

//    @Autowired
//    private final ProductService productService;
//
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getView(ModelMap model) {
        model.addAttribute("products", List.of(Product.builder()
                .id(1)
                .name("MEGA")
                .price(100)
                .creationDate(LocalDateTime.now())
                .build()));
        return "products";
    }

//    @GetMapping("/products")
//    public String getView(ModelMap model) {
//        model.addAttribute("products",productService.findAll());
//       return "products" ;
//    }
}
