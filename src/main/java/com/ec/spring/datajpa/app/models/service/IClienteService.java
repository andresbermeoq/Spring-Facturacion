package com.ec.spring.datajpa.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ec.spring.datajpa.app.models.entities.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
 
	public void save(Cliente cliente);
	
	public Cliente findClientebyID(Long id);
	
	public void delete(Long id);

}
