package com.franquicia.services;

import com.franquicia.models.Menu;
import com.franquicia.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;
    public List<Menu> findByEstado() {
        return menuRepository.findByActivo(true);
    }
}
