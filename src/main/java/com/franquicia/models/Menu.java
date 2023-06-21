package com.franquicia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long myid;
    private long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private String urlImagen;
    private Boolean activo;
    private String creado;
    private String actualizado;
}
