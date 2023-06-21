package com.franquicia.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.franquicia.models.DetalleVenta;
import com.franquicia.services.DetalleVentaService;

@RestController
@RequestMapping(("/api/detalleventa"))
public class DetalleVentaController {
	
	@Autowired
    private DetalleVentaService serviceDetalleVenta;
	
	@GetMapping("/")
	public ResponseEntity<List<DetalleVenta>> findByDateBetween(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
		return new ResponseEntity<List<DetalleVenta>>(serviceDetalleVenta.detalle(from, to) , HttpStatus.OK);
	}

}
