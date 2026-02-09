package com.dam.accesodatos.tellonozal_veronica_ra3v3.repository;

import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Camion;
import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.EstadoCamion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

    // Camion por rol
    List<Camion> findByEstado(EstadoCamion estado);

    // Solo Camions activos
    List<Camion> findByActivoTrue();

}
