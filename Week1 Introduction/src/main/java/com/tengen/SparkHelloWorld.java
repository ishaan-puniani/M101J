package com.tengen;

import spark.*;

import static spark.Spark.*;

/**
 * Created by ishaan.puniani on 2014-03-25.
 */
public class SparkHelloWorld {
    public static void main(String[] args) {
        get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World!";
            }
        });
    }
}
