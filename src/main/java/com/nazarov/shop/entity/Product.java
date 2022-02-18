package com.nazarov.shop.entity;

import lombok.*;

@Data
@Builder
public class Product {
    private int id;
    private double price;
    private String name;
}
