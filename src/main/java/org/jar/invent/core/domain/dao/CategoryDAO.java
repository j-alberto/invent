package org.jar.invent.core.domain.dao;

import java.util.List;

import org.jar.invent.core.domain.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends PagingAndSortingRepository<Category, Integer>{
    
	List<Category> findByDescriptionIgnoreCase(String text);
	List<Category> findByDescriptionIgnoreCase(String text, Pageable pageRequest);
	
	
}