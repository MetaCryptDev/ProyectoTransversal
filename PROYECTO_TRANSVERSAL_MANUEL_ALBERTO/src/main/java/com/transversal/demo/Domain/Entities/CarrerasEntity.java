package com.transversal.demo.Domain.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "CARRERAS")
public class CarrerasEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_CARRERA")
	private Integer idCarrera;
	
	@Column(name = "CIUDAD")
	private String ciudad;
	
	@Column(name = "HORA")
	private String hora;
	
	@Lob
	@Column(name = "PARTICIPANTES")
	private String participantes;
	
	@Column(name = "ESTADO")
	private String estado;

	@Lob
	@Column(name = "CLASIFICACION")
	private String clasificacion;

	
	public CarrerasEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CarrerasEntity(Integer idCarrera, String ciudad, String hora, String participantes, String estado,
			String clasificacion) {
		super();
		this.idCarrera = idCarrera;
		this.ciudad = ciudad;
		this.hora = hora;
		this.participantes = participantes;
		this.estado = estado;
		this.clasificacion = clasificacion;
	}


	public Integer getIdCarrera() {
		return idCarrera;
	}


	public void setIdCarrera(Integer idCarrera) {
		this.idCarrera = idCarrera;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getParticipantes() {
		return participantes;
	}


	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getClasificacion() {
		return clasificacion;
	}


	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

}