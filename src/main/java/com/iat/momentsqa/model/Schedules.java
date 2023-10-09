package com.iat.momentsqa.model;

import com.fasterxml.jackson.annotation.*;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "scheduleId")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    Long scheduleId;

    @Column(unique = true)
    String title;

    @DateTimeFormat
    LocalDateTime broadcastDate;

    @DateTimeFormat
    LocalDateTime timeStart;

    @DateTimeFormat
    LocalDateTime timeEnd;

    @URL
    @Nullable
    String streamingUrl;

    // @JsonIgnoreProperties({ "programs"})
    // @MapsId("channelId")
    // @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "channel_fk")
//    @JsonIgnoreProperties({ "channelName", "description", "logoURL", "programs", "schedules" })
//    @JsonIgnore
    Channels channel_fk;

    // @MapsId("programId")
    @ManyToOne
    @JoinColumn(name = "program_fk")
    // @JsonBackReference
    // @JsonIgnoreProperties({ "name", "title", "contentType", "genre", "duration", "releaseDate", "prodCountry",
    //         "description", "channels", "schedules"
    // })
//     @JsonIgnoreProperties({ "channels" })
//    @JsonIgnore
    Programs program_fk;
}
