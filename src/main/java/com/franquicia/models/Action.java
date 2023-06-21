package com.franquicia.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Action {
    private String accion;
    private List<Menu> menus;
    private Reporte reporte;
}
