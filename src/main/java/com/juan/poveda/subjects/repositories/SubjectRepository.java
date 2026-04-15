package com.juan.poveda.subjects.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.juan.poveda.subjects.models.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    /**
     * Finds a subject by its unique academic code.
     * Useful for internal lookups and validation.
     * * @param code The unique code of the subject (e.g., "MATH101")
     * @return An Optional containing the subject if found.
     */
    Optional<Subject> findByCode(String code);

    /**
     * Checks if a subject exists with a specific code.
     * * @param code The unique code to check.
     * @return true if it exists, false otherwise.
     */
    boolean existsByCode(String code);

}