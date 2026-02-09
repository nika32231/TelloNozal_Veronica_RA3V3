package com.dam.accesodatos.tellonozal_veronica_ra3v3.repository;

import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Usuario;
import com.dam.accesodatos.tellonozal_veronica_ra3v3.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca por username (login)
    Optional<Usuario> findByUsername(String username);

    // Busca por email
    Optional<Usuario> findByEmail(String email);

    // Usuarios por rol
    List<Usuario> findByRol(Rol rol);

    // Solo usuarios activos
    List<Usuario> findByActivoTrue();

    // coordinadores (para asignaciones)
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'COORDINADOR' AND u.activo = true")
    List<Usuario> findCoordinadoresActivos();

    // Validaciones de unicidad
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Búsqueda por nombre parcial (case-insensitive)
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
}
