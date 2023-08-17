package com.example.block7crudvalidation.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO {

    private String idProfesor;
    private Long idPersona;
    private String comments;
    private String branch;

}

