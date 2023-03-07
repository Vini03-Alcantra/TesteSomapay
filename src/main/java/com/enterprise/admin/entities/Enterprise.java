package com.enterprise.admin.entities;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "tb_enterprise")
@Table(name = "tb_enterprise")
public class Enterprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@Column(name = "name", nullable = false)
	private String nome;
	
	@Column(name = "cnpj", nullable = false, unique = true)
	private String cnpj;	
	
	@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	public Enterprise() {}
	
	public Enterprise(String nome, String cnpj) {		
		this.nome = nome;
		this.cnpj = cnpj;		
	}	
	
}
