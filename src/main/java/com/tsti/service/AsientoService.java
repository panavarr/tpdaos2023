package com.tsti.service;

public interface AsientoService {
    public void reservarAsiento(Long idAsiento, Long idReserva, Long dni);
    public void liberarAsiento(Long idAsiento, Long idReserva);
    public void bloquearAsiento(Long idAsiento, Long idReserva, Long dni);
    public void desbloquearAsiento(Long idAsiento, Long idReserva);
}
