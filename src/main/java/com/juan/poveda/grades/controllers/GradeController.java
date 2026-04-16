package com.juan.poveda.grades.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.poveda.grades.models.dtos.GradeDTO;
import com.juan.poveda.grades.services.GradeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/grades")
public class GradeController {

    private final GradeService gradeService;

    /**
     * Registers a new grade for a student in a specific subject.
     */
    @PostMapping
    public ResponseEntity<GradeDTO> registerGrade(@Valid @RequestBody GradeDTO gradeDTO) {
        GradeDTO created = gradeService.registerGrade(gradeDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Lists all grades belonging to a specific student.
     */
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeDTO>> getGradesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.findGradesByStudent(studentId));
    }

}