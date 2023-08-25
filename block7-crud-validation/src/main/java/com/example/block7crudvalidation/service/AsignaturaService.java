package com.example.block7crudvalidation.service;

import com.example.block7crudvalidation.controller.dto.AsignaturaDTO;

import java.util.List;

public interface AsignaturaService {
    AsignaturaDTO createAsignatura(AsignaturaDTO asignaturaDTO);

    AsignaturaDTO getAsignatura(String id);

    List<AsignaturaDTO> getAllAsignaturas();

    AsignaturaDTO updateAsignatura(String id, AsignaturaDTO asignaturaDTO);

    void deleteAsignatura(String id);

    List<AsignaturaDTO> getByIdEstudiante(String idEstuadinte);

}
