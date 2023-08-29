package com.example.consumer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Scanner;


@SpringBootApplication
public class SpringBootConsumerApplication implements CommandLineRunner {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public SpringBootConsumerApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Introduce un mensaje de respuesta: ");
			String message = scanner.nextLine();
			kafkaTemplate.send("my-topic", message);
		}
	}

	@KafkaListener(topics = "my-topic2", groupId = "my-group")
	public void consumeMessage(String message) {
		System.out.println("Mensaje recibido: " + message);
	}

}
