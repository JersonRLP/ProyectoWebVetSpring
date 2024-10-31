package com.citvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citvet.model.Cliente;
import com.citvet.model.Veterinario;
import com.citvet.repository.IVeterinarioRepository;
import com.citvet.repository.IDistritoRepository;

@Controller
public class VeterinarioController {
	
	@Autowired
	private IVeterinarioRepository vetrepo;
	
	@Autowired
	private IDistritoRepository disrepo;
	

	@GetMapping("/veterinario-listado")
	public String listadoVeterinarios(Model model) {
		model.addAttribute("content", "veterinario-listado");
		model.addAttribute("lstVeterinarios", vetrepo.findAll());
		return "layout";
	}
	
	@GetMapping("/veterinario-nuevo")
	public String cargarPag(Model model) {
		Veterinario veterinario = new Veterinario();
		model.addAttribute("content", "veterinario-nuevo");
		model.addAttribute("veterinario", veterinario);
		model.addAttribute("lstDistritos", disrepo.findAll());
		return "layout";
	}
		
	@PostMapping("/grabarVeterinario")
	public String grabarPag(@ModelAttribute Veterinario veterinario, RedirectAttributes attribute) {
		if(vetrepo.save(veterinario) != null) {
			attribute.addFlashAttribute("success", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsuccess", "Error registrando!");
		}
		return "redirect:/veterinario-listado";
	}
	
	@PostMapping("/actualizarVeterinario")
    public String actualizarVeterinarios(@ModelAttribute Veterinario veterinario, RedirectAttributes attribute) {
        if(vetrepo.save(veterinario) != null) {
            attribute.addFlashAttribute("success", "Veterinario actualizado con éxito!");
        } else {
            attribute.addFlashAttribute("unsuccess", "Error al actualizar el veterinario!");
        }
        return "redirect:/veterinario-listado";
    }
	
	@PostMapping("/eliminarVeterinario")
	public String eliminarPag(@ModelAttribute Veterinario veterinario, RedirectAttributes attribute) {
		Veterinario veterinarioToDelete = vetrepo.findByCodVeterinario(veterinario.getCodVeterinario());
		vetrepo.delete(veterinarioToDelete);
		attribute.addFlashAttribute("sucess","Veterinario eliminado con éxito!");
		return "redirect:/veterinario-listado";
	}

}
