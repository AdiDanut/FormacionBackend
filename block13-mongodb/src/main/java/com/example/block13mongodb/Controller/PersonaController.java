package com.example.block13mongodb.Controller;

import com.example.block13mongodb.Controller.DTO.PersonaDTO;
import com.example.block13mongodb.Services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;


    @PostMapping
    public ResponseEntity<PersonaDTO> createPersona(@RequestBody PersonaDTO personaDTO) {
        PersonaDTO createdPersona = personaService.createPersona(personaDTO);
        return ResponseEntity.ok(createdPersona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable String id) {
        PersonaDTO personaDTO = personaService.getPersonaById(id);
        if (personaDTO != null) {
            return ResponseEntity.ok(personaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<PersonaDTO>> getAllPersonas(int page, int size) {
        Page<PersonaDTO> personas = personaService.getAllPersonas(page, size);
        return ResponseEntity.ok(personas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDTO> updatePersona(@PathVariable String id, @RequestBody PersonaDTO personaDTO) {
        PersonaDTO updatedPersona = personaService.updatePersona(id, personaDTO);
        if (updatedPersona != null) {
            return ResponseEntity.ok(updatedPersona);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable String id) {
        personaService.deletePersona(id);
        return ResponseEntity.noContent().build();
    }
}
