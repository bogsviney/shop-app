package com.nazarov.shop.dao;

import com.nazarov.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> findAll();

    void save(Product product);

    void edit(Product product);

    void delete(int id);

    public Product findById(int id);
}
