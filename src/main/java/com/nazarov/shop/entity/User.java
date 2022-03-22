package com.nazarov.shop.entity;

import com.nazarov.shop.service.security.Role;
import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String  password;
    private Role role;
}
