package com.nazarov.shop.dao;

public interface UserDao {
    boolean checkUser(String email, String password);
}
