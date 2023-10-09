package com.iat.momentsqa.dtomapper;

import com.iat.momentsqa.dto.ChannelDto;
import com.iat.momentsqa.dto.CreateChannelDto;
import com.iat.momentsqa.model.Channels;
import org.springframework.stereotype.Component;

// https://www.baeldung.com/java-dto-pattern
@Component
public class MapperChannel {
    public ChannelDto toDto(Channels channel) {
        return ChannelDto.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .description(channel.getDescription())
                .logoURL(channel.getLogoURL())
                .build();
    }

    public Channels toChannel(CreateChannelDto createChannelDto) {
        return Channels.builder()
                .channelName(createChannelDto.channelName())
                .description(createChannelDto.description())
                .logoURL(createChannelDto.logoURL()).build();
    }

    public ChannelDto toChannelId(Channels channel) {
        return ChannelDto.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .description(channel.getDescription())
                .programs(channel.getPrograms())
                .logoURL(channel.getLogoURL())
                .build();
    }

    public CreateChannelDto toChannelQ(Channels channel) {
        return CreateChannelDto.builder()
                .channelName(channel.getChannelName())
                .description(channel.getDescription())
                .logoURL(channel.getLogoURL())
                .build();
    }

    public Channels toChannelEdit(CreateChannelDto channelDto, Long id) {
        return  Channels.builder()
                .channelId(id)
                .channelName(channelDto.channelName())
                .description(channelDto.description())
                .logoURL(channelDto.logoURL())
                .build();
    }
}
