package com.example.block7crudvalidation.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturaDTO {
    private String idAsignatura;
    private List<String> idStudent;
    private String asignatura;
    private String comments;
    private Date initialDate;
    private Date finishDate;
}
