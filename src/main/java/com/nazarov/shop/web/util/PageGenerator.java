package com.nazarov.shop.web.util;

import freemarker.template.*;
import java.io.*;
import java.util.*;

public class PageGenerator {

    private static final String HTML_DIR = "templates";

    private static PageGenerator pageGenerator;
    private final Configuration configuration;
    private PageGenerator() {
        configuration = new Configuration();
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
            Template template = configuration.getTemplate(HTML_DIR + File.separator + path);
            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }
}
