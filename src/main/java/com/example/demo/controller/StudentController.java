package com.example.demo.controller;

import com.example.demo.dto.NameResponse;
import com.example.demo.dto.StudentCreateRequest;
import com.example.demo.dto.StudentResponse;
import com.example.demo.dto.StudentUpdateRequest;
import com.example.demo.mapper.StudentMapper;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentCreateRequest request) {
        Student studentEntity = StudentMapper.toEntity(request);
        Student saved = studentService.addStudent(studentEntity);
        return ResponseEntity.ok(StudentMapper.toResponse(saved));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> responses = studentService.getAllStudents()
                .stream()
                .map(StudentMapper::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable int id) {
        Student found = studentService.getStudentById(id);
        return ResponseEntity.ok(StudentMapper.toResponse(found));
    }

    // UPDATE BY ID
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudentById(
            @PathVariable Integer id,
            @Valid @RequestBody StudentUpdateRequest request
    ) {
        Student updatedData = StudentMapper.toEntity(request);
        updatedData.setId(id); // id burada setleniyor

        Student updated = studentService.updateStudent(id, updatedData);
        return ResponseEntity.ok(StudentMapper.toResponse(updated));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable int id) {
        boolean deleted = studentService.deleteStudentById(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // İSİM BİLGİSİ
    @PostMapping("/name-info")
    public ResponseEntity<NameResponse> getNameInfo(@RequestBody StudentCreateRequest request) {
        return ResponseEntity.ok(
                studentService.processName(request.getFirstName())
        );
    }

    // SADECE MASKELİ NUMARA ÖRNEĞİ
    @GetMapping("/{id}/phone-masked")
    public ResponseEntity<String> getMaskedPhone(@PathVariable int id) {
        Student s = studentService.getStudentById(id);
        String masked = String.valueOf(studentService.processPhoneNumber(s.getPhoneNumber()));
        return ResponseEntity.ok(masked);
    }

    @GetMapping("/{id}/tc-masked")
    public ResponseEntity<String> maskedtcNo(@PathVariable int id) {
        Student s = studentService.getStudentById(id);
        String masked = String.valueOf(studentService.processTcNo(s.getTcNo()));
        return ResponseEntity.ok(masked);
    }

}
