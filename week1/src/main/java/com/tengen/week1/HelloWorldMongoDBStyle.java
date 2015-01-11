package com.tengen.week1;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class HelloWorldMongoDBStyle {
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");

        DBCollection collection = database.getCollection("hello");

        DBObject dbObject = collection.findOne();

        System.out.println(dbObject);

    }
}
