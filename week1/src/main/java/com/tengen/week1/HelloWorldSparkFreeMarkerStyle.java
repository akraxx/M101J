package com.tengen.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class HelloWorldSparkFreeMarkerStyle {

    public static void main(String[] args) {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class, "/");

        Spark.get("/", (request, response) -> {
            StringWriter stringWriter = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Freemarker");

                helloTemplate.process(helloMap, stringWriter);
            } catch (Exception e) {
                Spark.halt(500,e.getMessage());
                e.printStackTrace();
            }
            return stringWriter;
        });
    }
}
