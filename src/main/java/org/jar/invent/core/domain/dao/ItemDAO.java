package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.ItemEntity;
import org.jar.invent.core.domain.UnitEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends JpaRepository<ItemEntity, Integer>{
    
	
}