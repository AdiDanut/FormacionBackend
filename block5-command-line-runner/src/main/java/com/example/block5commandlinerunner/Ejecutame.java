package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class Ejecutame {
    Scanner sc = new Scanner(System.in);
    @Value("${my.number}")
    private String miValor;
    @Value("${greeting}")
    private String miValor2;

    @Value("${mivar2}")
    private String miValor3;
    @Bean
    CommandLineRunner ejecutame1()
    {
        return p ->
        {
            System.out.println("Hola desde la clase 1");
        };
    }

    @Bean
    CommandLineRunner ejecutame2()
    {
        String str = sc.nextLine();
        return p ->
        {
            System.out.println(str);
        };
    }

    @PostConstruct
    public void ejecutame3PostConstruct() {
        System.out.println("El valor de greeting es: " +  miValor + "\n" +
                        "El valor de my.number es: " + miValor2 + "\n"  +
                        "El valor de new.property es: " + miValor3 + "\n");
    }
}
