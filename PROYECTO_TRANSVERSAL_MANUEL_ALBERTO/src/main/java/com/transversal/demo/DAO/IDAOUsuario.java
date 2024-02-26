package com.transversal.demo.DAO;

import org.springframework.data.repository.CrudRepository;

import com.transversal.demo.Domain.Entities.UsuarioEntity;



public interface IDAOUsuario extends CrudRepository<UsuarioEntity, Integer> {

}