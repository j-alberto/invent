package org.pke.liberalbus.domain.dao;

import java.util.List;

import org.pke.liberalbus.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    List<Student> findByLastName(String lastName);
}