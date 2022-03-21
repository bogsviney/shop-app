package com.nazarov.shop.service;

import com.nazarov.shop.entity.Product;


import java.util.List;

public class CartService {

    public void addToCart(List <Product> cart, Product product) {
        cart.add(product);
    }

    public void removeFromCart(List <Product> cart, Product product) {
        cart.remove(product);
    }
//
//    public List<Product> viewCart() {
//        return cart.;
//    }


 }
