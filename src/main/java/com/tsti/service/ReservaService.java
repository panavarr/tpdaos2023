package com.tsti.service;

import com.tsti.persistence.entity.Reserva;
import java.util.List;

public interface ReservaService {
    /**
     * Inserta una nueva reserva
     *
     * @param v Vuelo
     * @param c Cliente
     * @return
     * @throws Exception excepción
     */
    public Reserva crearNuevaReserva(Long v, Long c) throws Exception;

    /**
     * Actualiza la fecha y hora de una reserva
     * @param Reserva Reserva
     */
    public void modificarReserva(Long Reserva, Reserva reserva);

    /**
     * Elimina una reserva
     * @param nroReserva Número de reserva
     */
    public void cancelarReserva(Long nroReserva);

    /**
     * Devuelve la lista completa de reservas
     * @return List<Reserva>
     */
    public List<Reserva> getAll();

    /**
     * Obtiene los datos de una reserva a partir del nro de reserva
     * @param nroReserva Número de reserva
     * @return Reserva
     */
    public Reserva getByNroReserva(Long nroReserva);

    /**
     * Obtiene los datos de una reserva a partir del nro de vuelo y dni del cliente
     * @param nroVuelo Número de vuelo
     * @param dniCliente DNI del cliente
     * @return Reserva
     */
    public Reserva getReserva(Long nroVuelo, Long dniCliente);

    /**
     * Confirma el pago de una reserva
     * @param nroReserva Número de reserva
     * @return Reserva confirmada o Error
     */
    public Reserva confirmarPago(Long nroReserva);
}
