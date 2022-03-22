package com.nazarov.shop;

import com.nazarov.shop.config.PropertiesReader;
import com.nazarov.shop.dao.*;
import com.nazarov.shop.dao.jdbc.*;
import com.nazarov.shop.service.*;
import com.nazarov.shop.web.LoginFilter;
import com.nazarov.shop.web.servlets.*;
import com.nazarov.shop.service.security.SecurityService;
import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;


import java.util.EnumSet;
import java.util.Properties;

public class Starter {
    public static void main(String[] args) throws Exception {

        PropertiesReader propertiesReader = new PropertiesReader("application.properties");

        Properties properties = propertiesReader.getProperties();

        ConnectionFactory connectionFactory =
                new ConnectionFactory(properties.getProperty("db.url"),
                        properties.getProperty("db.username"),
                        properties.getProperty("db.password")
                );
//
//        Flyway flyway =
//                Flyway.configure().dataSource(properties.getProperty("db.url"),
//                        properties.getProperty("db.username"),
//                        properties.getProperty("db.password")).load();
//        flyway.migrate();


        ProductDao productDao = new JDBCProductDao(connectionFactory);
        PasswordEncoder passwordEncoder = new PasswordEncoder();
        UserDao userDao = new JDBCUserDao(connectionFactory, passwordEncoder);

        ProductService productService = new ProductService(productDao);
        UserService userService = new UserService(userDao);

        SecurityService securityService = new SecurityService(userService);

        CartService cartService = new CartService();

        LoginServlet loginServlet = new LoginServlet(securityService);

        ProductServlet productServlet = new ProductServlet(productService);

        SaveProductServlet saveProductServlet = new SaveProductServlet(productService, securityService);

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(productService);

        EditProductServlet editProductServlet = new EditProductServlet(productService, securityService);

        AddToCartServlet addToCartServlet = new AddToCartServlet(productService, cartService, securityService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(productServlet), "/products");
        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        contextHandler.addServlet(new ServletHolder(saveProductServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(editProductServlet), "/products/edit/*");
        contextHandler.addServlet(new ServletHolder(deleteProductServlet), "/products/delete/*");
        contextHandler.addServlet(new ServletHolder(addToCartServlet), "/products/cart/*");
        contextHandler.addFilter(new FilterHolder(new LoginFilter(securityService)),"/products/*", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(9898);
        server.setHandler(contextHandler);
        server.start();

    }
}
