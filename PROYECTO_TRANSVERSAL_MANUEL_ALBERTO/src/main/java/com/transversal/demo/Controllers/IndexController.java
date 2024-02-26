package com.transversal.demo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
//MOSTRAR LAS VISTAS
	
	@GetMapping(value={"/index"})
	public String verIndex(Model modelo) {
		modelo.addAttribute("titulo","NO estas logueado (Debug)");
		return "PaginaPrincipal/index";
	}
	
}