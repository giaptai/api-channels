package com.iat.momentsqa.service.impl;

import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.model.Schedules;
import com.iat.momentsqa.repository.SchedsRepository;
import com.iat.momentsqa.service.ISchedsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedsServiceImpl implements ISchedsService {
    @Autowired
    SchedsRepository schedsRepository;

    @Override
    public Page<Schedules> getAllSchedules(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return schedsRepository.findAll(pageable);
    }

    @Override
    public Schedules addSchedule(Schedules schedule) {
        return schedsRepository.addWithCheck(schedule.getTitle(), schedule.getTimeStart(), schedule.getTimeEnd(),
                schedule.getStreamingUrl(), schedule.getChannel_fk().getChannelId(),
                schedule.getProgram_fk().getProgramId());
    }

    @Override
    public List<Schedules> addMultiSchedule(List<Schedules> schedule) {
        return schedsRepository.saveAll(schedule);
    }

    @Override
    public Slice<Schedules> findByChannelName(Channels id) {
        return null;
    }

    @Override
    public Streamable<Schedules> findByDateTime(LocalDateTime staTime, LocalDateTime endTime) {
        return schedsRepository.findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(staTime, endTime);
    }

    @Override
    public void deleteScheduleById(Long id) {
        schedsRepository.deleteScheduleById(id);
    }
}
