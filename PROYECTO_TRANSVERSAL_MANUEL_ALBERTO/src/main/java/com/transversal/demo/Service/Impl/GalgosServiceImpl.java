package com.transversal.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transversal.demo.DAO.IDAOGalgos;
import com.transversal.demo.Domain.DTO.GalgosDTO;
import com.transversal.demo.Domain.Entities.GalgosEntity;
import com.transversal.demo.Service.IServiceGalgos;

@Service
public class GalgosServiceImpl implements IServiceGalgos{
	
	@Autowired
	private IDAOGalgos galgosDao;
	
	
	@Override
	public List<GalgosDTO> buscarGalgos() {
		List<GalgosEntity> lstGalgos= null;
		List<GalgosDTO> listaGalgos = null;
		
		try {
			lstGalgos= (List<GalgosEntity>) galgosDao.findAll();
			listaGalgos = new ArrayList<>();
			for(int i = 0; i < lstGalgos.size(); i++) {
				GalgosDTO gal = new GalgosDTO();
				
				gal.setIdGalgo(lstGalgos.get(i).getIdGalgo());
				gal.setNombre(lstGalgos.get(i).getNombre());
				gal.setDorsal(lstGalgos.get(i).getDorsal());
				gal.setColor(lstGalgos.get(i).getColor());
				gal.setGanancia(lstGalgos.get(i).getGanancia());
				gal.setAceleracion(lstGalgos.get(i).getAceleracion());
				gal.setVelocidad(lstGalgos.get(i).getVelocidad());
				gal.setExperiencia(lstGalgos.get(i).getExperiencia());
				gal.setAvance(lstGalgos.get(i).getAvance());
				
				listaGalgos.add(gal);
			} 
			
		} catch (Exception e) {
			System.out.println("Error, en el BuscarUsuarios en UsuarioServiceImpl");
		}
		return listaGalgos;
	}
}
