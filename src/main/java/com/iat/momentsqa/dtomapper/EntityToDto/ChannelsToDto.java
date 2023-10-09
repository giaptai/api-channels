package com.iat.momentsqa.dtomapper.EntityToDto;

import com.iat.momentsqa.dto.ChannelDto;
import com.iat.momentsqa.model.Channels;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ChannelsToDto implements Function<Channels, ChannelDto> {
    public ChannelDto apply(Channels channel) {
//        return new ChannelDto (
//                channel.getChannelId(),
//                channel.getChannelName(),
//                channel.getDescription(),
//                channel.getLogoURL(),
//                channel.getPrograms()
//        );
        return ChannelDto.builder()
                .channelId(channel.getChannelId())
                .channelName(channel.getChannelName())
                .description( channel.getDescription())
                .logoURL(channel.getLogoURL())
                .programs(channel.getPrograms()
                ).build();
    }
}
