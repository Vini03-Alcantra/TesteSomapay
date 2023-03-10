package com.enterprise.admin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.admin.dto.EmployeeDTO;
import com.enterprise.admin.dto.EmployeeResponseDTO;
import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.repository.EmployeeRepository;
import com.enterprise.admin.repository.FolhapagamentoReposiotry;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private FolhapagamentoReposiotry folhapagamentoReposiotry;
	
	public List<Employee> getEmployeeList(Long enterpriseID){
		List<Employee> employee = this.employeeRepository.findAll()
			.stream()
			.filter(em -> em.getEnterprise().getID() == enterpriseID).toList();
		
		return employee;
	}
	
	public Employee getEmployeeByID(Long id) {
		Employee employee = this.employeeRepository.getReferenceById(id); 
		if(employee == null) {
			return null;
		}
		Employee employeeResponse = this.convertEmployee(employee.getID(), employee.getNome(), employee.getEmail(), employee.getCpf(), employee.getPost(), 
					employee.getSalary(), employee.getData(), employee.getHorario(), employee.getEnterprise());
		
		return employeeResponse;
	}
	
	public Optional<Employee> verifyExistsByCPF(String cpf) {
		try {
			List<Employee> employeeList = this.employeeRepository.findAll();
			Optional<Employee> employeeFind = employeeList
					.stream()
					.filter(employee -> employee.getCpf() == cpf)
					.findFirst();
			if(employeeFind == null) {
				return null;
			}
			
			return employeeFind;
		} catch (Exception e) {
			throw new Error("Erro ao verificar CPF do funiconário");
		}
	}
	
	@Transactional
	public EmployeeResponseDTO createEmployee(Employee employee) {
		Optional<Employee> verifyEmployee = this.verifyExistsByCPF(employee.getCpf());
		if(verifyEmployee == null) {
			return null;
		}
		try {
			employeeRepository.save(employee);
			Optional<Employee> employeeCreated = this.verifyExistsByCPF(employee.getCpf());
			if(employeeCreated == null) {
				return null;
			}
			EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
			employeeResponseDTO.setNome(employeeCreated.get().getNome());
			employeeResponseDTO.setCpf(employeeCreated.get().getCpf());
			employeeResponseDTO.setEmail(employeeCreated.get().getEmail());
			employeeResponseDTO.setDepartamento(employeeCreated.get().getDepartamento());
			employeeResponseDTO.setPost(employeeCreated.get().getPost());
			employeeResponseDTO.setSalary(employeeCreated.get().getSalary());
			employeeResponseDTO.setEnterprise(employeeCreated.get().getEnterprise());
			
			return employeeResponseDTO;
		} catch (Exception e) {
			throw new Error("Erro ao criar novo funiconário"); 
		}
		
	}
	
	public EmployeeDTO updateEmployeeByID(EmployeeDTO employeeDTO ,Long id) {		
		Employee employeeIfExists = employeeRepository.getReferenceById(id);
		if(employeeIfExists == null) {
			return null;
		}
		
		try {
			employeeIfExists.setNome(employeeDTO.getNome());
			employeeIfExists.setEmail(employeeDTO.getEmail());
			employeeIfExists.setCpf(employeeDTO.getCpf());
			employeeIfExists.setPost(employeeDTO.getPost());
			employeeIfExists.setSalary(employeeDTO.getSalary());
			employeeRepository.save(employeeIfExists);
			return employeeDTO;
		} catch (Exception e) {
			throw new Error("Erro ao atualizar funcionário");
		}
		
	}
	
	public Boolean deleteEmployeeByID(Long id) {
		try {
			employeeRepository.deleteById(id);
			Employee employee = employeeRepository.getReferenceById(id);
			if(employee != null) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			throw new Error("Erro ao atualizar funcionário");
		}
	}
	
	public Employee convertEmployee(Long ID, String nome, String email, String cpf, String post, Float salary, LocalDate data, LocalTime horario,Enterprise enterprise) {
		Employee employeeGenerate = new Employee(ID, nome, email, cpf, post, post, salary, data, horario, enterprise);
		return employeeGenerate;
	}
	
}
