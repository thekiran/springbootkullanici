package com.example.demo.service;

import com.example.demo.dto.NameResponse;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.util.PhoneMaskUtil;
import com.example.demo.util.TcNoMaskUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.util.StudentNormalizeUtil.normalize;
import static com.example.demo.util.StudentValidator.basicFieldChecks;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        normalize(student);
        validateStudentForCreate(student);
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı: " + id));
    }

    public Student updateStudent(Integer id, Student updatedData) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Öğrenci bulunamadı: " + id));

        existing.setFirstName(updatedData.getFirstName());
        existing.setLastName(updatedData.getLastName());
        existing.setEmail(updatedData.getEmail());
        existing.setPhoneNumber(updatedData.getPhoneNumber());

        normalize(existing);
        validateStudentForUpdate(existing);

        return studentRepository.save(existing);
    }

    public boolean deleteStudentById(int id) {
        if (!studentRepository.existsById(id)) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }

    // ---------- BUSINESS VALIDATION (iş kuralları) ----------

    private void validateStudentForCreate(Student student) {
        basicFieldChecks(student);

        // Yeni kayıt → email herhangi bir öğrencide olmamalı
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new IllegalArgumentException("Bu email zaten kayıtlı");
        }

    }

    private void validateStudentForUpdate(Student student) {
        basicFieldChecks(student);

        // Update → aynı email’e sahip ama FARKLI id’li öğrenci var mı?
        if (studentRepository.existsByEmailAndIdNot(student.getEmail(), student.getId())) {
            throw new IllegalArgumentException("Bu email başka bir öğrenciye ait");
        }

    }

    // NameResponse
    public NameResponse processName(String firstName) {
        return new NameResponse(firstName, firstName.length());
    }

    public NameResponse processEmail(String email) {
        return new NameResponse(email, email.length());
    }

    public  NameResponse processPhoneNumber(String phoneNumber) {return new NameResponse(phoneNumber, phoneNumber.length());}

    public NameResponse processTcNo(String tcNo) {return new NameResponse(tcNo, tcNo.length());}

}
