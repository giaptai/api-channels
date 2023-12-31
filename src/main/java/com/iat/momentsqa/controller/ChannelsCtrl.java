package com.iat.momentsqa.controller;

import com.iat.momentsqa.dto.ChannelDto;
import com.iat.momentsqa.dto.CreateChannelDto;
import com.iat.momentsqa.dtomapper.MapperChannel;
import com.iat.momentsqa.messagingrabbitmq.RabbitmqProducer;
import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.service.IChnlsService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(value = "/channels")
public class ChannelsCtrl {
    IChnlsService service;
    MapperChannel mapperChannel;
    RabbitmqProducer rabbitmqProducer;

    public ChannelsCtrl(IChnlsService service, MapperChannel mapper, RabbitmqProducer rabbitmqProducer) {
        this.service = service;
        this.mapperChannel = mapper;
        this.rabbitmqProducer = rabbitmqProducer;
    }

    //GET ALL NO PAGINATION
    @GetMapping("/all")
    public ResponseEntity<List<ChannelDto>> getAllChannels() {
        List<ChannelDto> channelDtoList = service.getAllChannels().stream().map(mapperChannel::toDto).collect(Collectors.toList());
        rabbitmqProducer.sendMessage("all");
        return ResponseEntity.ok(channelDtoList);
    }

    //GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ChannelDto> getChannelById(@PathVariable(name = "id") Long id) {
        ChannelDto channelDto = mapperChannel.toChannelId(service.getChannelsById(id));
        rabbitmqProducer.sendMessage2(String.valueOf(id));
        return ResponseEntity.ok(channelDto);
    }

    //SEARCH BY TITLE
    @GetMapping("/search")
    public ResponseEntity<List<CreateChannelDto>> getChannelsByChannelName(@RequestParam(name = "q") Optional<String> name, @RequestParam(defaultValue = "0") int page) {
        List<CreateChannelDto> channels = service.getChannelsByChannelName(name.get(), page).stream().map(mapperChannel::toChannelQ).collect(toList());
        return ResponseEntity.ok(channels);
    }

    //ADD CHANNEL
    @PostMapping("/add-channel")
    public ResponseEntity<ChannelDto> addChannel(@RequestBody @Valid CreateChannelDto createChannelDto) {
        Channels channel = mapperChannel.toChannel(createChannelDto);
        return ResponseEntity.ok(mapperChannel.toDto(service.addChannel(channel)));
    }

    //ADD MULTI CHANNELS
    @PostMapping("/add-channels")
    public ResponseEntity<List<Channels>> addMultipleChannels(@RequestBody List<CreateChannelDto> createChannelDtos) {
        List<Channels> createChannelDtoList = createChannelDtos.stream().map(mapperChannel::toChannel).collect(toList());
        return ResponseEntity.ok(service.addMultipleChannels(createChannelDtoList));
    }

    //EDIT CHANNEL
    @PatchMapping("/channel/{id}")
    public ResponseEntity<CreateChannelDto> renewChannel(@RequestBody @Valid CreateChannelDto createChannelDto, @PathVariable(name = "id") Long id) {
        Channels channel = mapperChannel.toChannelEdit(createChannelDto, id);
        return service.renewChannel(channel) ? ResponseEntity.ok(createChannelDto) : ResponseEntity.ok(null);
    }
}
