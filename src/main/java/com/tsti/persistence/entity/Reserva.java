package com.tsti.persistence.entity;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @Column(name = "nro_reserva")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroReserva;

    @Column(nullable = false)
    private Double precio;

    private Boolean confirmada;

    @OneToOne
    @JsonIgnore
    @JoinColumn(
            name = "dni",
            referencedColumnName = "dni"
    )
    private Cliente dni;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "nro_vuelo",
            referencedColumnName = "nro_vuelo",
            nullable = false
    )
    private Vuelo nroVuelo;

    @OneToOne
    @JsonIgnore
    @JoinColumn(
            name = "nro_asiento",
            referencedColumnName = "nro_asiento",
            nullable = false
    )
    private Asiento nroAsiento;

    public Reserva() {
        super();
    }

    public Reserva(
            Double precio,
            Boolean confirmada,
            Cliente dni,
            Vuelo nroVuelo,
            Asiento nroAsiento
    ) {
        this.precio = precio;
        this.confirmada = confirmada;
        this.dni = dni;
        this.nroVuelo = nroVuelo;
        this.nroAsiento = nroAsiento;
    }

    public Long getNroReserva() {
        return nroReserva;
    }

    public void setNroReserva(Long nroReserva) {
        this.nroReserva = nroReserva;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Cliente getDni() {
        return dni;
    }

    public void setDni(Cliente dni) {
        this.dni = dni;
    }

    public Vuelo getNroVuelo() {
        return nroVuelo;
    }

    public void setNroVuelo(Vuelo nroVuelo) {
        this.nroVuelo = nroVuelo;
    }

    public Asiento getNroAsiento() {
        return nroAsiento;
    }

    public void setNroAsiento(Asiento nroAsiento) {
        this.nroAsiento = nroAsiento;
    }

    public Boolean getConfirmada() {
        return confirmada;
    }

    public void setConfirmada(Boolean confirmada) {
        this.confirmada = confirmada;
    }
}
