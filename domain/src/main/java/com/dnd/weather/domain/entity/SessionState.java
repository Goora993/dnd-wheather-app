package com.dnd.weather.domain.entity;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "session_state")
public class SessionState extends AbstractEntity {

    @OneToOne
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
