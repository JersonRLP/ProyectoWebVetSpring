package com.citvet.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citvet.model.Cita;
import com.citvet.model.Pago;
import com.citvet.model.Veterinario;
import com.citvet.repository.ICitaRepository;
import com.citvet.repository.IPagoRepository;
import com.citvet.repository.IVeterinarioRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Controller
public class ConsultasController {

	@Autowired
	IPagoRepository p;
	
	@Autowired
	ICitaRepository c;

	@Autowired
	IVeterinarioRepository v;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/consultas")
	public String cargarpag(Model model) {
		List<Veterinario> listVet = v.findAll();
		model.addAttribute("listVet",listVet);
		model.addAttribute("listPagos", p.findAll());
		
		 // Verificación de consultas (si aplicable, depende de cómo manejas las consultas)
	    boolean noHayConsultas = true; // Cambia esto según tu lógica de consulta
	    model.addAttribute("noHayConsultas", noHayConsultas);
		return "consultascitas";
	}
	
	
	@GetMapping("/consulta1")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listadodecitas(@RequestParam("codVeterinario")Integer codigo,
							@RequestParam("fecha_cita")@DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha,Model model){
		Map<String, Object> response = new HashMap<>();
		List<Veterinario> listVet = v.findAll();
		
		
		List<Cita> cons = c.listConsulta(codigo, fecha);
		response.put("cons", cons);
		
	    for (Cita consulta : cons) {
	        System.out.println("Cliente : " + consulta.getCliente().getNombres());
	        System.out.println("Mascota : " + consulta.getMascota().getNombre_mascota());
	        System.out.println("fecha : " + consulta.getFecha_cita());
	        System.out.println("Hora : " + consulta.getHora_cita());
	        System.out.println("----");
	    }
	    
	    
	   
	    model.addAttribute("listVet",listVet);
	    model.addAttribute("listPagos", p.findAll());
	    return ResponseEntity.ok(response);
	    	
		
	}
	
	
	@GetMapping("/consulta2")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> listadostotal(Model model, @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
		Map<String, Object> response = new HashMap<>();
		
		List<Veterinario> listVet = v.findAll();
		
	    List<Object[]> cons2 = p.constotal(fecha);
	    
	    response.put("cons2", cons2);

	    for (Object[] resultado : cons2) {
	        Date fechaPago = (Date) resultado[0];
	        Double totalMonto = (Double) resultado[1];
	        
	        System.out.println("Fecha de Pago: " + fechaPago);
	        System.out.println("Total Monto: " + totalMonto);
	    }
	    
	    
	    model.addAttribute("listVet",listVet);
	    model.addAttribute("listPagos", p.findAll());
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping(value="/consultas", params="exportar")
	public void exportar(HttpServletResponse response) throws JRException, SQLException {
	String nombreReporte = "ListadoPagos";
	response.setHeader("Content-Disposition","inline;filename" + nombreReporte + ".pdf");
	
	response.setContentType("application/pdf");
	
		try {
			String ru =resourceLoader.getResource("classpath:Reportes/ListadoPagos.jasper").getURI().getPath();
			HashMap<String,Object> listado = new HashMap<>();
			List<Pago> listPagos = p.findAll();
			
			listado.put("listPagos", listPagos);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, listado,dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
			
			
		} catch (JRException | IOException e) {
			
			e.printStackTrace();
			
		}
		
	
	}
	
	
	
	@PostMapping(value="/consultas", params="graficar")
	public void graficar(HttpServletResponse response) throws JRException, SQLException {
	String nombreReporte = "ListadoPagos";
	response.setHeader("Content-Disposition","inline;filename" + nombreReporte + ".pdf");
	
	response.setContentType("application/pdf");
	
		try {
			String ru =resourceLoader.getResource("classpath:Reportes/GraficosPagos.jasper").getURI().getPath();
			HashMap<String,Object> listado = new HashMap<>();
			List<Pago> listPagos = p.findAll();
			
			listado.put("listPagos", listPagos);
			JasperPrint jasperPrint = JasperFillManager.fillReport(ru, listado,dataSource.getConnection());
			OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		
			
			
		} catch (JRException | IOException e) {
			
			e.printStackTrace();
			
		}
		
	
	}
	
}
