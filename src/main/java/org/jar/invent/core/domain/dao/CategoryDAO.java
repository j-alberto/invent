package org.jar.invent.core.domain.dao;

import java.util.List;

import org.jar.invent.core.domain.CategoryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends PagingAndSortingRepository<CategoryEntity, Integer>{
    
	List<CategoryEntity> findByDescriptionIgnoreCase(String text);
	List<CategoryEntity> findByDescriptionIgnoreCase(String text, Pageable pageRequest);
	
	
}