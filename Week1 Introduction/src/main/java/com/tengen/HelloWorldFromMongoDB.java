package com.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;

import static spark.Spark.get;

/**
 * Created by ishaan.puniani on 2014-03-25.
 */
public class HelloWorldFromMongoDB {
    public static void main(String[] args) {


        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFromMongoDB.class, "/");
        try {
            MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
            DB db = client.getDB("test");
            DBCollection collection= db.getCollection("things");
           final DBObject obj = collection.findOne();

            get(new Route("/mongo") {
                @Override
                public Object handle(Request request, Response response) {

                    Template tmpl = null;
                    try {
                        tmpl = configuration.getTemplate("hello.ftl");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    StringWriter writer = new StringWriter();

                    try {
                        tmpl.process(obj, writer);
                    } catch (TemplateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(writer);
                    return writer;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
