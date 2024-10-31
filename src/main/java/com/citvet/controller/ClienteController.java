package com.citvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citvet.model.Cliente;
import com.citvet.model.Distrito;
import com.citvet.repository.IClienteRepository;
import com.citvet.repository.IDistritoRepository;

@Controller
public class ClienteController {
	
	@Autowired
	private IClienteRepository clirepo;
	
	@Autowired
	private IDistritoRepository disrepo;
	

	@GetMapping("/cliente-listado")
	public String listadoClientes(Model model) {
	    model.addAttribute("content", "cliente-listado");      
	    model.addAttribute("lstClientes", clirepo.findAll());
	    return "layout";
	}

	
	@GetMapping("/cliente-nuevo")
	public String cargarPag(Model model) {
	    Cliente cliente = new Cliente();
	    model.addAttribute("content", "cliente-nuevo");
	    model.addAttribute("cliente", cliente);
	    model.addAttribute("lstDistritos", disrepo.findAll());
	    return "layout";
	}

		
	@PostMapping("/grabarCliente")
	public String grabarPag(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {
		if(clirepo.save(cliente) != null) {
			attribute.addFlashAttribute("sucess", "Registrado con éxito!");
		}else {
			attribute.addFlashAttribute("unsucess", "Error registrando!");
		}
		return "redirect:/cliente-listado";
	}
	
	@PostMapping("/actualizarCliente")
    public String actualizarClientes(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {
        if(clirepo.save(cliente) != null) {
            attribute.addFlashAttribute("sucess", "Cliente actualizado con éxito!");
        } else {
            attribute.addFlashAttribute("unsucess", "Error al actualizar el cliente!");
        }
        return "redirect:/cliente-listado";
    }
	
		
	@PostMapping("/eliminarCliente")
	public String eliminarPag(@ModelAttribute Cliente cliente, RedirectAttributes attribute) {
		Cliente cli = clirepo.findByCodCliente(cliente.getCodCliente());
		clirepo.delete(cli);
		attribute.addFlashAttribute("sucess","Cliente eliminado con éxito!");
		return "redirect:/cliente-listado";
	}

}
