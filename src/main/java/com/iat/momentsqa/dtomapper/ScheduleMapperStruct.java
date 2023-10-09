package com.iat.momentsqa.dtomapper;

import com.iat.momentsqa.dto.ScheduleDto;
import com.iat.momentsqa.model.Schedules;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ScheduleMapperStruct {
    ScheduleMapperStruct INSTANCE = Mappers.getMapper(ScheduleMapperStruct.class);
    //source nơi lấy thuộc tính để ánh xạ, target nơi thuộc tính ánh xạ tới
    @Mapping(source = "channel_fk.channelId", target = "channelId")
    @Mapping(source = "channel_fk.channelName", target = "channelName")
    @Mapping(source = "channel_fk.logoURL", target = "logoUrl")
    @Mapping(source = "program_fk.programId", target = "programId")
    @Mapping(source = "program_fk.name", target = "programName")
    @Mapping(target = "title", ignore = true) //ignore this property when is mapping
    ScheduleDto scheduleToScheduleDto(Schedules schedule);
}
