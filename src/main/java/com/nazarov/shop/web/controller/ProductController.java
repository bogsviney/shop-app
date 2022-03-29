package com.nazarov.shop.web.controller;

import com.nazarov.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String showAllProducts(ModelMap model) {
        model.addAttribute("products", productService.findAll());
        return "products";
    }

    @GetMapping("/add")
    public ModelAndView getAddProductPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("products_save");
        return view;
    }

//    @PostMapping("/add")
//    public String saveProduct(@ModelAttribute("product") Product product) {
//        productService.save(product);
//        return "redirect:/";
//    }

    @GetMapping("/edit/{id}")
    public String getEditProductPage(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("product", productService.findById(id));
        return "products_edit";
    }

//    @PutMapping("/edit/{id}")
//    public String editProduct(@PathVariable("id") int id, Product product) {
//        product = productService.findById(id);
//        productService.edit(product);
//        return "redirect:/";
//    }

//    @DeleteMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable("id") int id) {
//        productService.delete(id);
//        return "redirect:/";
//    }
}
