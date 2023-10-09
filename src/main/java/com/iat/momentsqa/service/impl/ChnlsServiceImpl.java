package com.iat.momentsqa.service.impl;

import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.repository.ChnlsRepository;
import com.iat.momentsqa.service.IChnlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChnlsServiceImpl implements IChnlsService {
    @Autowired
    ChnlsRepository repository;

    @Override
    public List<Channels> getAllChannels() {
        return repository.findAll();
    }

    @Override
    public Channels addChannel(Channels channel) {
        return repository.save(channel);
    }

    @Override
    public List<Channels> addMultipleChannels(List<Channels> channels) {
        return repository.saveAll(channels);
    }

    @Override
    public Channels getChannelsById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Slice<Channels> getChannelsByChannelName(String name, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findByChannelNameContainingIgnoreCase(name, pageable);
    }

    @Override
    public boolean renewChannel(Channels channels) {
        if (repository.existsById(channels.getChannelId())) {
            repository.save(channels);
        }
//        Channels channel=getChannelsById(channels.getChannelId());
//        channel.equals(channels);
        return repository.existsById(channels.getChannelId());
    }
}
