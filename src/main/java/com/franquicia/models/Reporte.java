package com.franquicia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reporte {
    @Id
    private long id;
    private String tipo;
    private Instant fechaInicio;
    private Instant fechaFin;
    private String intervalo;
    private Long reporteCanceladoId;

}
