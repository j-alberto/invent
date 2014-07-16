package org.jar.invent.core.domain.dao;

import java.util.List;

import org.jar.invent.core.domain.ClassGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassGroupRepository extends CrudRepository<ClassGroup, Integer> {

    List<ClassGroup> findByName(String name);
}