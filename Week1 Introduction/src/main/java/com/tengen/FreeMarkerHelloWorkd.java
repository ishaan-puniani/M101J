package com.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ishaan.puniani on 2014-03-25.
 */
public class FreeMarkerHelloWorkd {
    public static void main(String[] args) {
Configuration configuration= new Configuration();
        configuration.setClassForTemplateLoading(FreeMarkerHelloWorkd.class,"/");
        try {
            Template tmpl = configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("message","Ishaan");
            tmpl.process(map,writer);
            System.out.println(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
