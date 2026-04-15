package com.juan.poveda.subjects.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.juan.poveda.subjects.models.dtos.SubjectDTO;
import com.juan.poveda.subjects.models.entities.Subject;
import com.juan.poveda.subjects.repositories.SubjectRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private SubjectDTO convertToDTO(Subject subject) {
        return new SubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                subject.getCredits()
        );
    }

    @Transactional(readOnly = true)
    public List<SubjectDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public SubjectDTO findById(Long id) {
        return subjectRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + id));
    }

    @Transactional
    public SubjectDTO create(SubjectDTO dto) {
  
        Subject subject = Subject.builder()
                .name(dto.name())
                .code(dto.code())
                .credits(dto.credits())
                .build();
        
        return convertToDTO(subjectRepository.save(subject));
    }

    @Transactional
    public SubjectDTO update(Long id, SubjectDTO dto) {

        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with ID: " + id));
        
        subject.setName(dto.name());
        subject.setCode(dto.code());
        subject.setCredits(dto.credits());
        
        return convertToDTO(subjectRepository.save(subject));
    }

    @Transactional
    public void delete(Long id) {

        if (!subjectRepository.existsById(id)) {
            throw new EntityNotFoundException("Cannot delete: Subject not found with ID: " + id);
        }

        subjectRepository.deleteById(id);

    }

}