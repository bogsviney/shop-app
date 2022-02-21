package com.nazarov.shop.web.util;

import com.nazarov.shop.config.PropertiesReader;
import freemarker.template.*;

import java.io.*;
import java.util.*;

public class PageGenerator {
    private static PropertiesReader propertiesReader = new PropertiesReader("application.properties");
    private static String HTML_DIR = propertiesReader.getProperties().getProperty("html.directory");
    private static PageGenerator pageGenerator;
    private final Configuration CONFIGURATION;

    private PageGenerator() {
        CONFIGURATION = new Configuration();
    }

    public static PageGenerator instance() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String path) {
        return getPage(path, Collections.emptyMap());
    }

    public String getPage(String path, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = CONFIGURATION.getTemplate(HTML_DIR + File.separator + path);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }
}
