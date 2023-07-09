package com.tsti.web.controller;

import com.tsti.persistence.entity.Reserva;
import com.tsti.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaRestController {

    @Autowired
    private ReservaService reservaService;

    /**
     * Devuelve la lista completa de reservas
     *
     * @return List<Reserva> reservas
     */
    @GetMapping("/todas")
    public ResponseEntity<List<Reserva>> getAll() {
        return new ResponseEntity<>(reservaService.getAll(), HttpStatus.OK);
    }

    /**
     * Obtiene una reserva por su número de reserva
     *
     * @param nroReserva Número de reserva
     * @return Reserva o not found
     */
    @GetMapping("/{nroReserva}")
    public ResponseEntity<Reserva> getReserva(
            @PathVariable("nroReserva") Long nroReserva
    ) {
        Reserva reserva = reservaService.getByNroReserva(nroReserva);
        if (reserva != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Crea una nueva reserva
     *
     * @param nroVuelo   Número de vuelo
     * @param dniCliente DNI del cliente
     * @return Reserva creada o Error
     */
    @PostMapping("/crear-nueva/{nroVuelo}/{dniCliente}")
    public ResponseEntity<Reserva> crearNuevaReserva(
            @PathVariable("nroVuelo") Long nroVuelo,
            @PathVariable("dniCliente") Long dniCliente
    ) {
        try {
            Reserva reserva = reservaService.crearNuevaReserva(nroVuelo, dniCliente);
            return new ResponseEntity<>(reserva, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Confirma el pago de una reserva
     *
     * @param nroReserva Número de reserva
     * @return Reserva con pago confirmado o Error
     */
    @PutMapping("/confirmar-pago/{nroReserva}")
    public ResponseEntity<Reserva> confirmarPago(
            @PathVariable("nroReserva") Long nroReserva
    ) {
        Reserva reserva = reservaService.confirmarPago(nroReserva);
        if (reserva != null) {
            return new ResponseEntity<>(reserva, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Cancela una reserva
     *
     * @param nroReserva Número de reserva
     * @return Reserva cancelada o Error
     */
    @PutMapping("/cancelar/{nroReserva}")
    public ResponseEntity<Void> cancelarReserva(
            @PathVariable("nroReserva") Long nroReserva
    ) {
        reservaService.cancelarReserva(nroReserva);
        if (reservaService.getByNroReserva(nroReserva) == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Modifica una reserva existente a partir de su número de reserva
     *
     * @param nroReserva Número de reserva
     * @param reserva    Reserva modificada
     * @return Reserva modificada o Error
     */
    @PutMapping("/modificar/{nroReserva}")
    public ResponseEntity<Reserva> modificarReserva(
            @PathVariable("nroReserva") Long nroReserva,
            @RequestBody Reserva reserva
    ) {
        try {
            reservaService.modificarReserva(nroReserva, reserva);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
