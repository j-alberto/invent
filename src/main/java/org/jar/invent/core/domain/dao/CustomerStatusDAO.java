package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.CustomerStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStatusDAO extends JpaRepository<CustomerStatusEntity, Integer>{
    
	Page<CustomerStatusEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
	
}