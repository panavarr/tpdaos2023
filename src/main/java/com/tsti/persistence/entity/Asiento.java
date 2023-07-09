package com.tsti.persistence.entity;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "asiento")
public class Asiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nro_asiento")
    private Long nroAsiento;

    @Column(name = "nro_fila", nullable = false)
    private Integer nroFila;

    @Column(name = "nro_columna", nullable = false)
    private Integer nroColumna;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "nro_vuelo",
            referencedColumnName = "nro_vuelo",
            insertable = false,
            updatable = false
    )
    private Vuelo nroVuelo;

    @OneToOne
    @JsonIgnore
    @JoinColumn(
            name = "dni",
            referencedColumnName = "dni",
            insertable = false,
            updatable = false
    )
    private Cliente dniOcupante;

    public Asiento() {
        super();
    }

    public Asiento(
            Integer nroFila,
            Integer nroColumna,
            Vuelo nroVuelo,
            Cliente dniOcupante
    ) {
        this.nroFila = nroFila;
        this.nroColumna = nroColumna;
        this.nroVuelo = nroVuelo;
        this.dniOcupante = dniOcupante;
    }

    public void setDisponible(boolean disponible) {
    }

    public boolean isDisponible() {
        return this.dniOcupante == null;
    }

    public Long getNroAsiento() {
        return nroAsiento;
    }

    public void setNroAsiento(Long nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    public Integer getNroFila() {
        return nroFila;
    }

    public void setNroFila(Integer nroFila) {
        this.nroFila = nroFila;
    }

    public Integer getNroColumna() {
        return nroColumna;
    }

    public void setNroColumna(Integer nroColumna) {
        this.nroColumna = nroColumna;
    }

    public Vuelo getNroVuelo() {
        return nroVuelo;
    }

    public void setNroVuelo(Vuelo nroVuelo) {
        this.nroVuelo = nroVuelo;
    }

    public Cliente getDniOcupante() {
        return dniOcupante;
    }

    public void setDniOcupante(Cliente dniOcupante) {
        this.dniOcupante = dniOcupante;
    }
}

