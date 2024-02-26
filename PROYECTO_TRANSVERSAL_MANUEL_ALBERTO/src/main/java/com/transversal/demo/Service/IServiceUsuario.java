package com.transversal.demo.Service;


import java.util.List;

import com.transversal.demo.Domain.DTO.UsuarioDTO;

public interface IServiceUsuario {
	public void guardarUsuario(UsuarioDTO usuario);
	public void actualizarUsuario(UsuarioDTO usuario);
	public void eliminarUsuario(Integer idUsuario);
	public UsuarioDTO buscarUnUsuario(Integer idUsuario);
	public List<UsuarioDTO> buscarUsuarios();
	public Integer buscarSaldoUsuario(Integer idUsuario);
	
	
}
