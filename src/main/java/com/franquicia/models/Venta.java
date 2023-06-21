package com.franquicia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToMany(mappedBy = "venta",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalleVenta;
    private Double precioTotal;
    private LocalDateTime fechaVenta;
    private String codigoVenta;
    
}
