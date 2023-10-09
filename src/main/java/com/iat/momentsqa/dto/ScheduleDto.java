package com.iat.momentsqa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
public record ScheduleDto(
        long channelId,
        String channelName,
        String logoUrl,
        Long programId,
        String programName,
        String title,
        LocalDateTime broadcastDate,
        LocalDateTime timeStart,
        @JsonFormat(pattern="dd-MM-YYYY HH:mm:ss")
        LocalDateTime timeEnd,
        String streamingUrl
) {
}
