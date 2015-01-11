package com.tengen.week1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get("/", (request, response) -> {
            StringWriter stringWriter = new StringWriter();
            try {
                Map<String, Object> fruitsMap = new HashMap<>();
                fruitsMap.put("fruits", Arrays.asList("apple", "orange", "banana", "peach"));

                Template fruitPickerTemplate = configuration.getTemplate("fruit.ftl");

                fruitPickerTemplate.process(fruitsMap, stringWriter);
            } catch (Exception e) {
                Spark.halt(500, e.getMessage());
                e.printStackTrace();
            }
            return stringWriter;
        });

        Spark.post("/favorite_fruit", (request, response) -> {
            final String fruit = request.queryParams("fruit");

            if(fruit == null) {
                return "Why don't you pick one?";
            } else {
                return "Your favorite fruit is " + fruit;
            }
        });
    }
}
