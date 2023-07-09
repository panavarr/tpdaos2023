package com.tsti.service;

import org.springframework.beans.factory.annotation.Autowired;

public class AsientoServiceImpl implements AsientoService{
    @Autowired
    private AsientoService dao;

    @Override
    public void reservarAsiento(Long idAsiento, Long idReserva, Long dni) {


    }

    @Override
    public void liberarAsiento(Long idAsiento, Long idReserva) {

    }

    @Override
    public void bloquearAsiento(Long idAsiento, Long idReserva, Long dni) {

    }

    @Override
    public void desbloquearAsiento(Long idAsiento, Long idReserva) {

    }
}
