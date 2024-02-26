package com.transversal.demo.Controllers;

import java.util.List;
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

import com.transversal.demo.Domain.DTO.CarrerasDTO;
import com.transversal.demo.Domain.DTO.GalgosDTO;
import com.transversal.demo.Domain.DTO.UsuarioDTO;
import com.transversal.demo.Service.IServiceCarreras;
import com.transversal.demo.Service.IServiceGalgos;
import com.transversal.demo.Service.IServiceUsuario;


@Controller
@RequestMapping("/galgos")
public class GalgosController {

	
	@Autowired
	private IServiceGalgos galgosService; 
	
	@Autowired
	private IServiceCarreras carrerasService; 
	
	@Autowired
	private IServiceUsuario usuarioService; 
	
	
	@GetMapping(value="verGalgos")
	public String verGalgos(Model modelo, @RequestParam(name = "idUsuario") String idUsuario) {
		modelo.addAttribute("titulo","Carrera de Galgos");
		Integer idUsuarioFix = Integer.parseInt(idUsuario);
		Integer saldo = usuarioService.buscarSaldoUsuario(idUsuarioFix);
		System.out.println("TENGO EL SALDO " + saldo + "€");
		modelo.addAttribute("idUsuario" ,idUsuarioFix);
		modelo.addAttribute("lblSaldo" ,saldo);
		return "Galgos/Galgos";
	}
	
	@GetMapping(value = "/recuperarGalgos")
	public ResponseEntity<List<GalgosDTO>> recuperarTodosGalgos() {
	    try {
	        List<GalgosDTO> galgos = galgosService.buscarGalgos();
	        return new ResponseEntity<>(galgos, HttpStatus.OK);
	    } catch (Exception e) {
	        System.out.println("Error en buscarTodosGalgos en GalgosController" + e.getMessage());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping(value = "/recuperarCarreras")
	public ResponseEntity<List<CarrerasDTO>> recuperarTodosCarreras() {
	    try {
	        List<CarrerasDTO> carreras = carrerasService.buscarCarreras();
	        return new ResponseEntity<>(carreras, HttpStatus.OK);
	    } catch (Exception e) {
	        System.out.println("Error en buscarTodosCarreras en GalgosController"+ e.getMessage());
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
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