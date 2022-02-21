package com.nazarov.shop;

import com.nazarov.shop.config.PropertiesReader;
import com.nazarov.shop.dao.*;
import com.nazarov.shop.dao.jdbc.JDBCProductDao;
import com.nazarov.shop.service.ProductService;
import com.nazarov.shop.web.servlets.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import java.util.ArrayList;
import java.util.List;
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

        ProductDao productDao = new JDBCProductDao(connectionFactory);

        ProductService service = new ProductService(productDao);

        List<String> userTokens = new ArrayList<>();
        LoginServlet loginServlet = new LoginServlet(userTokens);

        ProductServlet productServlet = new ProductServlet(service, userTokens);

        SaveProductServlet saveProductServlet = new SaveProductServlet(service, userTokens);

        DeleteProductServlet deleteProductServlet = new DeleteProductServlet(service, userTokens);

        EditProductServlet editProductServlet = new EditProductServlet(service, userTokens);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(loginServlet), "/login");
        contextHandler.addServlet(new ServletHolder(productServlet), "/products");
        contextHandler.addServlet(new ServletHolder(saveProductServlet), "/products/add");
        contextHandler.addServlet(new ServletHolder(editProductServlet), "/products/edit/*");
        contextHandler.addServlet(new ServletHolder(deleteProductServlet), "/products/delete/*");

        Server server = new Server(9898);
        server.setHandler(contextHandler);
        server.start();

    }
}
