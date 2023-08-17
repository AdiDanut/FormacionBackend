package com.example.block7crudvalidation.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String idStudent;
    private Long idPersona;
    private int numHoursWeek;
    private String comments;
    private String idProfesor;
    private String branch;

}

