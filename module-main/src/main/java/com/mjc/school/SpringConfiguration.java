package com.mjc.school;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Main Spring configuration class for the application.
 *
 * This class serves as the primary configuration class that sets up:
 *  - Component scanning for the application
 *  - AspectJ auto-proxy configuration
 *  - Application context initialization
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "com.mjc.school")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringConfiguration {
    public SpringConfiguration() {
        log.info("Initializing SpringConfiguration...");
    }
}
