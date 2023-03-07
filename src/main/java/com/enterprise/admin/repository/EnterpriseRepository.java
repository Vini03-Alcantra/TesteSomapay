package com.enterprise.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.enterprise.admin.entities.Enterprise;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long>{

}
