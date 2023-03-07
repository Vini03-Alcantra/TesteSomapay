package com.enterprise.admin.entities;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "tb_employee")
@Table(name = "tb_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@Column(name = "name", nullable = false)
	private String nome;
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;
	@Column(name = "post", nullable = false)
	private String post;
	@Column(name = "salary", nullable = false, unique = true)
	private Float salary;
	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;
	
	public Employee(String nome, String email, String cpf, String post, Float salary, Enterprise enterprise) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.post = post;
		this.salary = salary;
		this.enterprise = enterprise;
	}
	
	
}
