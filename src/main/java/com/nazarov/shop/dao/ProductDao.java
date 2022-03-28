package com.nazarov.shop.dao;

import com.nazarov.shop.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductDao {

    List<Product> findAll();

    void save(Product product);

    void edit(Product product);

    void delete(int id);

    Product findById(int id);
}
