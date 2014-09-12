package org.jar.invent.core.domain.dao;

import org.jar.invent.core.domain.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerEntity, Integer>{
    

	Page<CustomerEntity> findByNameContainingOrCodeContainingOrDescriptionContainingAllIgnoreCase(String name, String code, String desc, Pageable pageRequest);
}