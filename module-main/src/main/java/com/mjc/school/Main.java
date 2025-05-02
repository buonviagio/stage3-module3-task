package com.mjc.school;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.security.auth.login.AccountLockedException;


//@Slf4j
//@SpringBootApplication
/*public class Main {
    public static void main(String[] args) {
        log.info("\u001B[32mWe start method main() in Class Main\u001B[0m");
        *//* Creating spring container, here spring starts scanning classes with annotation Component, Service creating context, passing class configuration *//*
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        SpringApplication.run(SpringConfiguration.class, args);
        *//* spring returns instance of Bean StartApp, which has name startApp *//*
        StartApplication startApp = context.getBean("startApplication", StartApplication.class);
        *//*Object dataSource = context.getBean("dataSource");
        System.out.println("dataSource->" + dataSource);*//*
        startApp.run();


        log.info("The end of the method main in the Main class, application work finished successfully\n");

    }
}*/

@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaAuditing
public class Main implements CommandLineRunner {

    @Autowired
    private Environment environment;

    private final StartApplication startApp;

    @Autowired
    public Main(StartApplication startApp) {
        this.startApp = startApp;
    }

    public static void main(String[] args) {
        log.info("\u001B[32mWe start method main() in Class Main\u001B[0m");
        SpringApplication.run(Main.class, args);
        log.info("The end of the method main in the Main class, application work finished successfully\n");
    }
    // Run() will be executed after the Spring Boot application context is fully initialized
    // Here I check whether application.properties file was read
    @Override
    public void run(String... args) throws Exception {
        try {
        System.out.println("H2 Console Enabled: " + environment.getProperty("spring.h2.console.enabled"));
        System.out.println("H2 Console Path: " + environment.getProperty("spring.h2.console.path"));
        System.out.println("Datasource URL: " + environment.getProperty("spring.datasource.url"));
        // Now 'startApp' is injected by Spring Boot
        startApp.run();
        }catch (Exception ex){

            System.out.println(ex);
            log.error("Exception occurred while running StartApplication:", ex);

        }
    }
}