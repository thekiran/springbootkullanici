package com.example.demo.mapper;

import com.example.demo.dto.StudentCreateRequest;
import com.example.demo.dto.StudentResponse;
import com.example.demo.dto.StudentUpdateRequest;
import com.example.demo.model.Student;
import com.example.demo.util.PhoneMaskUtil;

public class StudentMapper {

    public static Student toEntity(StudentCreateRequest dto) {
        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setPhoneNumber(dto.getTelephone());
        return s;
    }

    public static Student toEntity(StudentUpdateRequest dto) {
        Student s = new Student();
        s.setFirstName(dto.getFirstName());
        s.setLastName(dto.getLastName());
        s.setEmail(dto.getEmail());
        s.setPhoneNumber(dto.getPhoneNumber());
        return s;
    }

    public static StudentResponse toResponse(Student entity) {
        return new StudentResponse(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                PhoneMaskUtil.mask(entity.getPhoneNumber())
        );
    }
}
