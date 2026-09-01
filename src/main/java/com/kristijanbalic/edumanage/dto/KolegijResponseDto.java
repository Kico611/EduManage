package com.kristijanbalic.edumanage.dto;

import java.util.List;

public class KolegijResponseDto {

    private Long id;
    private String naziv;
    private List<String> profesori;

    public KolegijResponseDto(Long id,
                              String naziv,
                              List<String> profesori) {
        this.id = id;
        this.naziv = naziv;
        this.profesori = profesori;
    }

    public Long getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public List<String> getProfesori() {
        return profesori;
    }
}