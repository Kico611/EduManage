package com.kristijanbalic.edumanage.dto;

import java.util.List;

public class ProfesorResponseDto {

    private Long id;
    private String ime;
    private String prezime;
    private List<String> kolegiji;

    public ProfesorResponseDto(Long id,
                               String ime,
                               String prezime,
                               List<String> kolegiji) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.kolegiji = kolegiji;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public List<String> getKolegiji() {
        return kolegiji;
    }
}