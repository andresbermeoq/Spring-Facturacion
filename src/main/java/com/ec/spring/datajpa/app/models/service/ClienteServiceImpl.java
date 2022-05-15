package com.ec.spring.datajpa.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ec.spring.datajpa.app.models.dao.IClienteDAO;
import com.ec.spring.datajpa.app.models.entities.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {
	
	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	@Transactional( readOnly = true)
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Override
	@Transactional( readOnly = true)
	public Cliente findClientebyID(Long id) {
		// TODO Auto-generated method stub
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDAO.deleteById(id); 
	}

	@Override
	@Transactional( readOnly = true) 
	public Page<Cliente> findAll(Pageable pageable) {
		return clienteDAO.findAll(pageable) ;
	}

}
