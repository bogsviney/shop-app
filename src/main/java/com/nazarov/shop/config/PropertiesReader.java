package com.nazarov.shop.config;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {

    private final Properties properties;

    public PropertiesReader(String path) {
        properties = new Properties();
        readProperties(path);
    }

    public Properties getProperties() {
        return new Properties(properties);
    }

    private void readProperties(String path) {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(path)) {
            properties.load(resource);
        } catch (IOException e) {
            throw new RuntimeException("CANNOT READ PROPERTIES by " + path, e);
        }
    }
}
