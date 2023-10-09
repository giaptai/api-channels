package com.iat.momentsqa.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;
import java.util.Set;

// @RequiredArgsConstructor
// @JsonIdentityInfo(
// generator = ObjectIdGenerators.PropertyGenerator.class,
// property = "channelId")
@Entity
@Table(name = "channels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Channels{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    Long channelId;

    String channelName;

    @Column(columnDefinition = "text")
    String description;

    @URL(message = "url đâu")
    @Column(columnDefinition = "text")
    String logoURL;

    @OneToMany(mappedBy = "channels", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "channels" })
//    @JsonIgnore
//    @JsonManagedReference
    Set<Programs> programs;

    // @JsonManagedReference
    @OneToMany(mappedBy = "channel_fk")
    // @JsonIgnoreProperties({ "channel_fk" })
    @JsonIgnore
    Set<Schedules> schedules;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        Channels channels = (Channels) o;
//        return Objects.equals(channelId, channels.channelId);
//    }
//
//    @Override
//    public int hashCode() {
//        return 0;
//    }
}
