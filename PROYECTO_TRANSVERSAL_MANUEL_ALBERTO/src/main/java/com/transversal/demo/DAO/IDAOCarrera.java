package com.transversal.demo.DAO;

import org.springframework.data.repository.CrudRepository;

import com.transversal.demo.Domain.Entities.CarrerasEntity;

public interface IDAOCarrera extends CrudRepository<CarrerasEntity, Integer> {

}