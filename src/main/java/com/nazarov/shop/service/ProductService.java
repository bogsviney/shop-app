package com.nazarov.shop.service;

import com.nazarov.shop.dao.ProductDao;
import com.nazarov.shop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findAll() {
        List<Product> products = productDao.findAll();
        System.out.println("PRODUCT SERVICE: FIND ALL PRODUCTS");
        return products;
    }

    public void save(Product product) {
        LocalDateTime now = LocalDateTime.now();
        product.setCreationDate(now);
        productDao.save(product);
    }

    public void edit(Product product) {
        productDao.edit(product);
    }

    public void delete(int id) {
        productDao.delete(id);
        System.out.println("PRODUCT SERVICE: DELETE PRODUCT");
    }

    public Product findById(int id) {
        Product product = productDao.findById(id);
        System.out.println("PRODUCT SERVICE: FIND BY ID: " + id);
        return product;
    }
}
