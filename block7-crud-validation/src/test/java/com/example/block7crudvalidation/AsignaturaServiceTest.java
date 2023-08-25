package com.example.block7crudvalidation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.block7crudvalidation.controller.dto.AsignaturaDTO;
import com.example.block7crudvalidation.entity.AsignaturaEntity;
import com.example.block7crudvalidation.repository.AsignaturaRepository;
import com.example.block7crudvalidation.service.impl.AsignaturaServiceImpl;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AsignaturaServiceImpl asignaturaService;

    @Test
    public void testCreateAsignatura() {
        AsignaturaDTO dto = new AsignaturaDTO();
        AsignaturaEntity entity = new AsignaturaEntity();

        when(modelMapper.map(dto, AsignaturaEntity.class)).thenReturn(entity);
        when(asignaturaRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, AsignaturaDTO.class)).thenReturn(dto);

        AsignaturaDTO result = asignaturaService.createAsignatura(dto);

        assertEquals(dto, result);
    }

    @Test
    public void testGetAsignatura() {
        AsignaturaEntity entity = new AsignaturaEntity();
        entity.setIdAsignatura("1");
        AsignaturaDTO dto = new AsignaturaDTO();

        when(asignaturaRepository.findById("1")).thenReturn(Optional.of(entity));
        when(modelMapper.map(entity, AsignaturaDTO.class)).thenReturn(dto);

        AsignaturaDTO result = asignaturaService.getAsignatura("1");

        assertEquals(dto, result);
    }

    @Test
    public void testGetAsignaturaNotFound() {
        when(asignaturaRepository.findById("1")).thenReturn(Optional.empty());
        AsignaturaDTO result = asignaturaService.getAsignatura("1");
        assertNull(result);
    }

    @Test
    public void testGetAllAsignaturas() {

        AsignaturaEntity entity1 = new AsignaturaEntity();
        AsignaturaDTO dto1 = new AsignaturaDTO();

        AsignaturaEntity entity2 = new AsignaturaEntity();
        AsignaturaDTO dto2 = new AsignaturaDTO();

        when(asignaturaRepository.findAll()).thenReturn(List.of(entity1, entity2));

        when(modelMapper.map(entity1, AsignaturaDTO.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, AsignaturaDTO.class)).thenReturn(dto2);

        List<AsignaturaDTO> expected = List.of(dto1, dto2);

        assertEquals(expected, asignaturaService.getAllAsignaturas());
    }

    @Test
    public void testUpdateAsignatura() {

        AsignaturaDTO dto = new AsignaturaDTO();
        AsignaturaEntity entity = new AsignaturaEntity();

        when(asignaturaRepository.existsById("1")).thenReturn(true);
        when(modelMapper.map(dto, AsignaturaEntity.class)).thenReturn(entity);
        when(asignaturaRepository.save(entity)).thenReturn(entity);
        when(modelMapper.map(entity, AsignaturaDTO.class)).thenReturn(dto);

        AsignaturaDTO expected = dto;

        AsignaturaDTO result = asignaturaService.updateAsignatura("1", dto);

        assertEquals(expected, result);

    }

    @Test
    public void testGetByIdEstudiante() {

        AsignaturaEntity entity1 = new AsignaturaEntity();
        AsignaturaDTO dto1 = new AsignaturaDTO();

        AsignaturaEntity entity2 = new AsignaturaEntity();
        AsignaturaDTO dto2 = new AsignaturaDTO();

        when(asignaturaRepository.findAsignaturasByStudentId("1"))
                .thenReturn(List.of(entity1, entity2));

        when(modelMapper.map(entity1, AsignaturaDTO.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, AsignaturaDTO.class)).thenReturn(dto2);

        List<AsignaturaDTO> expected = List.of(dto1, dto2);

        assertEquals(expected, asignaturaService.getByIdEstudiante("1"));

    }

    @Test
    public void testDeleteAsignatura() {

        asignaturaService.deleteAsignatura("1");

        verify(asignaturaRepository).deleteById("1");

    }
}
