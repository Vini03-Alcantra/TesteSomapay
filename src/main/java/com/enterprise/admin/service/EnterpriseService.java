package com.enterprise.admin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.admin.dto.EnterpriseDTO;
import com.enterprise.admin.dto.EnterpriseResponseDTO;
import com.enterprise.admin.dto.FolhaPagamentoDTO;
import com.enterprise.admin.dto.IncreaseBalanceDTO;
import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.repository.EmployeeRepository;
import com.enterprise.admin.repository.EnterpriseRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;

@Service
public class EnterpriseService {
	@Autowired
	private EnterpriseRepository enterpriseRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Enterprise> findAll(){
		try {
			List<Enterprise> enterpriseIterable = enterpriseRepository.findAll();
			if (enterpriseIterable == null) {
				return null;
			}
			
			return enterpriseIterable;
		} catch (Exception e) {
			throw new Error("Erro ao consultar lista de empresas");
		}
	}
	
	public Enterprise getEnterpriseByID(Long id) {
		try {
			Enterprise enterprise = this.enterpriseRepository.getReferenceById(id);
			if(enterprise == null) {
				return null;
			}
			Enterprise enterpriseResponse = this.convertEnterprise(enterprise.getID(), enterprise.getNome(), enterprise.getCnpj(), enterprise.getBalance(), enterprise.getData(), enterprise.getHorario());
			return enterpriseResponse;
		} catch (Exception e) {
			throw new Error("Erro ao buscar empresa por ID");
		}
	}
	
	public EnterpriseResponseDTO verifyExistsByCnpj(String cnpj) {
		try {
			EnterpriseResponseDTO enterpriseResponseDTO = new EnterpriseResponseDTO();
			List<Enterprise> enterpriseList = this.enterpriseRepository.findAll();
			System.out.println("Enterprise List" + enterpriseList);
			Optional<Enterprise> enterpriseFind = enterpriseList
				.stream()
				.filter(enterprise -> enterprise.getCnpj() == cnpj)
				.findFirst();
			System.out.println("Enterprise Find" +enterpriseFind);
			
			if(enterpriseFind.isEmpty()) {
				return null;
			}
			
			enterpriseResponseDTO.setID(enterpriseFind.get().getID());
			enterpriseResponseDTO.setNome(enterpriseFind.get().getNome());
			enterpriseResponseDTO.setCnpj(enterpriseFind.get().getNome());
			enterpriseResponseDTO.setBalance(enterpriseFind.get().getBalance());
			
			
			return enterpriseResponseDTO;
		} catch (Exception e) {
			throw new Error("Erro ao verificar cnpj da empresa");
		}
	}
	
	public Boolean verifyCnpjExists(String cnpj) {
		EnterpriseResponseDTO existsOrNot = this.verifyExistsByCnpj(cnpj);
		
		if (existsOrNot == null) {
			return false;
		}
		
		return true;
	}	

	@Transactional
	public EnterpriseResponseDTO createEnterprise(Enterprise enterprise) {	
		System.out.println(enterprise.getData() + " " + enterprise.getHorario());
		try {
			Boolean existsOrNot = this.verifyCnpjExists(enterprise.getCnpj());
			if(existsOrNot == true) {
				return null;
			}
			enterpriseRepository.save(enterprise);
			EnterpriseResponseDTO enterpriseCreated = verifyExistsByCnpj(enterprise.getCnpj());
			if(enterpriseCreated == null) {
				return null;
			}
			EnterpriseResponseDTO enterpriseResponseDTO = new EnterpriseResponseDTO();
			enterpriseResponseDTO.setID(enterpriseCreated.getID());
			enterpriseResponseDTO.setNome(enterpriseCreated.getNome());
			enterpriseResponseDTO.setCnpj(enterpriseCreated.getCnpj());
			enterpriseResponseDTO.setBalance(enterpriseCreated.getBalance());
			
			return enterpriseResponseDTO;
		} catch (Exception e) {
			System.out.println(e.toString());
			throw new Error("Erro ao criar empresa");
		}
	}
	
	@Transactional
	public EnterpriseResponseDTO receiveAndIncreasebalance(Long enterprise_id, IncreaseBalanceDTO increaseBalanceDTO) {
		try {
			Enterprise enterprise = this.enterpriseRepository.getReferenceById(enterprise_id);
			if(enterprise == null) {
				return null;
			}
			enterprise.setBalance(enterprise.getBalance() + increaseBalanceDTO.getValor());
			enterpriseRepository.save(enterprise);
			EnterpriseResponseDTO enterpriseResponseDTO = new EnterpriseResponseDTO();
			enterpriseResponseDTO.setID(enterprise.getID());
			enterpriseResponseDTO.setNome(enterprise.getNome());
			enterpriseResponseDTO.setCnpj(enterprise.getCnpj());
			enterpriseResponseDTO.setBalance(enterprise.getBalance());
			
			return enterpriseResponseDTO;
		} catch (Exception e) {
			throw new Error("Erro ao atualizar dados da empresa");
		}
	}
	
	@Transactional
	public EnterpriseResponseDTO pagarSalario(Long enterprise_id, FolhaPagamentoDTO folhaPagamentoDTO) {
		try {
			Enterprise enterprise = this.enterpriseRepository.getReferenceById(enterprise_id);
			if(enterprise == null) {
				return null;
			}
			if(folhaPagamentoDTO.getSalary() > enterprise.getBalance()) {
				throw new Error("Saldo insuficiente pra pagamento");
			}
			enterprise.setBalance(enterprise.getBalance() - folhaPagamentoDTO.getSalary());
			EnterpriseResponseDTO enterpriseResponseDTO = new EnterpriseResponseDTO();
			enterpriseResponseDTO.setID(enterprise.getID());
			enterpriseResponseDTO.setNome(enterprise.getNome());
			enterpriseResponseDTO.setCnpj(enterprise.getCnpj());
			enterpriseResponseDTO.setBalance(enterprise.getBalance());
			
			return enterpriseResponseDTO;
		} catch (Exception e) {
			throw new Error("Erro ao realizar pagamento");
		}
	}
	
	public Boolean deleteEnterpriseByID(Long id) {
		try {
			enterpriseRepository.deleteById(id);
			Enterprise enterprise = enterpriseRepository.getReferenceById(id);
			if (enterprise != null) {
				return false;
			}
			
			return true;
		} catch (Exception e) {
			throw new Error("Erro ao deletar empresa");
		}
	}
	
	public EnterpriseDTO updateEnterpriseByID(EnterpriseDTO enterpriseDTO, Long enterpriseID) {
		try {
			Enterprise enterpriseIfExists = enterpriseRepository.getReferenceById(enterpriseID);
			if (enterpriseIfExists == null) {
				return null;
			}
			enterpriseIfExists.setNome(enterpriseDTO.getNome());
			enterpriseIfExists.setCnpj(enterpriseDTO.getCnpj());
			
			enterpriseRepository.save(enterpriseIfExists);
			return enterpriseDTO;
		} catch (Exception e) {
			throw new Error("Erro ao atualizar empresa"); 
		}
	}
	
	public Enterprise convertEnterprise(Long ID, String nome, String cnpj, Float balance, LocalDate data, LocalTime horario) {
		Enterprise enterprise = new Enterprise( ID, nome, cnpj, balance, data, horario);
		
		return enterprise;
	}
}
