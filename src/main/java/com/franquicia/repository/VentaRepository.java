package com.franquicia.repository;

import com.franquicia.models.Venta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
	List<Venta> findTopByOrderByIdDesc();
}
