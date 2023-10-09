package com.iat.momentsqa.dtomapper.EntityToDto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.iat.momentsqa.dto.ProgramDto;
import com.iat.momentsqa.model.Programs;

@Component
public class ProgramsToDto implements Function<Programs, ProgramDto> {
    @Override
    public ProgramDto apply(Programs program) {
        return new ProgramDto(
                program.getChannels().getChannelId(),
                program.getChannels().getChannelName(),
                program.getProgramId(),
                program.getName(),
                program.getTitle(),
                program.getContentType(),
                program.getGenre(),
                program.getDuration(),
                program.getReleaseDate(),
                program.getProdCountry(),
                program.getDescription());
    }
}
