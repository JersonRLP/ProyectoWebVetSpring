package com.citvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citvet.model.Mascota;
import com.citvet.repository.IClienteRepository;
import com.citvet.repository.IEspecieRepository;
import com.citvet.repository.IMascotaRepository;
import com.citvet.repository.IRazaRepository;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MascotaController {
	
	@Autowired
	private IMascotaRepository mascrepo;
	
	@Autowired
	private IClienteRepository clie;
	
	@Autowired
	private IEspecieRepository espe;
	
	@Autowired
	private IRazaRepository raza;
	

	@GetMapping("/mascota-listado")
	public String listadoMascota(Model model) {
		model.addAttribute("content", "mascota-listado");
		model.addAttribute("lstMascotas", mascrepo.findAll());
		return "layout";
	}
	
	@GetMapping("/mascota-nueva")
	public String cargarPag(Model model) {
		Mascota masc = new Mascota();
		model.addAttribute("content", "mascota-nueva");
		model.addAttribute("mascota", masc);
		model.addAttribute("lstClientes", clie.findAll());
		model.addAttribute("lstEspecies", espe.findAll());
		model.addAttribute("lstRazas", raza.findAll());
		return "layout";
	}
		
	@PostMapping("/grabarMascota")
	public String grabarPag(@ModelAttribute Mascota mascota, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes attribute) {
	    try {
	        mascota.setEstado("Activo");
	        Mascota savedMascota = mascrepo.save(mascota);
	        if (savedMascota != null) {
	            if (imagen != null && !imagen.isEmpty()) {
	                String extension = imagen.getOriginalFilename().substring(imagen.getOriginalFilename().lastIndexOf("."));
	                String nombreImagen = savedMascota.getCodMascota() + "_" + savedMascota.getNombre_mascota() + extension;
	                Path directorioImagenes = Paths.get("src/main/resources/static/assets/imagenes/mascotas");
	                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
	                byte[] bytesImg = imagen.getBytes();
	                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + nombreImagen);
	                Files.write(rutaCompleta, bytesImg);
	                savedMascota.setFoto_mascota(nombreImagen);
	                mascrepo.save(savedMascota);
	            }
	            attribute.addFlashAttribute("sucess", "Mascota registrada con éxito!");
	        } else {
	            attribute.addFlashAttribute("unsucess", "Error al registrar mascota!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        attribute.addFlashAttribute("unsucess", "Error al registrar mascota!");
	    }
	    return "redirect:/mascota-listado";
	}

	

	
	@PostMapping("/actualizarMascota")
	public String actualizarMascotas(@ModelAttribute Mascota mascota, RedirectAttributes attribute) {
	    if(mascrepo.save(mascota) != null) {
	        attribute.addFlashAttribute("success", "Mascota actualizada con éxito!");
	    } else {
	        attribute.addFlashAttribute("unsuccess", "Error al actualizar la mascota!");
	    }
	    return "redirect:/mascota-listado";
	}



	@PostMapping("/eliminar")
	public String eliminarPag(@ModelAttribute Mascota mascota, RedirectAttributes attribute) {
		Mascota masc = mascrepo.findByCodMascota(mascota.getCodMascota());
		mascrepo.delete(masc);
		attribute.addFlashAttribute("sucess","Mascota eliminada con éxito!");
		return "redirect:/mascota-listado";
	}

}
	