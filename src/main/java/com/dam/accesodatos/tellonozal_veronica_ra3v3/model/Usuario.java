package com.dam.accesodatos.tellonozal_veronica_ra3v3.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identificador de inicio de sesión único
    @Column(unique = true, nullable = false, length = 50)
    private String username;

    // Email único para contacto/alternativa de login
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    // Hash de la contraseña (BCrypt u otro)
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    // Nombre legible del usuario
    @Column(nullable = false, length = 100)
    private String nombre;

    // Rol del usuario: ADMIN, Coordinador
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Rol rol;

    // EstadoCamion activo/inactivo (soft delete)
    @Column(nullable = false)
    private Boolean activo = true;

    // Fecha/hora de creación
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Usuario() {
    }

    public Usuario(Long id, String username, String email, String passwordHash, String nombre, Rol rol, Boolean activo, LocalDateTime fechaCreacion) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.rol = rol;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
    }

    public static UsuarioBuilder builder() {
        return new UsuarioBuilder();
    }

    // Inicializa la marca de tiempo al crear
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }

    // Metodo auxiliar para verificar contraseña con un PasswordEncoder
    public boolean verificarPassword(String password, org.springframework.security.crypto.password.PasswordEncoder encoder) {
        return encoder.matches(password, this.passwordHash);
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                ", activo=" + activo +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    public static class UsuarioBuilder {
        private Long id;
        private String username;
        private String email;
        private String passwordHash;
        private String nombre;
        private Rol rol;
        private Boolean activo = true;
        private LocalDateTime fechaCreacion;


        UsuarioBuilder() {}

        public UsuarioBuilder id(Long id) { this.id = id; return this; }
        public UsuarioBuilder username(String username) { this.username = username; return this; }
        public UsuarioBuilder email(String email) { this.email = email; return this; }
        public UsuarioBuilder passwordHash(String passwordHash) { this.passwordHash = passwordHash; return this; }
        public UsuarioBuilder nombre(String nombre) { this.nombre = nombre; return this; }
        public UsuarioBuilder rol(Rol rol) { this.rol = rol; return this; }
        public UsuarioBuilder activo(Boolean activo) { this.activo = activo; return this; }
        public UsuarioBuilder fechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; return this; }

        public Usuario build() {
            return new Usuario(id, username, email, passwordHash, nombre, rol, activo, fechaCreacion);
        }
    }
}
