package com.iat.momentsqa.controller;

import com.iat.momentsqa.dto.ScheduleDto;
import com.iat.momentsqa.dtomapper.ScheduleMapperStruct;
import com.iat.momentsqa.model.Schedules;
import com.iat.momentsqa.service.ISchedsService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@EnableSpringDataWebSupport
// @EnableWebMvc
@RequestMapping("/schedules")
@CrossOrigin(origins = "*") // cho phép API hoặc endpoint của mình chấp nhận các yêu cầu từ bất kỳ nguồn nào, bỏ qua same-origin policy.
@ComponentScan(basePackages = {"com.iat.momentsqa.controller", "com.iat.momentsqa.dtomapper"})
public class SchedsCtrl {
    @Autowired
    ISchedsService iSchedsService;
    @Autowired
    ScheduleMapperStruct scheduleMapperStruct;

    // GET ALL SCHEDULES
//    @GetMapping()
//    public Page<Schedules> getAllSchedules(@RequestParam(defaultValue = "0") int page) {
//        return iSchedsService.getAllSchedules(page);
//    }
    @GetMapping()
    public List<ScheduleDto> getAllSchedules(@RequestParam(defaultValue = "0") int page) {
        return iSchedsService.getAllSchedules(page).stream().map(schedules -> scheduleMapperStruct.INSTANCE.scheduleToScheduleDto(schedules)).collect(Collectors.toList());
    }

    // FILTER SCHEDULES BY DATE
    @GetMapping("/date")
    public List<ScheduleDto> filterByDateTime(@RequestParam(name = "time_start") LocalDateTime timeStart,
            @RequestParam(name = "time_end") LocalDateTime timeEnd) {
        return iSchedsService.findByDateTime(timeStart, timeEnd).stream().map(
                schedule->ScheduleMapperStruct.INSTANCE.scheduleToScheduleDto(schedule)
        ).collect(Collectors.toList());
    }

    // @GetMapping("/search")
    // public Slice<Schedules> getAllByChannelName(@RequestParam(name="q") Channels
    // name) {
    // return iSchedsService.findByChannelName(name);
    // }

    // ADD SINGLE SCHEDULE - TYPE XML
    @PostMapping(value = "/add-schedule", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Schedules addSchedule(@RequestBody Schedules schedule) {
        return iSchedsService.addSchedule(schedule);
    }

    // ADD MULTI SCHEDULES
    @PostMapping("/add-schedules")
    public List<Schedules> addMultiSchedule(@RequestBody List<Schedules> schedule) {
        return iSchedsService.addMultiSchedule(schedule);
    }

    // DELETE A SCHEDULE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void addMultiSchedule(@PathVariable(name = "id") Long id) {
        iSchedsService.deleteScheduleById(id);
    }
}
