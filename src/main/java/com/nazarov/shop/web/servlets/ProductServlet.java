package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.util.PageGenerator;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class ProductServlet extends HttpServlet {

    private List<String> userTokens;
    private ProductService productService;

    public ProductServlet(ProductService productService, List<String> userTokens) {
        this.productService = productService;
        this.userTokens = userTokens;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<Product> products = productService.findAll();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);
        String page = pageGenerator.getPage("products.html", params);
        response.getWriter().write(page);
    }
}
