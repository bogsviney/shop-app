package com.nazarov.shop.service;

import com.nazarov.shop.entity.Product;
import com.nazarov.shop.service.security.Session;

import java.util.List;

public class CartService {
    Session session;

    public void addToCart(Product product) {
        session.getCart().add(product);
    }

    public void removeFromCart(Product product) {
        session.getCart().remove(product);
    }

    public List<Product> viewCart() {
        return session.getCart();
    }
}
