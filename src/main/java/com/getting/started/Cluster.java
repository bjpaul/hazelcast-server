package com.getting.started;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by bijoy on 11/6/16.
 */
@SpringBootApplication
public class Cluster {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Cluster.class, args);
        /*System.out.println("~~~~~~~~~~~~~~~~List of registered beans~~~~~~~~~~~~");
        for(String s:ctx.getBeanDefinitionNames()){
            System.out.println(s);
        }*/

    }
}
