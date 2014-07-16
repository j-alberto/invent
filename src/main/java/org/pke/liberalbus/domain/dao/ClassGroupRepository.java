package org.pke.liberalbus.domain.dao;

import java.util.List;

import org.pke.liberalbus.domain.ClassGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends CrudRepository<ClassGroup, Integer> {

    List<ClassGroup> findByName(String name);
}