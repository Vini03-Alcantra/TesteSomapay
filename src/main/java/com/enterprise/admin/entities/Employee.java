package com.enterprise.admin.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_employee")
@Table(name = "tb_employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@NotBlank
	@Column(name = "name", nullable = false)
	private String nome;
	
	@NotBlank
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@NotBlank
	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;
	
	@Column
	private String departamento;
	
	@NotBlank
	@Column(name = "post", nullable = false)
	private String post;
	
	@Column(name = "salary", nullable = false, unique = true)
	private Float salary;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	@NotNull
	private LocalTime horario;
	
	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;
	
	@OneToMany(mappedBy = "employee")	
	private List<FolhaPagamento> folhaPagamento;
	
		
	public Employee() {}

	
	
	public Employee(Long iD, @NotBlank String nome, @NotBlank String email, @NotBlank String cpf, String departamento,
			@NotBlank String post, Float salary, @NotNull LocalDate data, @NotNull LocalTime horario, Enterprise enterprise) {
		ID = iD;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.departamento = departamento;
		this.post = post;
		this.salary = salary;
		this.data = data;
		this.horario = horario;
		this.enterprise = enterprise;
	}



	public Long getID() {
		return ID;
	}

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

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public List<FolhaPagamento> getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(List<FolhaPagamento> folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
	}
	
	
}
