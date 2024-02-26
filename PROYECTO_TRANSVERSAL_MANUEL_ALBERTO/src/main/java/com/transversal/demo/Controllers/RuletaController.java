package com.transversal.demo.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transversal.demo.Domain.DTO.UsuarioDTO;
import com.transversal.demo.Service.IServiceUsuario;


@Controller
@RequestMapping("/ruleta")
public class RuletaController {
	
	@Autowired
	private IServiceUsuario usuarioService;
	
//MOSTRAR LAS VISTAS
	
	@GetMapping(value="verRuleta")
	public String verRuleta(Model modelo, @RequestParam(name = "idUsuario") String idUsuario) {
		modelo.addAttribute("titulo","Ruleta");
		Integer idUsuarioFix = Integer.parseInt(idUsuario);
		Integer saldo = usuarioService.buscarSaldoUsuario(idUsuarioFix);
		System.out.println("TENGO EL SALDO " + saldo + "€");
		modelo.addAttribute("idUsuario" ,idUsuarioFix);
		modelo.addAttribute("saldoInicial" ,saldo);
		return "Ruleta/ruleta";
	}
	
	@GetMapping(value = "/recuperarSaldo")
    public ResponseEntity<Integer> recuperarSaldo() {
        try {
            Integer saldo = usuarioService.buscarSaldoUsuario(null);
            return new ResponseEntity<>(saldo, HttpStatus.OK);
        } catch (Exception e) {
        	System.out.println("Error en buscarSaldoUsuario en GalgosController" + e.getMessage());;
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@PostMapping("/actualizarSaldo")
    public ResponseEntity<?> actualizarSaldo(@RequestBody Map<String, Integer> payload) {
       
        Integer idUsuario = payload.get("idUsuario");
        Integer saldo = payload.get("saldo");
        
        System.out.println("TENGO EL SALDO!!! " + saldo);

        UsuarioDTO usuario = usuarioService.buscarUnUsuario(idUsuario);
        if (usuario != null) {
            usuario.setSaldo(saldo);
            usuarioService.actualizarUsuario(usuario);
            return ResponseEntity.ok().body("Saldo actualizado con éxito para el usuario " + idUsuario);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
}