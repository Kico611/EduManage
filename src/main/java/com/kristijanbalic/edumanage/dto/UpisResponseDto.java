package com.kristijanbalic.edumanage.dto;

public class UpisResponseDto {

    private Long id;
    private Long studentId;
    private String studentIme;
    private Long kolegijId;
    private String kolegijNaziv;
    private Integer ocjena;

    public UpisResponseDto(Long id,
                           Long studentId,
                           String studentIme,
                           Long kolegijId,
                           String kolegijNaziv,
                           Integer ocjena) {
        this.id = id;
        this.studentId = studentId;
        this.studentIme = studentIme;
        this.kolegijId = kolegijId;
        this.kolegijNaziv = kolegijNaziv;
        this.ocjena = ocjena;
    }

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentIme() {
        return studentIme;
    }

    public Long getKolegijId() {
        return kolegijId;
    }

    public String getKolegijNaziv() {
        return kolegijNaziv;
    }

    public Integer getOcjena() {
        return ocjena;
    }
}