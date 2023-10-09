package com.iat.momentsqa.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "programId")
public class Programs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    Long programId;

    @Column(unique = true)
    String name;

    String title;

    String contentType;

    String genre;

    @NumberFormat
    int duration;

    @DateTimeFormat
    LocalDateTime releaseDate;

    String prodCountry;

    @Column(columnDefinition = "text")
    String description;

    // @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "channel_id")
    @JsonIgnoreProperties({ "description", "logoURL", "schedules", "programs" })
//    @JsonBackReference
    Channels channels;

    @OneToMany(mappedBy = "program_fk")
    // @JsonIgnoreProperties({ "program_fk", "channel_fk" })
     @JsonIgnore
    Set<Schedules> schedules;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Programs programs = (Programs) o;
        return Objects.equals(programId, programs.programId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programId);
    }
}
