package com.nazarov.shop.service.security;

import com.nazarov.shop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    private String token;
    private LocalDateTime expireDate;
    private List<Product> cart;
    private Role role;
}
