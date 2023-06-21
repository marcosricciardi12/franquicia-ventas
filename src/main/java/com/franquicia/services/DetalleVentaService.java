package com.franquicia.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.franquicia.models.DetalleVenta;
import com.franquicia.repository.DetalleVentaRepository;
import com.franquicia.repository.VentaRepository;

@Service
@Transactional
public class DetalleVentaService {

	@Autowired
    private DetalleVentaRepository repositoryDetalleVenta;
	
	public List<DetalleVenta> detalle(LocalDateTime from, LocalDateTime to) {
		Instant instantfrom = from.atZone(ZoneId.systemDefault()).toInstant();
		Date datefrom = Date.from(instantfrom);
		Instant instantto = to.atZone(ZoneId.systemDefault()).toInstant();
		Date dateto = Date.from(instantto);
		return repositoryDetalleVenta.findByFechaVentaBetween(from, to);
	}
}
