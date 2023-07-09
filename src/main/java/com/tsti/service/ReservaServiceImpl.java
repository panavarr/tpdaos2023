package com.tsti.service;

import com.tsti.persistence.entity.Asiento;
import com.tsti.persistence.entity.Cliente;
import com.tsti.persistence.entity.Reserva;
import com.tsti.persistence.entity.Vuelo;
import com.tsti.persistence.repository.AsientoDAO;
import com.tsti.persistence.repository.ClienteDAO;
import com.tsti.persistence.repository.ReservaDAO;
import com.tsti.persistence.repository.VueloDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {
    @Autowired
    private ReservaDAO reservaDAO;

    @Autowired
    private AsientoDAO asientoDAO;

    @Autowired
    private VueloDAO vueloDAO;

    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public Reserva crearNuevaReserva(Long v, Long c) throws Exception {

        // Pregunto si existe el destino (FUNCIONA)
        Vuelo vuelo = vueloDAO.findByNroVuelo(v);
        if (vuelo == null) {
            throw new Exception("El vuelo no existe");
        }

        // Pregunto si el vuelo ya partió (FUNCIONA)
        LocalDateTime fechaHoraVuelo = vuelo.getFechaHora();
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        if (fechaHoraActual.isAfter(fechaHoraVuelo)) {
            throw new Exception("El vuelo ya partió");
        }

        // Pregunto si existe el cliente (FUNCIONA)
        Cliente cliente = clienteDAO.findByDni(c);
        if (cliente == null) {
            throw new Exception("El cliente no existe");
        }

        // Pregunto si el vuelo es internacional (FUNCIONA)
        Boolean isInternacional = vuelo.getIsInternacional();
        System.out.println("isInternacional: " + isInternacional);
        /*if (isInternacional) {
            // Pregunto si el cliente tiene pasaporte
            if (cliente.getNumeroPasaporte() == null) {
                throw new Exception("El cliente no tiene pasaporte");
            }
        }*/
        /*System.out.println("Pre-ASIENTO ISSUE");
        // Pregunto si el asiento está disponible
        Asiento asientoReservado = asientoDAO.findByNroVuelo(vuelo.getNroVuelo());
        System.out.println("asientoReservado: " + asientoReservado);*/

        /*List<Asiento> asientosDisponibles = asientoDAO.findByDniOcupanteIsNull(
                vuelo.getNroVuelo()
        );
        System.out.println("asientosDisponibles: " + asientosDisponibles);
        if (asientosDisponibles.isEmpty()) {
            throw new Exception("No hay asientos disponibles");
        }

        // Están dadas todas las condiciones para efectuar la reserva
        Asiento asientoReservado = asientosDisponibles.get(0);
        asientoReservado.setDniOcupante(cliente);*/

        // Fijamos el precio de vuelo:
        // V. Nacional: Costo Pasaje + TAN + IVA
        // V. Internacional: Costo Pasaje * Cotización Dólar + TAI
        // La cotización del dólar es provista por el BCRA mediante una api REST.
        // La tasa aeroportuaria para vuelos internacionales y nacionales puede
        // ser consultada a Afip mediante una api rest.
        // La tasa de IVA es del 21%.
        // Una vez realizado el cobro, se confirmará la reserva
        Double precio = 100.0;
        Double TAN = 1.1;
        Double TAI = 1.2;
        Double IVA = 1.21;
        Integer cotizacionDolar = 500;
        if (isInternacional) {
            precio = precio * TAI * cotizacionDolar;
        } else {
            precio = precio * TAN * IVA;
        }
        System.out.println("precio: " + precio);
        // Reserva reserva = new Reserva();
        Reserva flamanteReserva = new Reserva(
                precio,
                false,
                cliente,
                vuelo,
                new Asiento()
        );
        System.out.println("flamanteReserva: " + flamanteReserva);
        return reservaDAO.save(flamanteReserva);
    }


    public void modificarReserva(Long Reserva) {

    }

    @Override
    public void modificarReserva(Long nroReserva, Reserva reserva) {
       /* Reserva reservaActualizada = reservaDAO.findById(nroReserva);*/

    }

    @Override
    public void cancelarReserva(Long nroReserva) {

    }

    @Override
    public List<Reserva> getAll() {
        return reservaDAO.findAll();
    }

    @Override
    public Reserva getByNroReserva(Long nroReserva) {
        return reservaDAO.findByNroReserva(nroReserva);
    }

    @Override
    public Reserva getReserva(Long nroVuelo, Long dniCliente) {
        return reservaDAO.findByNroVueloAndDni(nroVuelo, dniCliente);
    }

    @Override
    public Reserva confirmarPago(Long nroReserva) {
        Reserva reserva = reservaDAO.findByNroReserva(nroReserva);
        reserva.setConfirmada(true);
        reservaDAO.save(reserva);
        return reserva;
    }
}
