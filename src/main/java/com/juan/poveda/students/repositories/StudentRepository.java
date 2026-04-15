package com.juan.poveda.students.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juan.poveda.students.models.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query to find by email
    Optional<Student> findByEmail(String email);

}