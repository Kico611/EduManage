package com.example.CRUD.Model;

import com.example.CRUD.Model.Kolegij;
import com.example.CRUD.Model.Student;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Upis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Kolegij kolegij;
    @Column(nullable = true)
    private Integer ocjena;

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Kolegij getKolegij() {
        return kolegij;
    }

    public void setKolegij(Kolegij kolegij) {
        this.kolegij = kolegij;
    }

}
