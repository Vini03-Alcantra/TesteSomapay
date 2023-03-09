package com.enterprise.admin.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tb_increase_balance")
@Table(name = "tb_increase_balance")
public class IncreaseinBalance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long ID;
	
	@Column
	private String origin;
	
	@Column
	private Float valor;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotNull
	private LocalTime horario;
	
	
	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	private Enterprise enterprise;

	public IncreaseinBalance() {}

	

	public IncreaseinBalance(Long iD, String origin, Float valor, @NotNull LocalDate data, @NotNull LocalTime horario,
			Enterprise enterprise) {
		ID = iD;
		this.origin = origin;
		this.valor = valor;
		this.data = data;
		this.horario = horario;
		this.enterprise = enterprise;
	}



	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
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
