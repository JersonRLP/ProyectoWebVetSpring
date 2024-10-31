package com.citvet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.citvet.model.Cliente;
import com.citvet.model.Mascota;

public interface IMascotaRepository extends JpaRepository<Mascota, Integer> {
	
	Mascota findByCodMascota(int cod_mascota);
	
	@Query("SELECT m FROM Mascota m WHERE m.cliente.codCliente = :cod_cliente")
    List<Mascota> nombredemascota(@Param("cod_cliente") Integer codigo);
	
	@Query("SELECT m.codMascota, m.nombre_mascota FROM Mascota m WHERE m.cliente.codCliente = :cod_cliente")
	List<Object[]> codigosYnombresDeMascotas(@Param("cod_cliente") Integer codigo);

}
