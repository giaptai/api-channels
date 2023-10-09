package com.iat.momentsqa.dto;

import lombok.Builder;
import org.hibernate.validator.constraints.URL;

@Builder
public record CreateChannelDto(
        String channelName,
        String description,
        @URL(message = "url đâu")
        String logoURL
) {
}
