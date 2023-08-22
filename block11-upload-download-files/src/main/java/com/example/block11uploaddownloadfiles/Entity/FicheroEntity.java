package com.example.block11uploaddownloadfiles.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "fichero")
@Data
public class FicheroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date fechaSubida;

    private String categoria;
}
