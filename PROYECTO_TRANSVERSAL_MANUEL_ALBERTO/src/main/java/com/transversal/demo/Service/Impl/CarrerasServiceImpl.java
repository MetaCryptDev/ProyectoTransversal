package com.transversal.demo.Service.Impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.transversal.demo.DAO.IDAOCarrera;
import com.transversal.demo.Domain.DTO.CarrerasDTO;
import com.transversal.demo.Domain.Entities.CarrerasEntity;
import com.transversal.demo.Service.IServiceCarreras;

@Service
public class CarrerasServiceImpl implements IServiceCarreras{
	
	@Autowired
	private IDAOCarrera carreraDao;
	
	
	@Override
	public List<CarrerasDTO> buscarCarreras() {
		List<CarrerasEntity> lstCarreras= null;
		List<CarrerasDTO> listaCarreras= null;
		
		try {
			lstCarreras= (List<CarrerasEntity>) carreraDao.findAll();
			listaCarreras = new ArrayList<>();
			for(int i = 0; i < lstCarreras.size(); i++) {
				CarrerasDTO carrera = new CarrerasDTO();
				
				carrera.setIdCarrera(lstCarreras.get(i).getIdCarrera());
				carrera.setCiudad(lstCarreras.get(i).getCiudad());
				carrera.setHora(lstCarreras.get(i).getHora());
				carrera.setParticipantes(lstCarreras.get(i).getParticipantes());
				carrera.setEstado(lstCarreras.get(i).getEstado());
				carrera.setClasificacion(lstCarreras.get(i).getClasificacion());
				
				
				listaCarreras.add(carrera);
			} 
			
		} catch (Exception e) {
			System.out.println("Error, en el BuscarCarreras en CarrerasServiceImpl");
		}
		return listaCarreras;
	}
}
