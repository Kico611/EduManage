package com.kristijanbalic.edumanage.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String ime;

    @NotBlank(message = "Last name is required")
    private String prezime;

    @Transient
    private String kolegijiImena;

    @ManyToMany(mappedBy = "profesori")
    private List<Kolegij> kolegiji;

    public String getKolegijiImena() {
        return kolegijiImena;
    }

    public void setKolegijiImena(String kolegijiImena) {
        this.kolegijiImena = kolegijiImena;
    }

    public List<Kolegij> getKolegiji() {
        return kolegiji;
    }

    public void setKolegiji(List<Kolegij> kolegiji) {
        this.kolegiji = kolegiji;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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