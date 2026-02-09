package com.dam.accesodatos.tellonozal_veronica_ra3v3.repository;

import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

    List<Ruta> findByActivaTrue();

    List<Ruta> findByZona(String zona);
}
