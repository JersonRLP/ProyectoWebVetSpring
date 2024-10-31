package com.citvet.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citvet.model.Cita;

public interface ICitaRepository extends JpaRepository<Cita, Integer> {

	Cita findByCodCita(int cod_cita);



	@Query("SELECT MAX(c.codCita) FROM Cita c")
    Integer obtenerUltimoCodigoCita();
	
	@Query("SELECT con FROM Cita con WHERE con.vet.codVeterinario = :cod_veterinario AND con.fecha_cita = :fecha")
	List<Cita> listConsulta (@Param("cod_veterinario")Integer codigovet,@Param("fecha")Date fecha);
	
	
}
