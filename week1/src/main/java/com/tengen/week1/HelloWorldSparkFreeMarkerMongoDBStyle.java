package com.tengen.week1;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class HelloWorldSparkFreeMarkerMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_21);
        configuration.setClassForTemplateLoading(HelloWorldSparkFreeMarkerStyle.class, "/");

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));
        DB database = client.getDB("course");
        DBCollection collection = database.getCollection("hello");

        Spark.get("/", (request, response) -> {
            StringWriter stringWriter = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("hello.ftl");
                DBObject document = collection.findOne();

                helloTemplate.process(document, stringWriter);
            } catch (Exception e) {
                Spark.halt(500, e.getMessage());
                e.printStackTrace();
            }
            return stringWriter;
        });
    }
}
