package com.citvet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.citvet.model.Usuario;
import com.citvet.repository.IUsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	IUsuarioRepository u;
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/")
	public String index() {
		return "inicio";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	

    @GetMapping("/registrarUser")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrarUser";
    }

    @PostMapping("/registrarUser")
    public String procesarFormularioRegistro(@RequestParam("nombre") String nombre,
    		@RequestParam("usuario") String usuario,@RequestParam(name = "password") String password) {
        // Encriptar la contraseña antes de guardarla
        
    	Usuario nuevo = new Usuario();
    	
    	String passwordEncriptado = passwordEncoder.encode(password);
        
    	nuevo.setNombre(nombre);
    	nuevo.setPassword(passwordEncriptado);
    	nuevo.setUsuario(usuario);
 
        u.save(nuevo);

        return "redirect:/registrarUser?registroExitoso";
    }



	@GetMapping("/home")
	public String home() {
		return "home";
	}
	


	
	
	
}
