package org.jar.invent.core.domain.dao;

import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends JpaRepository<CategoryEntity, Short>{
    
	List<CategoryEntity> findByDescriptionIgnoreCase(String text);
	List<CategoryEntity> findByDescriptionIgnoreCase(String text, Pageable pageRequest);
	
	
}