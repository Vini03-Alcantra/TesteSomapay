package com.enterprise.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enterprise.admin.entities.Enterprise;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>{
	
}
