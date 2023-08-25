package com.example.block7crudvalidation.controller;

import com.example.block7crudvalidation.controller.dto.AsignaturaDTO;
import com.example.block7crudvalidation.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @PostMapping
    public ResponseEntity<AsignaturaDTO> createAsignatura(@RequestBody AsignaturaDTO asignaturaDTO) {
        AsignaturaDTO createdAsignatura = asignaturaService.createAsignatura(asignaturaDTO);
        return ResponseEntity.ok(createdAsignatura);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> getAsignatura(@PathVariable String id) {
        AsignaturaDTO asignaturaDTO = asignaturaService.getAsignatura(id);
        return ResponseEntity.ok(asignaturaDTO);
    }

    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> getAllAsignaturas() {
        List<AsignaturaDTO> asignaturas = asignaturaService.getAllAsignaturas();
        return ResponseEntity.ok(asignaturas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> updateAsignatura(@PathVariable String id, @RequestBody AsignaturaDTO asignaturaDTO) {
        AsignaturaDTO updatedAsignatura = asignaturaService.updateAsignatura(id, asignaturaDTO);
        if (updatedAsignatura != null) {
            return ResponseEntity.ok(updatedAsignatura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable String id) {
        asignaturaService.deleteAsignatura(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<AsignaturaDTO>> getAsignaturasByIdEstudiante(@PathVariable String idEstudiante){
        return ResponseEntity.ok(asignaturaService.getByIdEstudiante(idEstudiante));
    }
}
