package com.franquicia.models;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultadoVenta {
	private Venta venta;
	private List<DetalleVenta> detalle;

}
