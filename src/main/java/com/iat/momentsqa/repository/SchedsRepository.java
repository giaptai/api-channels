package com.iat.momentsqa.repository;

import com.iat.momentsqa.model.Channels;
import com.iat.momentsqa.model.Schedules;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SchedsRepository extends JpaRepository<Schedules, String> {
        // @Query(value = "INSERT INTO schedules (title, time_start, time_end, streaming_url, channel_fk, program_fk) SELECT ?1, ?2, ?3, ?4, ?5, ?6\n"
        //                 +
        //                 "WHERE (?6 IN (SELECT program_id FROM programs WHERE channel_id = ?5)) returning *", nativeQuery = true)
        @Query(value = "INSERT INTO schedules (title, time_start, time_end, streaming_url, channel_fk, program_fk) SELECT ?1, ?2, ?3, ?4, ?5, ?6\n"
                        +
                        "returning *", nativeQuery = true)
        Schedules addWithCheck(String title, LocalDateTime start, LocalDateTime end, String strm_url, Long chnl_id, long prog_id);

        @Modifying
        @Query(value = "delete from Schedules s where s.scheduleId = :id") // tên entity và thuộc tính của entity
        void deleteScheduleById(@Param("id") Long id);

        @Query
        Streamable<Schedules> findByTimeStartGreaterThanEqualAndTimeEndLessThanEqual(LocalDateTime TimeStart, LocalDateTime TimeEnd);
}
