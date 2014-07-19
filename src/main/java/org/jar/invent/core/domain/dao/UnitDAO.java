package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.UnitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitDAO extends JpaRepository<UnitEntity, Integer>{
    
	Page<UnitEntity> findByNameOrDescriptionContainingIgnoreCase(String text, Pageable pageRequest);
	
	
}