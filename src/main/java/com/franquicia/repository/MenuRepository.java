package com.franquicia.repository;

import com.franquicia.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findProdById(Long id);

    List<Menu> findByActivo(boolean b);
}
