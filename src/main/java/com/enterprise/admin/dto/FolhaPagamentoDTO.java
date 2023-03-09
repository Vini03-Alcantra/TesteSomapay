package com.enterprise.admin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;

public class FolhaPagamentoDTO {
	private Long employeeId;
	private Float salary;
	private LocalDate data;
	private LocalTime horario;
	private Employee employee;
	private Enterprise enterprise;

	public FolhaPagamentoDTO() {}
	
	public FolhaPagamentoDTO(Long employeeId, Float salary, LocalDate data, LocalTime horario, Employee employee,
			Enterprise enterprise) {
		this.employeeId = employeeId;
		this.salary = salary;
		this.data = data;
		this.horario = horario;
		this.employee = employee;
		this.enterprise = enterprise;
	}
	
	
	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
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
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

}
