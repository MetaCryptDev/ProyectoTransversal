package com.transversal.demo.Domain.DTO;

import java.io.Serializable;


public class GalgosDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	private Integer idGalgo;
	private String nombre;
	private Integer dorsal;
	private String color;
	private Integer ganancia;
	private Integer aceleracion;
	private Integer velocidad;
	private Integer experiencia;
	private Integer avance;
	
	
	public GalgosDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public GalgosDTO(Integer idGalgo, String nombre, Integer dorsal, String color, Integer ganancia,
			Integer aceleracion, Integer velocidad, Integer experiencia, Integer avance) {
		super();
		this.idGalgo = idGalgo;
		this.nombre = nombre;
		this.dorsal = dorsal;
		this.color = color;
		this.ganancia = ganancia;
		this.aceleracion = aceleracion;
		this.velocidad = velocidad;
		this.experiencia = experiencia;
		this.avance = avance;
	}


	public Integer getIdGalgo() {
		return idGalgo;
	}


	public void setIdGalgo(Integer idGalgo) {
		this.idGalgo = idGalgo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Integer getDorsal() {
		return dorsal;
	}


	public void setDorsal(Integer dorsal) {
		this.dorsal = dorsal;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Integer getGanancia() {
		return ganancia;
	}


	public void setGanancia(Integer ganancia) {
		this.ganancia = ganancia;
	}


	public Integer getAceleracion() {
		return aceleracion;
	}


	public void setAceleracion(Integer aceleracion) {
		this.aceleracion = aceleracion;
	}


	public Integer getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(Integer velocidad) {
		this.velocidad = velocidad;
	}


	public Integer getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(Integer experiencia) {
		this.experiencia = experiencia;
	}


	public Integer getAvance() {
		return avance;
	}


	public void setAvance(Integer avance) {
		this.avance = avance;
	}



		
}