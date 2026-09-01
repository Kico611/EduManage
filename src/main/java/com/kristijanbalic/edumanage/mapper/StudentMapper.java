package com.kristijanbalic.edumanage.mapper;

import com.kristijanbalic.edumanage.dto.StudentRequestDto;
import com.kristijanbalic.edumanage.dto.StudentResponseDto;
import com.kristijanbalic.edumanage.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto dto) {

        Student student = new Student();

        student.setIme(dto.getIme());
        student.setEmail(dto.getEmail());
        student.setAdresa(dto.getAdresa());
        student.setBrojIndeksa(dto.getBrojIndeksa());
        student.setGodinaUpisa(dto.getGodinaUpisa());

        return student;
    }

    public StudentResponseDto toResponseDto(Student student) {

        return new StudentResponseDto(
                student.getId(),
                student.getIme(),
                student.getEmail(),
                student.getAdresa(),
                student.getBrojIndeksa(),
                student.getGodinaUpisa()
        );
    }
}