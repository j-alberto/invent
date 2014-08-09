package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.CustomerTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTypeDAO extends JpaRepository<CustomerTypeEntity, Integer>{
    
	Page<CustomerTypeEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
	
}