package com.enterprise.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprise.admin.dto.EmployeeDTO;
import com.enterprise.admin.dto.EmployeeResponseDTO;
import com.enterprise.admin.dto.EnterpriseDTO;
import com.enterprise.admin.dto.EnterpriseResponseDTO;
import com.enterprise.admin.dto.FolhaPagamentoDTO;
import com.enterprise.admin.dto.FolhaPagamentoResponseDTO;
import com.enterprise.admin.dto.IncreaseBalanceDTO;
import com.enterprise.admin.dto.IncreaseBalanceResponseDTO;
import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.entities.FolhaPagamento;
import com.enterprise.admin.entities.IncreaseinBalance;
import com.enterprise.admin.service.EmployeeService;
import com.enterprise.admin.service.EnterpriseService;
import com.enterprise.admin.service.FolhaPagamentoService;
import com.enterprise.admin.service.IncreaseBalanceService;

@RestController
@RequestMapping(value = "/enterprise", consumes = MediaType.APPLICATION_JSON_VALUE)
public class EnterpriseController {
	@Autowired
	private EnterpriseService enterpriseService;	
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private FolhaPagamentoService folhaPagamentoService;
	@Autowired
	private IncreaseBalanceService increaseBalanceService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Enterprise> getEnterprise(@PathVariable(name = "id") Long enterpriseID) {				
		Enterprise enterprise = this.enterpriseService.getEnterpriseByID(enterpriseID);
		return ResponseEntity.ok().body(enterprise);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Enterprise>> getAllList() {
		List<Enterprise> enterprise = this.enterpriseService.findAll();
		return ResponseEntity.ok().body(enterprise);
	}
	
	@PostMapping
	public ResponseEntity<EnterpriseResponseDTO> createEnterprise(@RequestBody EnterpriseDTO enterpriseDTO) {
		Enterprise enterprise = new Enterprise();
		
		enterprise.setNome(enterpriseDTO.getNome());
		enterprise.setCnpj(enterpriseDTO.getCnpj());
		enterprise.setBalance(enterpriseDTO.getBalance());
		enterprise.setData(enterpriseDTO.getData());
		enterprise.setHorario(enterpriseDTO.getHorario());
		EnterpriseResponseDTO enterpriseResponse = enterpriseService.createEnterprise(enterprise);
		
		return ResponseEntity.ok().body(enterpriseResponse);
	}
	
	
	@PutMapping("/{enterpriseId}")
	public ResponseEntity<EnterpriseDTO> updateEnterprise(
			@RequestBody EnterpriseDTO enterpriseDTO, 
			@PathVariable("enterpriseId") Long enterpriseID
		) {
		Enterprise enterprise = enterpriseService.getEnterpriseByID(enterpriseID);
		enterprise.setCnpj(enterpriseDTO.getCnpj());
		enterprise.setNome(enterpriseDTO.getNome());
		return ResponseEntity.ok().body(enterpriseDTO);
	}
	
	@DeleteMapping("/{enterpriseId}")
	public ResponseEntity<Boolean> delete(@PathVariable("enterpriseId") Long enterpriseID) {
		Boolean enterpriseDeleted = enterpriseService.deleteEnterpriseByID(enterpriseID);
		return ResponseEntity.ok().body(enterpriseDeleted);
	}
	
	
	// Employee routes atraves da Empresa
	@PostMapping("/employee/{id}")
	public ResponseEntity<EmployeeResponseDTO> createEmploye(
			@PathVariable(name = "id") Long enterpriseID, 
			@RequestBody EmployeeDTO employeeDTO
	) {
		Enterprise enterprise = enterpriseService.getEnterpriseByID(enterpriseID);
		Employee employee = new Employee(); 
		employee.setNome(employeeDTO.getNome());
		employee.setEmail(employeeDTO.getEmail());
		employee.setCpf(employeeDTO.getCpf());
		employee.setDepartamento(employeeDTO.getDepartamento());
		employee.setPost(employeeDTO.getPost());
		employee.setSalary(employeeDTO.getSalary());
		employee.setHorario(employeeDTO.getHorario());
		employee.setData(employeeDTO.getData());
		employee.setEnterprise(enterprise);
		
		EmployeeResponseDTO employeeResponse = employeeService.createEmployee(employee);
		return ResponseEntity.ok().body(employeeResponse);
	}
	
	
	@GetMapping("/employee/list/{id}")
	public ResponseEntity<List<Employee>> listEmploymentByEnterprise(
			@PathVariable(name = "id") Long enterpriseID
	) {
		List<Employee> employee = employeeService.getEmployeeList(enterpriseID);
		
		return ResponseEntity.ok().body(employee);
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> employeeByID(
			@PathVariable(name = "id") Long employeeID
	) {
		Employee employee = employeeService.getEmployeeByID(employeeID);
		return ResponseEntity.ok().body(employee);
	}
	
	@PutMapping("/{employeeId}")
	public ResponseEntity<EmployeeDTO> updateEmployee(
			@PathVariable(name = "id") Long employeeID,
			@RequestBody EmployeeDTO employeeDTO
	) {
		EmployeeDTO employee = employeeService.updateEmployeeByID(employeeDTO, employeeID);
		return ResponseEntity.ok().body(employee);
	}
	
	//Folha Pagamento route
	@PostMapping("/folha/{enterprise_id}")
	public ResponseEntity<FolhaPagamento> createRegisterFolhapagamento(
			@PathVariable(name = "enterprise_id") Long enterpriseID,
			@RequestBody FolhaPagamentoDTO folhaPagamentoDTO
	) {
		
		Enterprise enterprise = enterpriseService.getEnterpriseByID(folhaPagamentoDTO.getEmployeeId());
		if (enterprise == null) {
			return ResponseEntity.badRequest().body(null);
		}
		Employee employee = employeeService.getEmployeeByID(folhaPagamentoDTO.getEmployeeId());
		EnterpriseResponseDTO enterpriseResponseDTO = enterpriseService.pagarSalario(enterpriseID, folhaPagamentoDTO);
		if(enterpriseResponseDTO == null) {
			return ResponseEntity.badRequest().body(null);
		}
		FolhaPagamento folhaPagamento = new FolhaPagamento();
		folhaPagamento.setSalary(folhaPagamentoDTO.getSalary());
		folhaPagamento.setData(folhaPagamentoDTO.getData());
		folhaPagamento.setHorario(folhaPagamentoDTO.getHorario());
		folhaPagamento.setEmployee(employee);
		folhaPagamento.setEnterprise(enterprise);
		folhaPagamentoService.createFolhaPagamento(folhaPagamento);
		
		return ResponseEntity.ok().body(folhaPagamento);
	}
	
	
	// Increase Balance route
	@PostMapping("/increase/{enterprise_id}")
	public ResponseEntity<IncreaseinBalance> createRegisterIncreaseInBalance(
		@PathVariable(name = "enterprise_id") Long enterpriseID,
		@RequestBody IncreaseBalanceDTO increaseBalanceDTO 
	) {
		Enterprise enterprise = enterpriseService.getEnterpriseByID(enterpriseID);
		if (enterprise == null) {
			return ResponseEntity.badRequest().body(null);
		}
		EnterpriseResponseDTO enterpriseResponseDTO = enterpriseService.receiveAndIncreasebalance(enterpriseID, increaseBalanceDTO);
		if(enterpriseResponseDTO == null) {
			return ResponseEntity.badRequest().body(null);
		}
		IncreaseinBalance increaseinBalance = new IncreaseinBalance();
		increaseinBalance.setOrigin(increaseBalanceDTO.getOrigin());
		increaseinBalance.setValor(increaseBalanceDTO.getValor());
		increaseinBalance.setData(increaseBalanceDTO.getData());
		increaseinBalance.setHorario(increaseBalanceDTO.getHorario());
		increaseinBalance.setEnterprise(enterprise);
		IncreaseinBalance increaseBalanceResponseDTO = increaseBalanceService.receiveAndIncreasebalance(increaseinBalance);
		
		return ResponseEntity.ok().body(increaseBalanceResponseDTO);
	}
	
}
