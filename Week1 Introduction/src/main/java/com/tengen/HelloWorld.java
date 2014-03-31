package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by ishaan.puniani on 2014-03-25.
 */
public class HelloWorld {
    public static void main(String[] args) {


    final   Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorld.class, "/");
        try {
            get(new Route("/world") {
                @Override
                public Object handle(Request request, Response response) {

                    Template tmpl = null;
                    try {
                        tmpl = configuration.getTemplate("hello.ftl");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    StringWriter writer = new StringWriter();
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("message", "Ishaan");
                    try {
                        tmpl.process(map, writer);
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