package com.citvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citvet.model.Servicio;
import com.citvet.repository.IServicioRepository;
import com.citvet.repository.ITipoServicioRepository;

@Controller
public class ServicioController {
	
	@Autowired
	private IServicioRepository serrepo;
	
	@Autowired
	private ITipoServicioRepository tiprepo;
	

	@GetMapping("/servicio-listado")
	public String listadoServicios(Model model) {
		model.addAttribute("content", "servicio-listado");
		model.addAttribute("lstServicios", serrepo.findAll());
		return "layout";
	}
	
	@GetMapping("/servicio-nuevo")
	public String cargarPag(Model model) {
		Servicio servicio = new Servicio();
		model.addAttribute("content", "servicio-nuevo");
		model.addAttribute("servicio", servicio);
		model.addAttribute("lstTiposServicio", tiprepo.findAll());
		return "layout";
	}
		
	@PostMapping("/grabarServicio")
	public String grabarPag(@ModelAttribute Servicio servicio, RedirectAttributes attribute) {
		if(serrepo.save(servicio) != null) {
			attribute.addFlashAttribute("success", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error registrando!");
		}
		return "redirect:/servicio-listado";
	}
	
	@PostMapping("/actualizarServicio")
	public String actualizarServicios(@ModelAttribute Servicio servicio, RedirectAttributes attribute) {
	    if(serrepo.save(servicio) != null) {
	        attribute.addFlashAttribute("success", "Servicio actualizado con éxito!");
	    } else {
	        attribute.addFlashAttribute("unsuccess", "Error al actualizar el servicio!");
	    }
	    return "redirect:/servicio-listado";
	}

	@PostMapping("/eliminarServicio")
	public String eliminarPag(@ModelAttribute Servicio servicio, RedirectAttributes attribute) {
		Servicio servicioToDelete = serrepo.findByCodServicio(servicio.getCodServicio());
		serrepo.delete(servicioToDelete);
		attribute.addFlashAttribute("sucess","Servicio eliminado con éxito!");
		return "redirect:/servicio-listado";
	}

}
