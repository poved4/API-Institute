package com.juan.poveda.grades.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juan.poveda.grades.models.entities.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByStudentId(Long studentId);

    List<Grade> findBySubjectId(Long subjectId);

}