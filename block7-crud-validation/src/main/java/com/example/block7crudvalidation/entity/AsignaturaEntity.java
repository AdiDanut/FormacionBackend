package com.example.block7crudvalidation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ASIGNATURA")
public class AsignaturaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_asignatura")
    private String idAsignatura;

    @ManyToMany(mappedBy = "asignaturaEntityList", cascade = CascadeType.MERGE)
    private List<StudentEntity> student;

    @Column(name = "asignatura")
    private String asignatura;

    @Column(name = "coments")
    private String comments;

    @Column(name = "initial_date", nullable = false)
    private Date initialDate;

    @Column(name = "finish_date")
    private Date finishDate;

}
