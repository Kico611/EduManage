package com.kristijanbalic.edumanage.dto;

import jakarta.validation.constraints.NotBlank;

public class ProfesorRequestDto {

    @NotBlank(message = "First name is required")
    private String ime;

    @NotBlank(message = "Last name is required")
    private String prezime;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}