package com.dam.accesodatos.tellonozal_veronica_ra3v3.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutas")
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String zona;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false, length = 15)
    private DiaSemana diaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;

    @Column(nullable = false)
    private Boolean activa = true;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asignacion> asignaciones = new ArrayList<>();

    public Ruta() {}

    public Ruta(Long id, String nombre, String zona, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFin, Boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.zona = zona;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.activa = activa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public DiaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(DiaSemana diaSemana) { this.diaSemana = diaSemana; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }
    public Boolean getActiva() { return activa; }
    public void setActiva(Boolean activa) { this.activa = activa; }
    public List<Asignacion> getAsignaciones() { return asignaciones; }
    public void setAsignaciones(List<Asignacion> asignaciones) { this.asignaciones = asignaciones; }
}
