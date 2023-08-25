package com.example.block7crudvalidation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_student")
    private String idStudent;

    @OneToOne
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private PersonaEntity persona;

    @Column(name = "num_hours_week")
    private int numHoursWeek;

    @Column(name = "coments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private ProfesorEntity profesor;

    @Column(name = "branch")
    private String branch;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "student_asignatura",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "asignatura_id")
    )
    @JsonIgnore
    private List<AsignaturaEntity> asignaturaEntityList;
}
