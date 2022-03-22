package com.nazarov.shop.service;

import com.nazarov.shop.dao.UserDao;
import com.nazarov.shop.service.security.Role;

public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkUser(String email, String password) {
        return userDao.checkUser(email, password);
    }

    public String getUserRole(String email) {
       return userDao.getUserRole(email);
    }
}

