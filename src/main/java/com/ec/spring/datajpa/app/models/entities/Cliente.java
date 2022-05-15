package com.ec.spring.datajpa.app.models.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cliente_sequence", initialValue = 3, sequenceName = "cliente_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_sequence")
	private Long idCliente;

	@NotEmpty
	private String name;
	
	@NotEmpty
	@Email
	private String email;

	@Column(name = "last_name_cliente")
	@NotEmpty
	private String last_name;

	@NotNull
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate createdAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDate.now();
	}
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
