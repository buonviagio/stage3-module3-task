package com.mjc.school;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("\u001B[32mWe start method main() in Class Main\u001B[0m");
        /* Creating spring container, here spring starts scanning classes with annotation Component, Service creating context, passing class configuration */
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* spring returns instance of Bean StartApp, which has name startApp */
        StartApplication startApp = context.getBean("startApplication", StartApplication.class);
        startApp.run();
        log.info("The end of the method main in the Main class, application work finished successfully\n");
    }
}
