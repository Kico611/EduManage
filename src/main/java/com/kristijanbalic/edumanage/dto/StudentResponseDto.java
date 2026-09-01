package com.kristijanbalic.edumanage.dto;

public class StudentResponseDto {

    private Long id;
    private String ime;
    private String email;
    private String adresa;
    private String brojIndeksa;
    private int godinaUpisa;

    public StudentResponseDto() {
    }

    public StudentResponseDto(Long id,
                              String ime,
                              String email,
                              String adresa,
                              String brojIndeksa,
                              int godinaUpisa) {
        this.id = id;
        this.ime = ime;
        this.email = email;
        this.adresa = adresa;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public int getGodinaUpisa() {
        return godinaUpisa;
    }
}