package com.transversal.demo.Domain.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "GALGOS")
public class GalgosEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_GALGO")
	private Integer idGalgo;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DORSAL")
	private Integer dorsal;
	
	@Column(name = "COLOR")
	private String color;
	
	@Column(name = "GANANCIA")
	private Integer ganancia;

	@Column(name = "ACELERACION")
	private Integer aceleracion;

	@Column(name = "VELOCIDAD")
	private Integer velocidad;
	
	@Column(name = "EXPERIENCIA")
	private Integer experiencia;
	
	@Column(name = "AVANCE")
	private Integer avance;
	
	public GalgosEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GalgosEntity(Integer idGalgo, String nombre, Integer dorsal, String color, Integer ganancia,
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

	public void setIdGalgos(Integer idGalgos) {
		this.idGalgo = idGalgos;
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