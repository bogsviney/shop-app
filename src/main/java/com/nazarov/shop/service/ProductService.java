package com.nazarov.shop.service;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.entity.Product;

import java.util.List;

public class ProductService {

    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        List<Product> products = productDao.findAll();
        return products;
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public void edit(Product product) {
        productDao.edit(product);
    }

    public void delete(int id) {
        productDao.delete(id);
    }

    public Product findById(int id) {
        Product product = productDao.findById(id);
        return product;
    }
}
