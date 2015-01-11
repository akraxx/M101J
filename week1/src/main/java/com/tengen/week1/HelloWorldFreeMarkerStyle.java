package com.tengen.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("hello.ftl");
            StringWriter stringWriter = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name", "Freemarker");

            helloTemplate.process(helloMap, stringWriter);

            System.out.println(stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
