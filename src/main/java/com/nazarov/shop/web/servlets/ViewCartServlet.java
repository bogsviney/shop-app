package com.nazarov.shop.web.servlets;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.CartService;
import com.nazarov.shop.service.security.SecurityService;
import com.nazarov.shop.service.security.Session;
import com.nazarov.shop.web.util.PageGenerator;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ViewCartServlet extends HttpServlet {

    private CartService cartService;
    private SecurityService securityService;

    public ViewCartServlet(CartService cartService, SecurityService securityService) {
        this.cartService = cartService;
        this.securityService = securityService;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String token = securityService.getTokenFromCookies(request.getCookies());
        Session session = securityService.getSession(token);
        List<Product> products = session.getCart();
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        params.put("products", products);
        String page = pageGenerator.getPage("cart.html", params);
        response.getWriter().write(page);
    }
}
