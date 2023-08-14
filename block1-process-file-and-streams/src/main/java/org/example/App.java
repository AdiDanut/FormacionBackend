package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws InvalidLineFormatException {
        Class<App> clazz = App.class;
        InputStream inputStream = clazz.getResourceAsStream("/prueba.csv");
        List<Person> lines = readPeopleFromInputStream(inputStream);
        lines.forEach(line -> {
            System.out.println(line.toString());
        });

    }

    private static List<Person> readPeopleFromInputStream(InputStream inputStream) throws InvalidLineFormatException {
        List<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    Person person = parsePersonLine(line);
                    people.add(person);
                } catch (InvalidLineFormatException e) {
                    throw new InvalidLineFormatException("Error en la línea: " + line, e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    private static Person parsePersonLine(String line) throws InvalidLineFormatException {
        String[] parts = line.split(",");

        String name = parts[0];
        if (name.trim().isEmpty()) {
            throw new InvalidLineFormatException("El nombre está vacío en la línea: " + line);
        }
        String town = parts.length > 1 ? parts[1] : "Desconocida";
        int age = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;

        return new Person(name, town, age);
    }

}
