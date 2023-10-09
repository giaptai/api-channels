package com.iat.momentsqa.service;

import com.iat.momentsqa.model.Channels;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface IChnlsService {
    List<Channels> getAllChannels(); // lấy danh sách channels có phân trang
    Channels addChannel(Channels channel); // thêm 1 channel
    public abstract List<Channels> addMultipleChannels(List<Channels> channels); // thêm nhiều channel cùng lúc
    Channels getChannelsById(Long id);
    Slice<Channels> getChannelsByChannelName(String name, int page);
    boolean renewChannel(Channels channels);
}
