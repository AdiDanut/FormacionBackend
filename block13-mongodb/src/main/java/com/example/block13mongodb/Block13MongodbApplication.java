package com.example.block13mongodb;

import com.example.block13mongodb.Repository.PersonaRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class Block13MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Block13MongodbApplication.class, args);
    }

}
