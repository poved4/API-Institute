package com.juan.poveda.students.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.poveda.students.models.dtos.StudentDTO;
import com.juan.poveda.students.models.entities.Student;
import com.juan.poveda.students.repositories.StudentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
            student.getId(),
            student.getFirstName(),
            student.getLastName(),
            student.getEmail(),
            student.getBirthDate()
        );
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public StudentDTO findById(Long id) {
        return studentRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    @Transactional
    public StudentDTO create(StudentDTO dto) {

        Student student = Student.builder()
            .firstName(dto.firstName())
            .lastName(dto.lastName())
            .email(dto.email())
            .birthDate(dto.birthDate())
            .build();

        return convertToDTO(
            studentRepository.save(student)
        );
    }

    @Transactional
    public StudentDTO update(Long id, StudentDTO dto) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));

        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setBirthDate(dto.birthDate());

        return convertToDTO(studentRepository.save(student));
    }

    @Transactional
    public void delete(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete: Student not found with ID: " + id);
        }

        studentRepository.deleteById(id);

    }

}