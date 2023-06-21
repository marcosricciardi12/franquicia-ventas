package com.franquicia.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franquicia.models.Consulta;
import com.franquicia.models.Datos;
import com.franquicia.models.DetalleVenta;
import com.franquicia.models.DetalleVentaReporte;
import com.franquicia.models.FechaDatos;
import com.franquicia.models.Menu;
import com.franquicia.repository.DetalleVentaRepository;
import com.franquicia.repository.MenuRepository;

@Service
@Transactional
public class ReporteService {

	private List<DetalleVenta> detalleventa;
	private List<DetalleVentaReporte> detalleventareporte;
	private Datos datos;
	private Optional<Menu> menu;
	
	@Autowired
    private MenuRepository repositoryMenu;
	@Autowired
    private DetalleVentaRepository repositoryDetalleVenta;
	
	public List<DetalleVenta> detalle(Instant from, Instant to) {
		LocalDateTime fromldt = from.atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime toldt = to.atZone(ZoneId.systemDefault()).toLocalDateTime();
		return repositoryDetalleVenta.findByFechaVentaBetween(fromldt, toldt);
	}


	public Datos datosreporte(FechaDatos intervalo) {
		this.detalleventa = this.detalle(intervalo.getFechaInicio(), intervalo.getFechaFin());
		System.out.println("\n\nEnviando datos de reporte solicitado, del " +
							"servicio franquicia al servicio de reportes\n\n");
		//Preparo lista con los detalles, para enviar un Unico objeto con todos los detalles
		this.detalleventareporte = new ArrayList<>();
		for (DetalleVenta detalle : this.detalleventa) {
			Instant instantfechaventa = detalle.getFechaVenta().atZone(ZoneId.systemDefault()).toInstant();
			DetalleVentaReporte detallereporte = new DetalleVentaReporte();
			detallereporte.setFecha(instantfechaventa);
			detallereporte.setMenu(detalle.getMenu().getId());
			detallereporte.setPrecio(detalle.getPrecioUnitario());
			this.detalleventareporte.add(detallereporte);
			System.out.println("\nDETALLE REPORTE: " + detallereporte + " \n");
		}
		this.datos = new Datos();
		this.datos.setDetalleventa(this.detalleventareporte);
		System.out.println("Datos del reporte a enviar: \n\n" + this.datos + "\n\n");
		return this.datos;
	}
}
