package com.example.block7crudvalidation.service.impl;

import com.example.block7crudvalidation.controller.dto.AsignaturaDTO;
import com.example.block7crudvalidation.entity.AsignaturaEntity;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.service.AsignaturaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public AsignaturaDTO createAsignatura(AsignaturaDTO asignaturaDTO) {
        AsignaturaEntity asignaturaEntity = modelMapper.map(asignaturaDTO, AsignaturaEntity.class);
        asignaturaEntity = asignaturaRepository.save(asignaturaEntity);
        return modelMapper.map(asignaturaEntity, AsignaturaDTO.class);
    }

    @Override
    public AsignaturaDTO getAsignatura(String id) {
        AsignaturaEntity asignaturaEntity = asignaturaRepository.findById(id).orElse(null);
        return modelMapper.map(asignaturaEntity, AsignaturaDTO.class);
    }

    @Override
    public List<AsignaturaDTO> getAllAsignaturas() {
        List<AsignaturaEntity> asignaturaEntities = asignaturaRepository.findAll();
        return asignaturaEntities.stream()
                .map(entity -> modelMapper.map(entity, AsignaturaDTO.class))
                .toList();
    }

    @Override
    public AsignaturaDTO updateAsignatura(String id, AsignaturaDTO asignaturaDTO) {
        if (asignaturaRepository.existsById(id)) {
            AsignaturaEntity asignaturaEntity = modelMapper.map(asignaturaDTO, AsignaturaEntity.class);
            asignaturaEntity.setIdAsignatura(id);

            asignaturaEntity = asignaturaRepository.save(asignaturaEntity);
            return modelMapper.map(asignaturaEntity, AsignaturaDTO.class);
        }
        return null;
    }

    @Override
    public void deleteAsignatura(String id) {
        asignaturaRepository.deleteById(id);
    }

    @Override
    public List<AsignaturaDTO> getByIdEstudiante(String idEstudiante) {
        List<AsignaturaEntity> asignaturaEntities = asignaturaRepository.findAsignaturasByStudentId(idEstudiante);

        return asignaturaEntities.stream()
                .map(this::convertToDTO)
                .toList();
    }

    private AsignaturaDTO convertToDTO(AsignaturaEntity entity) {
        return modelMapper.map(entity, AsignaturaDTO.class);
    }
}







