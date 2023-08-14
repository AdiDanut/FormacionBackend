package com.example.block5logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Block5LoggingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Block5LoggingApplication.class, args);

        // Ejemplos de logs
        Logger logger = LoggerFactory.getLogger(Block5LoggingApplication.class);

        logger.debug("Este es un mensaje de depuración.");
        logger.info("Este es un mensaje informativo.");
        logger.warn("Este es un mensaje de advertencia.");
        logger.error("Este es un mensaje de error.");
    }
}