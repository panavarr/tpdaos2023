package com.tsti.persistence.repository;

import com.tsti.persistence.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaDAO extends JpaRepository<Reserva, Long> {
    Reserva findByNroReserva(Long nroReserva);

    Reserva findByNroVueloAndDni(Long nroVuelo, Long dniCliente);

}
