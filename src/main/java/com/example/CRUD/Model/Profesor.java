package com.example.CRUD.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ime;
    private String prezime;
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

    // Getter i setter za id
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

    // Getter i setter za prezime
    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

}
