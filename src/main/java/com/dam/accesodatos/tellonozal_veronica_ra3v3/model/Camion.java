package com.dam.accesodatos.tellonozal_veronica_ra3v3.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "camiones")
public class Camion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // matricula, unico para cada camion
    @Column(unique = true, nullable = false, length = 10)
    private String matricula;

    // modelo de cada camion
    @Column(nullable = false, length = 100)
    private String modelo;

    // Capacidad kg (DECIMAL 10,2)
    @Column(name = "capacidad_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacidadKg;

    // Estado de Camion: DISPONIBLE, EN_RUTA, MANTENIMIENTO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoCamion estado;

    // Fecha de alta
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;

    // Estado activo/inactivo (soft delete)
    @Column(nullable = false)
    private Boolean activo = true;

    // Relacion: un camión tiene muchas asignaciones
    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Asignacion.class)
    private Set<Asignacion> asignaciones = new LinkedHashSet<>();

    public Camion() {
    }

    public Camion(Long id, String matricula, String modelo, BigDecimal capacidadKg, EstadoCamion estado, Boolean activo, LocalDate fechaAlta) {
        this.id = id;
        this.matricula = matricula;
        this.modelo = modelo;
        this.capacidadKg = capacidadKg;
        this.estado = estado;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
    }

    public static CamionBuilder builder() {
        return new CamionBuilder();
    }

    @PrePersist
    protected void onCreate() {
        if (fechaAlta == null) {
            fechaAlta = LocalDate.now();
        }
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public BigDecimal getCapacidadKg() { return capacidadKg; }
    public void setCapacidadKg(BigDecimal capacidadKg) { this.capacidadKg = capacidadKg; }
    public EstadoCamion getEstado() { return estado; }
    public void setEstado(EstadoCamion estado) { this.estado = estado; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }
    public Set<Asignacion> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(Set<Asignacion> asignaciones) { this.asignaciones = asignaciones; }

    @Override
    public String toString() {
        return "Camion{" +
                "id=" + id +
                ", matricula='" + matricula + '\'' +
                ", modelo='" + modelo + '\'' +
                ", capacidadKg=" + capacidadKg +
                ", estado=" + estado +
                ", activo=" + activo +
                ", fechaAlta=" + fechaAlta +
                '}';
    }

    public static class CamionBuilder {
        private Long id;
        private String matricula;
        private String modelo;
        private BigDecimal capacidadKg;
        private EstadoCamion estado;
        private Boolean activo = true;
        private LocalDate fechaAlta;

        CamionBuilder() {}

        public CamionBuilder id(Long id) { this.id = id; return this; }
        public CamionBuilder matricula(String matricula) { this.matricula = matricula; return this; }
        public CamionBuilder modelo(String modelo) { this.modelo = modelo; return this; }
        public CamionBuilder capacidadKg(BigDecimal capacidadKg) { this.capacidadKg = capacidadKg; return this; }
        public CamionBuilder estado(EstadoCamion estado) { this.estado = estado; return this; }
        public CamionBuilder activo(Boolean activo) { this.activo = activo; return this; }
        public CamionBuilder fechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; return this; }

        public Camion build() {
            return new Camion(id, matricula, modelo, capacidadKg, estado, activo, fechaAlta);
        }
    }
}