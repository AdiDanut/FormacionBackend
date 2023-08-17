package com.example.block7crudvalidation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFESOR")
public class ProfesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_profesor")
    private String idProfesor;

    @OneToOne
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    private PersonaEntity persona;

    @Column(name = "coments")
    private String comments;

    @Column(name = "branch", nullable = false)
    private String branch;

}
