package com.tengen.week2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import java.net.UnknownHostException;

/**
 * Created by Maximilien on 17/01/2015.
 */
public class Homework22 {

    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = new MongoClient().getDB("students").getCollection("grades");


        int i = 0;
        QueryBuilder queryBuilder = QueryBuilder.start("type").is("homework");

        try(DBCursor dbObjects = collection.find(queryBuilder.get()).sort(new BasicDBObject("student_id", 1).append("score", 1))) {

            int lastStudentId = -1;
            while (dbObjects.hasNext()) {
                DBObject object = dbObjects.next();
                Integer studentIdInDb = Integer.valueOf(object.get("student_id").toString());
                if(studentIdInDb != lastStudentId) {
                    i++;

                    collection.remove(new BasicDBObject("_id", object.get("_id")));
                    lastStudentId = studentIdInDb;
                }
                System.out.println(object);

            }
        }

        System.out.println(i);

    }

}
