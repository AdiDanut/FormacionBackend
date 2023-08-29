package com.example.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Scanner;

@SpringBootApplication
public class SpringBootProviderApplication implements CommandLineRunner {

	@Autowired
	private final KafkaTemplate<String, String> kafkaTemplate;

	public SpringBootProviderApplication(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProviderApplication.class, args);
	}

	@KafkaListener(topics = "my-topic", groupId = "my-group")
	public void consumeMessage(String message) {
		System.out.println("\n Mensaje de respuesta: " + message);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Introduce un mensaje para enviar: ");
			String message = scanner.nextLine();
			kafkaTemplate.send("my-topic2", message);
		}
	}
}
