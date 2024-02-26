package com.transversal.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transversal.demo.DAO.IDAOUsuario;
import com.transversal.demo.Domain.DTO.UsuarioDTO;
import com.transversal.demo.Domain.Entities.UsuarioEntity;
import com.transversal.demo.Service.IServiceUsuario;

import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements IServiceUsuario{
	
	@Autowired
	private IDAOUsuario usuarioDao;
	
	

	@Override
	@Transactional
	public void guardarUsuario(UsuarioDTO usuario) {
		try {
			
			Integer idUsuario = usuario.getIdUsuario();
			String nombre = usuario.getNombre();
			String passwd = usuario.getPasswd();
			String email = usuario.getEmail();
			Integer saldo = usuario.getSaldo();
			
			UsuarioEntity usu = new UsuarioEntity(idUsuario, nombre, email, passwd, saldo);
			
			System.out.println("Lo mando a BBDD");
			
			usuarioDao.save(usu);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error, en el GuardarUsuario en UsuarioServiceImpl");
		}
	}


	@Override
	@Transactional
	public void actualizarUsuario(UsuarioDTO usuario) {
		
		try {	
			Integer idUsuario = usuario.getIdUsuario();
			String nombre = usuario.getNombre();
			String email = usuario.getEmail();
			String passwd = usuario.getPasswd();
			Integer saldo = usuario.getSaldo();
			
			UsuarioEntity usu = new UsuarioEntity(idUsuario, nombre, email, passwd, saldo);
			
			System.out.println("Lo mando a BBDD");
			
			usuarioDao.save(usu);
			
		} catch (Exception e) {
			System.out.println("Error, en el ActualizarUsuario en UsuarioServiceImpl");
		}	
	}
	
	@Override
	@Transactional
	public void eliminarUsuario(Integer idUsuario) {

		UsuarioEntity usuario = null;
		try {
			usuario= usuarioDao.findById(idUsuario).orElse(null);
			usuarioDao.delete(usuario);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error, en el EliminarUsuario en UsuarioServiceImpl");
		}	
	}
	
	@Override
	public UsuarioDTO buscarUnUsuario(Integer idUsuario) {
			UsuarioEntity usuario = null;
			UsuarioDTO usu = new UsuarioDTO();
			try {
			
				usuario = usuarioDao.findById(idUsuario).orElse(null);
				
				usu.setIdUsuario(usuario.getIdUsuario());
				usu.setNombre(usuario.getNombre());
				usu.setPasswd(usuario.getPasswd());
				usu.setEmail(usuario.getEmail());
				usu.setSaldo(usuario.getSaldo());
				

		} catch (Exception e) {
			System.out.println("Error, en el BuscarUnUsuario en UsuarioServiceImpl");
		}	
		return usu;
	}
	
	@Override
	public List<UsuarioDTO> buscarUsuarios() {
		List<UsuarioEntity> lstUsuarios = null;
		List<UsuarioDTO> listaUsuarios = null;
		
		try {
			lstUsuarios= (List<UsuarioEntity>) usuarioDao.findAll();
			listaUsuarios = new ArrayList<>();
			for(int i = 0; i < lstUsuarios.size(); i++) {
				UsuarioDTO usu = new UsuarioDTO();
				
				usu.setIdUsuario(lstUsuarios.get(i).getIdUsuario());
				usu.setNombre(lstUsuarios.get(i).getNombre());
				usu.setPasswd(lstUsuarios.get(i).getPasswd());
				usu.setEmail(lstUsuarios.get(i).getEmail());
				usu.setSaldo(lstUsuarios.get(i).getSaldo());
							
				listaUsuarios.add(usu);
			} 
			
		} catch (Exception e) {
			System.out.println("Error, en el BuscarUsuarios en UsuarioServiceImpl");
		}
		return listaUsuarios;
	}
	
	
	@Override
	public Integer buscarSaldoUsuario(Integer idUsuario) {
			UsuarioEntity usuario = null;
			Integer saldo = 0;
			try {
			
				usuario = usuarioDao.findById(idUsuario).orElse(null);
				
				saldo = usuario.getSaldo();
				
	
		} catch (Exception e) {
			System.out.println("Error, en el BuscarUnUsuario en UsuarioServiceImpl");
		}	
		return saldo;
	}
	
}
