package com.kristijanbalic.edumanage.mapper;

import com.kristijanbalic.edumanage.dto.ProfesorRequestDto;
import com.kristijanbalic.edumanage.dto.ProfesorResponseDto;
import com.kristijanbalic.edumanage.entity.Kolegij;
import com.kristijanbalic.edumanage.entity.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {

    public Profesor toEntity(ProfesorRequestDto dto) {
        Profesor profesor = new Profesor();

        profesor.setIme(dto.getIme());
        profesor.setPrezime(dto.getPrezime());

        return profesor;
    }

    public ProfesorResponseDto toResponseDto(Profesor profesor) {

        return new ProfesorResponseDto(
                profesor.getId(),
                profesor.getIme(),
                profesor.getPrezime(),
                profesor.getKolegiji() == null
                        ? java.util.List.of()
                        : profesor.getKolegiji()
                        .stream()
                        .map(Kolegij::getNaziv)
                        .toList()
        );
    }
}