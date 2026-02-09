package com.dam.accesodatos.tellonozal_veronica_ra3v3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Asignacion;
import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {

    List<Asignacion> findByCamionId(Long camionId);

    List<Asignacion> findByRutaId(Long rutaId);
}
