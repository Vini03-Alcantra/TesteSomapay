package com.enterprise.admin.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_enterprise")
@Table(name = "tb_enterprise")
public class Enterprise {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@Column(name = "name", nullable = false)
	@NotEmpty(message = "Name must not be empty")
	private String nome;
	
	@Column(name = "cnpj", nullable = false, unique = true)
	@NotEmpty(message = "CNPJ must not be empty")
	private String cnpj;	
	
	@Column(name = "balance")
	private Float balance;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotNull
	private LocalTime horario;
	
	@OneToMany(mappedBy = "enterprise")
	@JsonIgnore
	private List<Employee> employee;
	
	@OneToMany(mappedBy = "enterprise")
	@JsonIgnore
	private List<IncreaseinBalance> increaseinBalance;
	
	@OneToMany(mappedBy = "enterprise")
	@JsonIgnore
	private List<FolhaPagamento> folhaPagamento;
	
	public Enterprise() {}

	public Enterprise(Long iD, @NotEmpty(message = "Name must not be empty") String nome,
			@NotEmpty(message = "CNPJ must not be empty") String cnpj, Float balance, @NotNull LocalDate data,
			@NotNull LocalTime horario) {		
		ID = iD;
		this.nome = nome;
		this.cnpj = cnpj;
		this.balance = balance;
		this.data = data;
		this.horario = horario;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public List<IncreaseinBalance> getIncreaseinBalance() {
		return increaseinBalance;
	}

	public void setIncreaseinBalance(List<IncreaseinBalance> increaseinBalance) {
		this.increaseinBalance = increaseinBalance;
	}

	public List<FolhaPagamento> getFolhaPagamento() {
		return folhaPagamento;
	}

	public void setFolhaPagamento(List<FolhaPagamento> folhaPagamento) {
		this.folhaPagamento = folhaPagamento;
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
}
