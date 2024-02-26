package com.transversal.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.transversal.demo.DAO.IDAOUsuario;
import com.transversal.demo.Domain.DTO.UsuarioDTO;
import com.transversal.demo.Domain.Entities.UsuarioEntity;
import com.transversal.demo.Service.IServiceUsuario;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	
	@Autowired
	private IDAOUsuario usuarioDao;
	
	@Autowired
	private IServiceUsuario usuarioService;
	
//MOSTRAR LAS VISTAS
	
	@GetMapping(value={"","/","/login"})
	public String verLogin(Model modelo) {
		modelo.addAttribute("titulo","Login aplicacion");
		return "LoginRegistro/loginRegistro";
	}
	
	@GetMapping(value = "/comprobar")
	public String oomprobarUsuario(HttpServletRequest request, Model modelo) {

		try {
			String email = request.getParameter("email");
			String passwd = request.getParameter("passwd");
		
			System.out.println("Usuario recogido, toca comprobar");
			
			  List<UsuarioEntity> lstUsuarios = null;
			  lstUsuarios = (List<UsuarioEntity>) usuarioDao.findAll();
		        UsuarioDTO usuarioDTO = null; 

		        for (UsuarioEntity usuarioEntity : lstUsuarios) {
		            if (usuarioEntity.getEmail().equals(email) && usuarioEntity.getPasswd().equals(passwd)) {
		                usuarioDTO = new UsuarioDTO();
		                usuarioDTO.setIdUsuario(usuarioEntity.getIdUsuario());
		                usuarioDTO.setEmail(usuarioEntity.getEmail());
		                usuarioDTO.setPasswd(usuarioEntity.getPasswd());
		                usuarioDTO.setNombre(usuarioEntity.getNombre());
		                usuarioDTO.setSaldo(usuarioEntity.getSaldo());
		                
		                modelo.addAttribute("idUsuario", usuarioDTO.getIdUsuario());
		                modelo.addAttribute("usuario", usuarioDTO.getNombre());
		                modelo.addAttribute("saldo", usuarioDTO.getSaldo());
		                return "PaginaPrincipal/index"; 
		            }
		        }
		       
		        modelo.addAttribute("titulo", "Login de la aplicación");
		        modelo.addAttribute("error", "Usuario o contraseña incorrectos");
		        System.out.println("Credenciales incorrectas");
		    } catch (Exception e) {
		        modelo.addAttribute("titulo", "Gestión de Usuario");
		        modelo.addAttribute("error", "Error al comprobar el usuario");
		        System.out.println(e.getMessage());
		        System.out.println("Error durante la autenticación");
		    }

		    return "LoginRegistro/loginRegistro";
		}
	
	@GetMapping(value = "/anadir")
	public String anadirUsuario(HttpServletRequest request, Model modelo) {
		
		UsuarioDTO usuario= new UsuarioDTO();

		try {
			String nombre = request.getParameter("nombre");
			usuario.setNombre(nombre);
			String email = request.getParameter("email");
			usuario.setEmail(email);
			String passwd = request.getParameter("passwd");
			usuario.setPasswd(passwd);
			
			usuario.setSaldo(0);
			 
			System.out.println("Voy a mandarlo");
			
			usuarioService.guardarUsuario(usuario);
			
			 List<UsuarioEntity> lstUsuarios = null;
			  lstUsuarios = (List<UsuarioEntity>) usuarioDao.findAll();
		        UsuarioDTO usuarioDTO = null; 

		        for (UsuarioEntity usuarioEntity : lstUsuarios) {
		            if (usuarioEntity.getNombre().equals(usuario.getNombre())) {
		                usuarioDTO = new UsuarioDTO();
		                usuarioDTO.setIdUsuario(usuarioEntity.getIdUsuario());
		                usuarioDTO.setEmail(usuarioEntity.getEmail());
		                usuarioDTO.setPasswd(usuarioEntity.getPasswd());
		                usuarioDTO.setNombre(usuarioEntity.getNombre());
		                usuarioDTO.setSaldo(usuarioEntity.getSaldo());
		                
		                modelo.addAttribute("idUsuario", usuarioDTO.getIdUsuario());
		                modelo.addAttribute("usuario", usuarioDTO.getNombre());
		                modelo.addAttribute("saldo", usuarioDTO.getSaldo());
		              
		            }
		           }
		
		}catch (Exception e) {
			modelo.addAttribute("error", "Error al añadir al nuevo Usuario");
			System.out.println(e.getMessage());
			System.out.println("Error en Alta en LoginController");
		}
		
		return "PaginaPrincipal/index";
	}
	
	
	@GetMapping(value = "/eliminar/{id}")
	public String eliminarUsuario(@PathVariable Integer id, Model modelo) {
		
		try {
			usuarioService.eliminarUsuario(id);
			modelo.addAttribute("titulo", "Todos los Usuarios");
			modelo.addAttribute("listaUsuarios", usuarioService.buscarUsuarios()) ;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			modelo.addAttribute("titulo", "Gestion de Usuario");
			System.out.println("Error en eliminar en UsuarioController");
		}
		return "LoginRegistro/loginRegistro";
	}
	
/*	@GetMapping(value= "/modificarUsuario/{id}")
	public String modificarUsuario(@PathVariable Integer id, Model modelo){
	    UsuarioDTO usuario = usuarioService.buscarUnUsuario(id);
	try {
	    modelo.addAttribute("idUsuario", usuario.getIdUsuario());
	    modelo.addAttribute("nombre", usuario.getNombre());
	    modelo.addAttribute("passwd", usuario.getPasswd());
	    modelo.addAttribute("email", usuario.getEmail());
	    modelo.addAttribute("saldo", usuario.getSaldo());
	    modelo.addAttribute("titulo", "Modificando el Usuario");
	} catch (Exception e) {
		modelo.addAttribute("titulo", "Gestion de Usuario" + e.getMessage());
		System.out.println("Error en modificarUsuario en UsuarioController");
	}
	    return "usuario/modificarUsuario";
	}
	
	@GetMapping(value = "/actualizar")
	public String actualizarUsuario(HttpServletRequest request, Model modelo) {
		
		UsuarioDTO usuario= new UsuarioDTO();
		try{
			Integer idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
			usuario.setIdUsuario(idUsuario);
			String nombre = request.getParameter("nombre");
			usuario.setNombre(nombre);
			String passwd = request.getParameter("passwd");
			usuario.setPasswd(passwd);
			Integer saldo = Integer.parseInt(request.getParameter("saldo"));
			usuario.setSaldo(saldo);
			
			System.out.println("Voy a mandarlo");
			
			usuarioService.actualizarUsuario(usuario);
			
			
			modelo.addAttribute("usuario", usuario.getNombre());
			modelo.addAttribute("saldo", usuario.getSaldo());
		
		}catch(Exception e) {
			System.out.println("Error en actualizarUsuario en UsuarioController");
		}
		return "index";
	}	*/
	
	}