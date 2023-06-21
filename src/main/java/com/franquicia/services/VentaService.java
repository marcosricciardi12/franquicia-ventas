package com.franquicia.services;

import com.franquicia.models.DetalleVenta;
import com.franquicia.models.Menu;
import com.franquicia.models.NuevaVenta;
import com.franquicia.models.ResultadoVenta;
import com.franquicia.models.Venta;
import com.franquicia.repository.DetalleVentaRepository;
import com.franquicia.repository.MenuRepository;
import com.franquicia.repository.VentaRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VentaService {

    @Autowired
    private VentaRepository repositoryVenta;
    @Autowired
    private MenuRepository repositoryMenu;
    @Autowired
    private DetalleVentaRepository repositoryDetalleVenta;

    private ResultadoVenta resultado;
    private Venta ventaActual;
    private DetalleVenta detalle;
    private List<DetalleVenta> listaDetalles;
    private Optional<Menu> menu;
    private Double preciototal;

    private String codigoVenta;

    public ResultadoVenta newVenta(List<NuevaVenta> venta) {
    	System.out.println("\nPROCESANDO NUEVA VENTA \n");
    	this.resultado = new ResultadoVenta();
    	this.ventaActual = new Venta();
    	
    	this.listaDetalles = new ArrayList<DetalleVenta>();
    	this.preciototal = 0.0;
    	this.ventaActual.setPrecioTotal(0.0);
    	
    	Instant fecha_actual_general = Instant.now();
		ZoneId zoneId = ZoneId.of("America/Argentina/Buenos_Aires");
		//System.out.println("ZONA HORARIO ELEGIDA: " + zoneId);
		ZonedDateTime fecha_actual_zoneARG = ZonedDateTime.ofInstant(fecha_actual_general, zoneId);
		LocalDateTime fecha_actual_arg = fecha_actual_zoneARG.toLocalDateTime();
		
    	
    	this.ventaActual.setFechaVenta(fecha_actual_arg);
        repositoryVenta.save(ventaActual);
        this.ventaActual = repositoryVenta.findTopByOrderByIdDesc().get(0);
        this.codigoVenta = "0000000000000" + Long.toString(this.ventaActual.getId());
        this.ventaActual.setCodigoVenta(this.codigoVenta.substring(codigoVenta.length() - 4));
        for (NuevaVenta detalleVenta : venta) {
        	this.menu = repositoryMenu.findById(detalleVenta.getId_menu());
        	//System.out.println(this.menu);
        	for(Integer i=0;i<detalleVenta.getCantidad();i++) {
        		this.detalle = new DetalleVenta();
        		this.detalle.setVenta(this.ventaActual);
        		this.detalle.setMenu(this.menu.orElse(null));
        		this.detalle.setPrecioUnitario(this.menu.get().getPrecio());
        		this.preciototal = this.preciototal + this.menu.get().getPrecio();
        		this.detalle.setFechaVenta(fecha_actual_arg);
        		this.listaDetalles.add(this.detalle);
        		//System.out.println(this.detalle);
        		repositoryDetalleVenta.save(this.detalle);
        	}
        }
        this.ventaActual.setPrecioTotal(this.preciototal);
        repositoryVenta.save(ventaActual);
        this.resultado.setVenta(this.ventaActual);
        this.resultado.setDetalle(this.listaDetalles);
        System.out.println("\nResultado venta: \n" + 
        					"\n\tVenta ID:" + resultado.getVenta().getId() +
        					"\n\tPrecio total: " + resultado.getVenta().getPrecioTotal() +
        					"\n\tFecha Venta: " + resultado.getVenta().getFechaVenta() +
        					"\n\tDetalle venta: " + resultado.getDetalle() +
        					"\n");
        return resultado;
    }
    //return objeto de la clase ResultadoVenta
}
