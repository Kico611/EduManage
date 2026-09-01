package com.kristijanbalic.edumanage.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class KolegijRequestDto {

    @NotBlank(message = "Course name is required")
    private String naziv;

    private List<Long> profesoriIds;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Long> getProfesoriIds() {
        return profesoriIds;
    }

    public void setProfesoriIds(List<Long> profesoriIds) {
        this.profesoriIds = profesoriIds;
    }
}