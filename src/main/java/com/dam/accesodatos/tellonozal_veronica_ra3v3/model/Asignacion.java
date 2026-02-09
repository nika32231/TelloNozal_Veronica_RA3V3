package com.dam.accesodatos.tellonozal_veronica_ra3v3.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "asignaciones")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "camion_id", nullable = false)
    private Camion camion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ruta_id", nullable = false)
    private Ruta ruta;

    @Column(name = "fecha_asignacion", nullable = false)
    private LocalDate fechaAsignacion = LocalDate.now();

    public Asignacion() {}

    public Asignacion(Long id, Camion camion, Ruta ruta, LocalDate fechaAsignacion) {
        this.id = id;
        this.camion = camion;
        this.ruta = ruta;
        this.fechaAsignacion = fechaAsignacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Camion getCamion() { return camion; }
    public void setCamion(Camion camion) { this.camion = camion; }
    public Ruta getRuta() { return ruta; }
    public void setRuta(Ruta ruta) { this.ruta = ruta; }
    public LocalDate getFechaAsignacion() { return fechaAsignacion; }
    public void setFechaAsignacion(LocalDate fechaAsignacion) { this.fechaAsignacion = fechaAsignacion; }
}
