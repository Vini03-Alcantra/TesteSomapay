package com.enterprise.admin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EmployeeDTO {
	private String nome;
	private String email;
	private String cpf;
	private String departamento;
	private String post;
	private Float salary;
	private LocalTime horario;
	private LocalDate data;
	private EnterpriseDTO Enterprise;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public EnterpriseDTO getEnterprise() {
		return Enterprise;
	}
	public void setEnterprise(EnterpriseDTO enterprise) {
		Enterprise = enterprise;
	}
	

	
	
}
