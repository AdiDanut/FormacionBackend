package com.example.block7crud.Controller;

import com.example.block7crud.Entity.PersonaEntity;
import com.example.block7crud.Service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public PersonaEntity addPersona(@RequestBody PersonaEntity persona) {
        return personaService.addPersona(persona);
    }

    @PutMapping("/{id}")
    public PersonaEntity updatePersona(@PathVariable Long id, @RequestBody PersonaEntity persona) throws ClassNotFoundException {
        return personaService.updatePersona(id, persona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id) throws ClassNotFoundException {
        personaService.deletePersona(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public PersonaEntity getPersonaById(@PathVariable Long id) throws ClassNotFoundException {
        return personaService.getPersonaById(id);
    }

    @GetMapping("/nombre/{nombre}")
    public List<PersonaEntity> getPersonasByNombre(@PathVariable String nombre) {
        return personaService.getPersonasByNombre(nombre);
    }

    @GetMapping
    public List<PersonaEntity> getAllPersonas() {
        return personaService.getAllPersonas();
    }
}
