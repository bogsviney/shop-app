package com.nazarov.shop.service;

import com.nazarov.shop.entity.Product;


import java.util.List;

public class CartService {

    public void addToCart(List <Product> cart, Product product) {
        cart.add(product);
        System.out.println("CART SERVICE: Product has been added to cart");
        System.out.println(cart);
    }

    public void removeFromCart(List <Product> cart, Product product) {
        cart.remove(product);
        System.out.println("CART SERVICE: Product has been removed from cart");
        System.out.println(cart);
    }

//    public List<Product> viewCart() {
//        return cart.;
//    }


 }
