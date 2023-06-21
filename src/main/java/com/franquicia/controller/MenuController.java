package com.franquicia.controller;

import com.franquicia.models.Menu;
import com.franquicia.models.NuevaVenta;
import com.franquicia.models.ResultadoVenta;
import com.franquicia.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(("/api/menu"))
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/")
    public ResponseEntity<List<Menu>> menuActivo(){
        return new ResponseEntity<List<Menu>>(menuService.findByEstado(), HttpStatus.OK);
    }
}
