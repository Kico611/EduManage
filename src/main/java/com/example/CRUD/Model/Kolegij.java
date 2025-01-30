package com.example.CRUD.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Kolegij {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String naziv;

    @ManyToMany
    @JoinTable(
            name = "kolegij_profesor",
            joinColumns = @JoinColumn(name = "kolegij_id"),
            inverseJoinColumns = @JoinColumn(name = "profesor_id")
    )

    private List<Profesor> profesori;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }
}
