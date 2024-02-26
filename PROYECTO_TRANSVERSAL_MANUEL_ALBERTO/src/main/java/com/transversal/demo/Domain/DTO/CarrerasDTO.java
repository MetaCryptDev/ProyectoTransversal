package com.transversal.demo.Domain.DTO;

import java.io.Serializable;


public class CarrerasDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer idCarrera;
	private String ciudad;
	private String hora;
	private String participantes;
	private String estado;
	private String clasificacion;
	
	
	
	public CarrerasDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CarrerasDTO(Integer idCarrera, String ciudad, String hora, String participantes, String estado,
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