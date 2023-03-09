package com.enterprise.admin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enterprise.admin.dto.IncreaseBalanceDTO;
import com.enterprise.admin.entities.Enterprise;
import com.enterprise.admin.entities.IncreaseinBalance;
import com.enterprise.admin.repository.IncreaseBalanceRepository;

import jakarta.transaction.Transactional;

@Service
public class IncreaseBalanceService {
	@Autowired
	private IncreaseBalanceRepository increaseBalanceRepository;
	
	@Transactional
	public IncreaseinBalance receiveAndIncreasebalance(IncreaseinBalance increaseBalance) {
		try {			
			IncreaseinBalance increaseinBalance = new IncreaseinBalance();
			increaseinBalance.setOrigin(increaseBalance.getOrigin());
			increaseinBalance.setValor(increaseBalance.getValor());
			increaseinBalance.setData(increaseBalance.getData());
			increaseinBalance.setHorario(increaseBalance.getHorario());
			increaseinBalance.setEnterprise(increaseBalance.getEnterprise());
			
			IncreaseinBalance increaseBalanceRes = increaseBalanceRepository.save(increaseinBalance);
			return increaseBalanceRes;
		} catch (Exception e) {
			throw new Error("Erro ao inserir registro no banco de dados");
		}
	}
	
	public List<IncreaseinBalance> listIncreaseInBalanceByEnterprise(Long enterprise_id){
		List<IncreaseinBalance> listBalance = increaseBalanceRepository.findAll();
		List<IncreaseinBalance> listBalanceByEnterprise = listBalance
			.stream()
			.filter(enterprise -> enterprise.getEnterprise().getID() == enterprise_id)
			.toList();
		return listBalanceByEnterprise;
	}
	
	public IncreaseinBalance balanceByID(Long balance_id){
		IncreaseinBalance balance = this.increaseBalanceRepository.getReferenceById(balance_id);
		if(balance == null) {
			return null;
		}
		IncreaseinBalance balanceConverted = this.convertIncreasebalance(balance.getID(), balance.getOrigin(), balance.getValor(), balance.getData(), balance.getHorario(), balance.getEnterprise());
		return balanceConverted;
	}
	
	public IncreaseinBalance convertIncreasebalance(long id, String origin, Float valor, LocalDate data,  LocalTime horario,Enterprise enterprise) {
		IncreaseinBalance increaseinBalance = new IncreaseinBalance(id, origin, valor, data, horario, enterprise);
		return increaseinBalance;
	}
	
}
