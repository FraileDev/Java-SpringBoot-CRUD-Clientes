package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.dao.IclienteDaoCrud;

@Service
public class ClienteServiceImpl implements IclienteService{

	@Autowired
	private IclienteDaoCrud clienteDaoCrud;
	
	@Transactional(readOnly = true)
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDaoCrud.findAll();
	}

	@Transactional
	@Override
	public void save(Cliente cliente) {
		clienteDaoCrud.save(cliente);
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente findOne(Long id) {
		return clienteDaoCrud.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void eliminar(Long id) {
		clienteDaoCrud.deleteById(id);
	}

}
