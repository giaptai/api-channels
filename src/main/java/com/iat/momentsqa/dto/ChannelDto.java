package com.iat.momentsqa.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iat.momentsqa.model.Programs;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Builder // with builder, we can create a constructor with params without dependency the constructor of class
public record ChannelDto(
        Long channelId,
        String channelName,
        String description,
        String logoURL,
        @JsonIgnoreProperties({"channels"})
        Set<Programs> programs
) {

}
