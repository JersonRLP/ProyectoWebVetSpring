package com.citvet.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citvet.model.Cita;
import com.citvet.model.Cliente;
import com.citvet.model.Mascota;
import com.citvet.model.Pago;
import com.citvet.model.Servicio;
import com.citvet.model.Veterinario;
import com.citvet.repository.ICitaRepository;
import com.citvet.repository.IClienteRepository;
import com.citvet.repository.IMascotaRepository;
import com.citvet.repository.IPagoRepository;
import com.citvet.repository.IServicioRepository;
import com.citvet.repository.IVeterinarioRepository;

@Controller
public class TransaccionController {

	@Autowired
	ICitaRepository c;
	
	@Autowired
	IPagoRepository p;
	
	@Autowired
	IClienteRepository cli;
	
	@Autowired 
	IMascotaRepository m;
	
	@Autowired
	IServicioRepository s;
	
	@Autowired
	IVeterinarioRepository v;
	
	@GetMapping("/citas")
	public String cargarpag(Model model) {
		Cita cita = new Cita();
		model.addAttribute("cita",cita);
		model.addAttribute("listClientes",cli.findAll());
		model.addAttribute("listMascotas", m.findAll());
		model.addAttribute("listVet",v.findAll());
		model.addAttribute("listServ",s.findAll());
		
	
		return "citas";
	}
	
	@GetMapping("/listMascotas")
	  public ResponseEntity<List<String>> listMascotas(@RequestParam("cod_cliente") Integer codCliente) {
        List<Mascota> mascotas = m.nombredemascota(codCliente);
        List<String> nombresMascotas = mascotas.stream().map(Mascota::getNombre_mascota).collect(Collectors.toList());
        return ResponseEntity.ok(nombresMascotas);
    }
		
	@GetMapping("/ListadocodNombres")
	public ResponseEntity<List<Object[]>> listcodnom(@RequestParam("cod_cliente") Integer codCliente) {
	    List<Object[]> codigosYnombres = m.codigosYnombresDeMascotas(codCliente);
	    return ResponseEntity.ok(codigosYnombres);
	}
	
	
	@PostMapping("/savecita")
	public String registrarCita(@ModelAttribute Cita cita,@RequestParam("mascota.codMascota") Integer codMascota){    
		 Mascota mascota = m.findByCodMascota(codMascota);
		    cita.setMascota(mascota);
		
		c.save(cita);
		System.out.println("Se grabo correctamente");
		   
	    return "redirect:/citas";
	}
	
	@GetMapping("/vercita")
	public String vercita(Model model) {
	    List<Cita> citas = c.findAll();
	    List<Pago> pagos = p.findAll();
	    
	    Set<Integer> codigosCitasPagadas = pagos.stream()
	                                             .map(pago -> pago.getCita().getCodCita())
	                                             .collect(Collectors.toSet());

	    model.addAttribute("listCitas", citas);
	    model.addAttribute("codigosCitasPagadas", codigosCitasPagadas);
	    return "vercita";
	}

	

	
	
	@PostMapping("/SavePago")
	public ResponseEntity<String> registrarPago(
	        @ModelAttribute Pago pago,
	        @RequestParam("codigocita") int codigo,
	        @RequestParam("monto_total") double montoTotal,
	        @RequestParam("fecha_hora_pago") String fechaHoraPagoStr,
	        @RequestParam("tipo_comprobante") String tipoComprobante) {

	    // Convertir fechaHoraPagoStr a java.sql.Date manualmente
	    java.sql.Date fechaHoraPago = java.sql.Date.valueOf(fechaHoraPagoStr);

	    // Buscar la cita por su código
	    Cita codcita = c.findByCodCita(codigo);

	    // Verificar si el pago ya está registrado
	    Integer busca = p.buscar(codigo);
	    if (busca != null) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El Pago YA Esta REGISTRADO");
	    } else {
	        // Establecer los valores en el objeto Pago
	        pago.setCita(codcita);
	        pago.setMonto_total(montoTotal);
	        pago.setFecha_hora_pago(fechaHoraPago);
	        pago.setTipo_comprobante(tipoComprobante);

	        // Guardar el pago
	        p.save(pago);

	        System.out.println("EL PAGO " + pago);

	        return ResponseEntity.ok("Pago Registrado Correctamente");
	    }
	}



}
