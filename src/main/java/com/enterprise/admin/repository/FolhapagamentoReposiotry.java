package com.enterprise.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.admin.entities.FolhaPagamento;

@Repository
public interface FolhapagamentoReposiotry extends JpaRepository<FolhaPagamento, Long> {

}
