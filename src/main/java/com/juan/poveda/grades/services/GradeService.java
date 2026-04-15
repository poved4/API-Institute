package com.juan.poveda.grades.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.poveda.grades.models.dtos.GradeDTO;
import com.juan.poveda.grades.models.entities.Grade;
import com.juan.poveda.grades.repositories.GradeRepository;
import com.juan.poveda.students.models.entities.Student;
import com.juan.poveda.students.repositories.StudentRepository;
import com.juan.poveda.subjects.models.entities.Subject;
import com.juan.poveda.subjects.repositories.SubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    private GradeDTO convertToDTO(Grade grade) {
        return new GradeDTO(
                grade.getId(),
                grade.getValue(),
                grade.getRegistrationDate(),
                grade.getStudent().getId(),
                grade.getSubject().getId()
        );
    }

    @Transactional
    public GradeDTO registerGrade(GradeDTO dto) {

        // 1. Validate Student existence
        Student student = studentRepository.findById(dto.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found ID: " + dto.studentId()));

        // 2. Validate Subject existence
        Subject subject = subjectRepository.findById(dto.subjectId())
                .orElseThrow(() -> new EntityNotFoundException("Subject not found ID: " + dto.subjectId()));

        // 3. Build and save the Entity
        Grade grade = Grade.builder()
                .value(dto.value())
                .student(student)
                .subject(subject)
                .build();

        return convertToDTO(gradeRepository.save(grade));
    }

    @Transactional(readOnly = true)
    public List<GradeDTO> findGradesByStudent(Long studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new EntityNotFoundException("Student not found ID: " + studentId);
        }
        
        return gradeRepository
            .findByStudentId(studentId)
            .stream()
            .map(this::convertToDTO)
            .toList();
    }

}