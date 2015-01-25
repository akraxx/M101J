package com.tengen.week3;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Maximilien on 25/01/2015.
 */
public class Homework31 {
    public static void main(String[] args) throws UnknownHostException {
        DBCollection collection = new MongoClient().getDB("school").getCollection("students");

        DBCursor dbCursor = collection.find();

        for (DBObject dbObject : dbCursor) {
            List<DBObject> scores = (List<DBObject>)dbObject.get("scores");

            List<DBObject> newScores = new ArrayList<>();
            DBObject lowestHomework = null;
            for (DBObject score : scores) {

                if(score.get("type").toString().equals("homework")) {
                    if(lowestHomework == null) {
                        lowestHomework = score;
                    } else if(Double.valueOf(score.get("score").toString())
                            < Double.valueOf(lowestHomework.get("score").toString())) {
                        lowestHomework = score;
                    }
                }
                newScores.add(score);
            }

            if(lowestHomework != null) {
                newScores.remove(lowestHomework);
            }

            collection.update(new BasicDBObject("_id", dbObject.get("_id")), new BasicDBObject("$set",
                    new BasicDBObject("scores", newScores)));
            System.out.println(newScores);
        }
    }
}
