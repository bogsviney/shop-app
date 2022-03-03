package com.nazarov.shop.entity;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Product {
    private int id;
    private double price;
    private String name;
    private LocalDateTime creationDate;
}
