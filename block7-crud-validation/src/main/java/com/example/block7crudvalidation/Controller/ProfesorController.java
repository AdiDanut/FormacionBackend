package com.example.block7crudvalidation.Controller;

import com.example.block7crudvalidation.Controller.DTO.ProfesorDTO;
import com.example.block7crudvalidation.Service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> createProfesor(@RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO createdProfesor = profesorService.createProfesor(profesorDTO);
        return ResponseEntity.ok(createdProfesor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getProfesor(@PathVariable String id) {
        ProfesorDTO profesorDTO = profesorService.getProfesor(id);
        return ResponseEntity.ok(profesorDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesores() {
        List<ProfesorDTO> profesores = profesorService.getAllProfesores();
        return ResponseEntity.ok(profesores);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> updateProfesor(@PathVariable String id, @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO updatedProfesor = profesorService.updateProfesor(id, profesorDTO);
        return ResponseEntity.ok(updatedProfesor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable String id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}

