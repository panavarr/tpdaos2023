package com.tsti.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tsti.persistence.entity.Ciudad;

public interface CiudadDAO extends JpaRepository<Ciudad, Long>{

}
