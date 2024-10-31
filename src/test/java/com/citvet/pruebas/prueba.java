package com.citvet.pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.citvet.model.Cita;
import com.citvet.model.Cliente;
import com.citvet.model.Mascota;

import com.citvet.model.Veterinario;
import com.citvet.repository.ICitaRepository;
import com.citvet.repository.IClienteRepository;
import com.citvet.repository.IMascotaRepository;
import com.citvet.repository.IPagoRepository;
import com.citvet.repository.IVeterinarioRepository;
	

@SpringBootTest
@Transactional
public class prueba {

	@Autowired
	private IVeterinarioRepository v;
	
	@Autowired
	private ICitaRepository c;
	
	@Autowired
	private IClienteRepository cli;
	
	@Autowired
	private IMascotaRepository m;
	
	@Autowired
	private IPagoRepository p;
	
	private static final Logger logger = LoggerFactory.getLogger(prueba.class);
	  @Test
	    public void testObtenerUltimoCodigoCita() {
	        // Llamar al método personalizado y verificar el resultado
	        Integer ultimoCodigoCita = c.obtenerUltimoCodigoCita();

	        // Realizar afirmaciones (assertions) para verificar el resultado
	        assertNotNull(ultimoCodigoCita); // Verificar que el resultado no sea nulo
	        assertEquals(Integer.valueOf(6), ultimoCodigoCita); // Cambia el valor esperado según tu lógica de negocio
	        logger.info("Resultado de obtenerUltimoCodigoCita: " + ultimoCodigoCita); // Imprimir el resultado en el registro de Spring Boot
	       
	    }
	  
	  @Test
	  public void test2() {
		   Cliente cliente = cli.findByCodCliente(3); // Obtén el cliente por su código
		    List<Mascota> listMascotas = m.nombredemascota(cliente.getCodCliente()); // Obtén la lista de mascotas del cliente

		    assertNotNull(listMascotas); // Verificar que la lista no sea nula

		    // Imprimir la lista de mascotas en el registro de Spring Boot
		    for (Mascota mascota : listMascotas) {
		        logger.info("Mascota: " + mascota.getNombre_mascota()); // Cambia "getNombre()" por el método adecuado para obtener el nombre de la mascota
		    }
	  }
	  
	  @Test
	  public void test4() {
	      Cliente cliente = cli.findByCodCliente(3); // Obtén el cliente por su código
	      List<Object[]> codigosYnombresDeMascotas = m.codigosYnombresDeMascotas(cliente.getCodCliente());

	      assertNotNull(codigosYnombresDeMascotas); // Verificar que la lista no sea nula

	      // Imprimir la lista de mascotas en el registro de Spring Boot
	      for (Object[] result : codigosYnombresDeMascotas) {
	          Integer codMascota = (Integer) result[0];
	          String nombreMascota = (String) result[1];
	          logger.info("Código de Mascota: " + codMascota);
	          logger.info("Nombre de Mascota: " + nombreMascota);
	      }
	  }

	  
	  @Test
	  public void test3() {
	      Integer codigoCita = p.buscar(3);
	      assertNotNull(codigoCita); // Verificar que el resultado no sea nulo
	      logger.info("Código de la cita es : " + codigoCita); // Imprimir el resultado en el registro de Spring Boot
	  }
	  
	  @Test
	  public void test5() {
		  
		  
		  Veterinario vet = v.findByCodVeterinario(1);
	

		  java.sql.Date date = java.sql.Date.valueOf("2023-11-08");
		
	
		  
		  List<Cita> listado = c.listConsulta(vet.getCodVeterinario(), date);
		  
		  assertNotNull(listado); // Verificar que la lista no sea nula
		   for (Cita cita :listado ) {
		        logger.info("Nombres: " + cita.getCliente().getNombres());
		        logger.info("Mascota: " + cita.getMascota().getNombre_mascota());
		        logger.info("fecha : " + cita.getFecha_cita());
		        logger.info("Hora : " + cita.getHora_cita()); // Cambia "getNombre()" por el método adecuado para obtener el nombre de la mascota
		    }
		  
	  }

	  
	  @Test
	  public void test6() {
	      java.sql.Date date = java.sql.Date.valueOf("2023-11-08");
	      List<Object[]> resultList = p.constotal(date);

	      assertNotNull(resultList); // Verificar que la lista no sea nula
	      for (Object[] result : resultList) {
	          Date fechaPago = (Date) result[0];
	          Double totalMonto = (Double) result[1];

	          logger.info("Fecha de Pago: " + fechaPago);
	          logger.info("Total Monto: " + totalMonto);
	      }
	  }

	
}
