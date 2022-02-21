package com.nazarov.shop.dao.jdbc;

import com.nazarov.shop.dao.*;
import com.nazarov.shop.dao.jdbc.mapper.ProductRowMapper;
import com.nazarov.shop.entity.Product;

import java.sql.*;
import java.util.*;

public class JDBCProductDao implements ProductDao {

    private final ConnectionFactory CONNECTION_FACTORY;
    private static final ProductRowMapper PRODUCT_ROW_MAPPER = new ProductRowMapper();
    private static final String FIND_BY_ID = "SELECT id, name, price FROM products WHERE id =?;";
    private static final String FIND_ALL = "SELECT id, name, price FROM products;";
    private static final String ADD = """
            INSERT INTO products (name, price)
            
            VALUES(?, ?);""";
    private static final String EDIT = "UPDATE products SET name = ?, price = ? WHERE id = ?;";
    private static final String DELETE_BY_ID = "DELETE FROM products WHERE id = ?;";

    public JDBCProductDao(ConnectionFactory connectionFactory) {
        this.CONNECTION_FACTORY = connectionFactory;
    }

    @Override
    public List<Product> findAll() {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Product product) {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! Product NOT ADDED", e);
        }
    }

    @Override
    public void edit(Product product) {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with product editing", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR! Product NOT DELETED", e);
        }

    }

    @Override
    public Product findById(int id) {
        try (Connection connection = CONNECTION_FACTORY.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Product product = PRODUCT_ROW_MAPPER.mapRow(resultSet);
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error with finding product by ID", e);
        }
    }
}
