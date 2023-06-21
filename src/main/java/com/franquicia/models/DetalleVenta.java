package com.franquicia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "menu_myid")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;
    private Double precioUnitario;
    private LocalDateTime fechaVenta;
}