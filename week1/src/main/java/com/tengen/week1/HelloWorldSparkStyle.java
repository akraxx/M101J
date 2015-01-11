package com.tengen.week1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by Maximilien on 10/01/2015.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/echo/:thing", (request, response) -> request.params(":thing"));
    }
}
