package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.OrderStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusDAO extends JpaRepository<OrderStatusEntity, Short>{
    
	Page<OrderStatusEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
	
}