package com.iat.momentsqa.dto;

import java.time.LocalDateTime;
import com.iat.momentsqa.model.Channels;
import org.springframework.web.bind.annotation.Mapping;

public record ProgramDto(
        Long channelId,
        String channelName,
        Long programId,
        String name,
        String title,
        String contentType,
        String genre,
        int duration,
        LocalDateTime releaseDate,
        String prodCountry,
        String description) {
}
