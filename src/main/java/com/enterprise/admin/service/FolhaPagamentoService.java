package com.enterprise.admin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.admin.dto.FolhaPagamentoResponseDTO;
import com.enterprise.admin.entities.Employee;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.entities.FolhaPagamento;
import com.enterprise.admin.repository.FolhapagamentoReposiotry;

import jakarta.transaction.Transactional;

@Service
public class FolhaPagamentoService {
	@Autowired
	private FolhapagamentoReposiotry folhapagamentoReposiotry;
	
	@Transactional 
	public FolhaPagamentoResponseDTO createFolhaPagamento(FolhaPagamento folhaPagamento) {
		try {
			FolhaPagamento folhaPagamentoRes = folhapagamentoReposiotry.save(folhaPagamento);
			FolhaPagamentoResponseDTO folhaPagamentoResponseDTO = new FolhaPagamentoResponseDTO();
			
			folhaPagamentoResponseDTO.setID(folhaPagamentoRes.getID());
			folhaPagamentoResponseDTO.setSalary(folhaPagamentoRes.getSalary());
			folhaPagamentoResponseDTO.setData(folhaPagamentoRes.getData());
			folhaPagamentoResponseDTO.setHorario(folhaPagamentoRes.getHorario());
			folhaPagamentoResponseDTO.setEmployee(folhaPagamentoRes.getEmployee());
			folhaPagamentoResponseDTO.setEnterprise(folhaPagamentoRes.getEnterprise());
			
			return folhaPagamentoResponseDTO;
		} catch (Exception e) {
			throw new Error("Não foi possível realizar a inserção da folha de pagamento");
		}
	}
	
	public FolhaPagamento getFolhapagamentoByID(Long folha_pagamento_id) {
		try {
			FolhaPagamento folhaPagamento= this.folhapagamentoReposiotry.getReferenceById(folha_pagamento_id);
			if (folhaPagamento == null) {
				return null;
			}
			FolhaPagamento folhaPagamentoConverted = this.convertFolhaPagmaneto(folha_pagamento_id, null, null, null, null, null);
			return folhaPagamentoConverted;
		} catch (Exception e) {
			throw new Error("Não foi possível realizar a busca");
		}
	}
	
	public List<FolhaPagamento> listFolhaPagamentoByID(Long enterprise_id){
		try {
			List<FolhaPagamento> listFolha = this.folhapagamentoReposiotry.findAll();
			if(listFolha == null) {
				return null;
			}
			List<FolhaPagamento> listFolhaByEnterprise = listFolha
					.stream()
					.filter(enterprise -> enterprise.getEnterprise().getID() == enterprise_id)
					.toList();
			return listFolhaByEnterprise;
		} catch (Exception e) {
			throw new Error("operação não disponível no momento");
		}
	}
	
	public FolhaPagamento convertFolhaPagmaneto(Long ID, Float salary, Enterprise enterprise, Employee employee, LocalDate data, LocalTime horario) {
		FolhaPagamento folhaPagamento = new FolhaPagamento(ID, salary, enterprise, employee, data, horario);
		return folhaPagamento;
	}
}
