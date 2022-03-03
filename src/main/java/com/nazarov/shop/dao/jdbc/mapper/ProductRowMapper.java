package com.nazarov.shop.dao.jdbc.mapper;

import com.nazarov.shop.entity.Product;

import java.sql.*;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        double price = resultSet.getDouble("price");
        String name = resultSet.getString("name");
        Timestamp creationDate = resultSet.getTimestamp("date");

        Product product = Product.builder()
                .id(id)
                .name(name)
                .price(price)
                .creationDate(creationDate.toLocalDateTime())
                .build();
        return product;
    }
}
