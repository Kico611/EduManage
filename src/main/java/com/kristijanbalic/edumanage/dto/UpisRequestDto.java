package com.kristijanbalic.edumanage.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class UpisRequestDto {

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Course ID is required")
    private Long kolegijId;

    @Min(value = 1, message = "Grade must be at least 1")
    @Max(value = 5, message = "Grade must be at most 5")
    private Integer ocjena;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getKolegijId() {
        return kolegijId;
    }

    public void setKolegijId(Long kolegijId) {
        this.kolegijId = kolegijId;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }
}