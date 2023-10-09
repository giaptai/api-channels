package com.iat.momentsqa.service;

import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.model.Schedules;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.util.Streamable;

import java.time.LocalDateTime;
import java.util.List;

public interface ISchedsService {
    Page<Schedules> getAllSchedules(int page); // lấy danh sách Schedules có phân trang

    Schedules addSchedule(Schedules schedule);

    List<Schedules> addMultiSchedule(List<Schedules> schedule);

    void deleteScheduleById(Long id);

    Slice<Schedules> findByChannelName(Channels id);

    Streamable<Schedules> findByDateTime(LocalDateTime staTime, LocalDateTime endTime);
}
