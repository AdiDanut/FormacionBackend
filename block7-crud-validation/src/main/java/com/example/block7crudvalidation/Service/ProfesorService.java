package com.example.block7crudvalidation.Service;

import com.example.block7crudvalidation.Controller.DTO.ProfesorDTO;
import java.util.List;

public interface ProfesorService {

    ProfesorDTO createProfesor(ProfesorDTO profesorDTO);

    ProfesorDTO getProfesor(String id);

    List<ProfesorDTO> getAllProfesores();

    ProfesorDTO updateProfesor(String id, ProfesorDTO profesorDTO);

    void deleteProfesor(String id);
}
