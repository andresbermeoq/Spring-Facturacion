package com.ec.spring.datajpa.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ec.spring.datajpa.app.models.entities.Cliente;

public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long> {
	
}
