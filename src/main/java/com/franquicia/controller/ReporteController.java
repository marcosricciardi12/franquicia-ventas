package com.franquicia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.franquicia.models.Datos;
import com.franquicia.models.FechaDatos;
import com.franquicia.services.ReporteService;

@RestController
@RequestMapping(("/api/reporte"))
public class ReporteController {
	
	 @Autowired
	    private ReporteService service;

	@PostMapping("/")
    public ResponseEntity<Datos> newVenta(@RequestBody FechaDatos intervalo){
        return new ResponseEntity<Datos>(service.datosreporte(intervalo), HttpStatus.OK);
    }
}
