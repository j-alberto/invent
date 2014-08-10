package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.OrderTypeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTypeDAO extends JpaRepository<OrderTypeEntity, Short>{
    
	Page<OrderTypeEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
	
}