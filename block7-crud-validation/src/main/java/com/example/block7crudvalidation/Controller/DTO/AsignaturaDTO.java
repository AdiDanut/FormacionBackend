package com.example.block7crudvalidation.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {
    private Long idAsignatura;
    private Long idStudent;
    private String asignatura;
    private String comments;
    private Date initialDate;
    private Date finishDate;
}
