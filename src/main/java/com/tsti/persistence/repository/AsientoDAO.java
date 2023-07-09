package com.tsti.persistence.repository;

import com.tsti.persistence.entity.Asiento;
import com.tsti.persistence.entity.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AsientoDAO extends JpaRepository<Asiento, Long> {

    @Query("SELECT a FROM Asiento a WHERE a.nroVuelo = ?1")
    Asiento findByNroVuelo(Long nroVuelo);

    @Query("SELECT a FROM Asiento a WHERE a.dniOcupante IS NULL AND a.nroVuelo = ?1")
    List<Asiento> findByDniOcupanteIsNull(Long nroVuelo);

}
