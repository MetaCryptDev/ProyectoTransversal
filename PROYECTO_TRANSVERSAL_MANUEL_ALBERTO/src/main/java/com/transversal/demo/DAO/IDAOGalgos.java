package com.transversal.demo.DAO;

import org.springframework.data.repository.CrudRepository;

import com.transversal.demo.Domain.Entities.GalgosEntity;

public interface IDAOGalgos extends CrudRepository<GalgosEntity, Integer> {

}