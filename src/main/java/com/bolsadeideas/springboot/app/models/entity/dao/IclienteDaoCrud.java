package com.bolsadeideas.springboot.app.models.entity.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IclienteDaoCrud  extends CrudRepository<Cliente, Long>{

}
