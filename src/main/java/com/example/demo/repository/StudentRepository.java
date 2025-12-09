package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    boolean existsByEmail(String email);

    // Update sırasında aynı email’i kullanan başka id var mı kontrolü
    boolean existsByEmailAndIdNot(String email, Integer id);
}
