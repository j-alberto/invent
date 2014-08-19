package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity, Integer>{
    

	Page<ItemEntity> findByNameContainingOrDescriptionContainingAllIgnoreCase(String name, String desc, Pageable pageRequest);
}