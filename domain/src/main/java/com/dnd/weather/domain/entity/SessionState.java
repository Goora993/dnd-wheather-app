package com.dnd.weather.domain.entity;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "session_state")
public class SessionState extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "session_id")
    private Session session;

    private int hour;

    private int minute;

    @Enumerated(EnumType.STRING)
    private WeatherType weather;

    @Enumerated(EnumType.STRING)
    private WindType wind;

    @Enumerated(EnumType.STRING)
    private WindDirection windDirection;

}
