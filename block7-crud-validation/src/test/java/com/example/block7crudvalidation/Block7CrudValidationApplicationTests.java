package com.example.block7crudvalidation;

import com.example.block7crudvalidation.entity.PersonaEntity;
import com.example.block7crudvalidation.repository.PersonaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Block7CrudValidationApplicationTests {

    @Autowired
    private PersonaRepository personaRepository;

    @Test
    void contextLoads() {
        PersonaEntity persona = new PersonaEntity();
        persona.setUsuario("john_doe");
        persona.setPassword("password123");
        persona.setName("John");
        persona.setSurname("Doe");
        persona.setCompanyEmail("john.doe@company.com");
        persona.setPersonalEmail("john.doe@example.com");
        persona.setCity("New York");
        persona.setActive(true);
        persona.setCreatedDate(new Date());
        persona.setImageUrl("https://example.com/john_doe.jpg");
        persona.setTerminationDate(null);

        PersonaEntity personaEntity = personaRepository.save(persona);

        assertNotNull(personaEntity);
    }

}
