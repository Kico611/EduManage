package com.kristijanbalic.edumanage.mapper;

import com.kristijanbalic.edumanage.dto.KolegijRequestDto;
import com.kristijanbalic.edumanage.dto.KolegijResponseDto;
import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Profesor;
import org.springframework.stereotype.Component;

@Component
public class KolegijMapper {

    public Kolegij toEntity(KolegijRequestDto dto) {

        Kolegij kolegij = new Kolegij();

        kolegij.setNaziv(dto.getNaziv());

        return kolegij;
    }

    public KolegijResponseDto toResponseDto(Kolegij kolegij) {

        return new KolegijResponseDto(
                kolegij.getId(),
                kolegij.getNaziv(),
                kolegij.getProfesori() == null
                        ? java.util.List.of()
                        : kolegij.getProfesori()
                        .stream()
                        .map(profesor ->
                                profesor.getIme() + " " + profesor.getPrezime())
                        .toList()
        );
    }
}