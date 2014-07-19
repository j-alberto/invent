package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Short>{
    
	Page<CategoryEntity> findByDescriptionContainingIgnoreCase(String desc, Pageable pageRequest);
	
	
}