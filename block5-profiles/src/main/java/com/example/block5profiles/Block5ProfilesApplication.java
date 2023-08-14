package com.example.block5profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Block5ProfilesApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block5ProfilesApplication.class, args);
    }

    @Component
    @Profile("INT")
    class MyCommandLineRunner implements CommandLineRunner {

        @Autowired
        private Environment environment;

        @Override
        public void run(String... args) throws Exception {
            String[] activeProfiles = environment.getActiveProfiles();

            if (activeProfiles.length > 0) {
                String activeProfile = activeProfiles[0];
                String bdUrl = environment.getProperty("bd.url");

                System.out.println("Active profile: " + activeProfile);
                System.out.println("bd.url: " + bdUrl);
            } else {
                System.out.println("No active profiles found.");
            }
        }
    }

}
