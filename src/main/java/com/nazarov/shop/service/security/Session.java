package com.nazarov.shop.service.security;

import com.nazarov.shop.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public class Session {
    String token;
    LocalDateTime expireDate;
    List<Product> cart;
}
