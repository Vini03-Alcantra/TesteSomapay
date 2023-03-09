package com.enterprise.admin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.enterprise.admin.entities.Enterprise;

public class IncreaseBalanceDTO {
	private String origin;
	private Float valor;
	private LocalDate data;
	private LocalTime horario;
	private Enterprise enterprise;
	
	public IncreaseBalanceDTO(String origin, Float valor, LocalDate data, LocalTime horario, Enterprise enterprise) {
		this.origin = origin;
		this.valor = valor;
		this.data = data;
		this.horario = horario;
		this.enterprise = enterprise;
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

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
}
