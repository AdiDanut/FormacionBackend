package com.example.block7crudvalidation.Service.Impl;

import com.example.block7crudvalidation.Controller.DTO.AsignaturaDTO;
import com.example.block7crudvalidation.Entity.AsignaturaEntity;
import com.example.block7crudvalidation.Repository.AsignaturaRepository;
import com.example.block7crudvalidation.Service.AsignaturaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
    }

    private AsignaturaDTO convertToDTO(AsignaturaEntity entity) {
        return modelMapper.map(entity, AsignaturaDTO.class);
    }
}







