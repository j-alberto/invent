package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.OrderWorkflowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderWorkflowDAO extends JpaRepository<OrderWorkflowEntity, Integer>{
    
	Page<OrderWorkflowEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
}