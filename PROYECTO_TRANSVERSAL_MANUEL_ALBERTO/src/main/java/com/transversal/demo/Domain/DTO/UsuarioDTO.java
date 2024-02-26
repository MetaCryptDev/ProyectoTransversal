package com.transversal.demo.Domain.DTO;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer idUsuario;
	private String nombre;
	private String email;
	private String passwd;
	private Integer saldo;
	
	
	
	public UsuarioDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public UsuarioDTO(Integer idUsuario, String nombre, String email, String passwd, Integer saldo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
		this.passwd = passwd;
		this.saldo = 0;
	}



	public Integer getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPasswd() {
		return passwd;
	}



	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	public Integer getSaldo() {
		return saldo;
	}



	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}		
}