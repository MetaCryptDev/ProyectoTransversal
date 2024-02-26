package com.transversal.demo.Domain.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
	@SequenceGenerator(name = "seqUsuario", allocationSize = 1, sequenceName = "SEQ_USUARIO")
	
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWD")
	private String passwd;
	
	@Column(name = "SALDO")
	private Integer saldo;

	
/*	@JoinTable(name ="USUARIOS_ROLES",
	joinColumns = @JoinColumn(name = "ID_USUARIO"),
	inverseJoinColumns = @JoinColumn(name="ID_ROL"))			
	@ManyToMany
	private List<RolEntity> listaRoles ; */
 
	
	
	public UsuarioEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UsuarioEntity(Integer idUsuario, String nombre, String email, String passwd, Integer saldo) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.email = email;
		this.passwd = passwd;
		this.saldo= saldo;
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