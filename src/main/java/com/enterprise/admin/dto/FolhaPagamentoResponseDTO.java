package com.enterprise.admin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;

public class FolhaPagamentoResponseDTO {
	private Long ID;
	private Float salary;
	private Enterprise enterprise;
	private Employee employee;
	private LocalDate data;
	private LocalTime horario;
	
	
	
	public FolhaPagamentoResponseDTO() {}

	public FolhaPagamentoResponseDTO (Long iD, Float salary, Enterprise enterprise, Employee employee, LocalDate data,
			LocalTime horario) {
		ID = iD;
		this.salary = salary;
		this.enterprise = enterprise;
		this.employee = employee;
		this.data = data;
		this.horario = horario;
	}

	

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
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
