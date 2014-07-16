package org.jar.invent.core.domain.dao;

import java.util.List;

import org.jar.invent.core.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByLastName(String lastName);
}