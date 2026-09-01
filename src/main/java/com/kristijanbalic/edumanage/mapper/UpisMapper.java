package com.kristijanbalic.edumanage.mapper;

import com.kristijanbalic.edumanage.dto.UpisResponseDto;
import com.kristijanbalic.edumanage.entity.Upis;
import org.springframework.stereotype.Component;

@Component
public class UpisMapper {

    public UpisResponseDto toResponseDto(Upis upis) {

        return new UpisResponseDto(
                upis.getId(),
                upis.getStudent().getId(),
                upis.getStudent().getIme(),
                upis.getKolegij().getId(),
                upis.getKolegij().getNaziv(),
                upis.getOcjena()
        );
    }
}