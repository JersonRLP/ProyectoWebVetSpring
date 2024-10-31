package com.citvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citvet.model.Servicio;

public interface IServicioRepository extends JpaRepository<Servicio, Integer> {

		Servicio findByCodServicio(int codservicio);
}
