package com.nazarov.shop.service;

import com.nazarov.shop.dao.UserDao;

public class UserService {

    private UserDao userDao;

    public UserService() {

    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean checkUser(String email, String password){
       return userDao.checkUser(email,password);
    }
}
