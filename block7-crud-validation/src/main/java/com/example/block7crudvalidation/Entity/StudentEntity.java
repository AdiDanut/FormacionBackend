package com.example.block7crudvalidation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private PersonaEntity persona;

    @Column(name = "num_hours_week")
    private int numHoursWeek;

    @Column(name = "coments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_profesor")
    private ProfesorEntity profesor;

    @Column(name = "branch", nullable = false)
    private String branch;
}
