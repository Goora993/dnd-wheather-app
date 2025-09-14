package com.dnd.weather_app.model.entity;

import com.dnd.weather_app.model.enumeration.WeatherType;
import com.dnd.weather_app.model.enumeration.WindDirection;
import com.dnd.weather_app.model.enumeration.WindType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "session_state")
public class SessionState extends AbstractEntity {

    @OneToOne
    private Session session;

    private int hour;

    private int minute;

    private WeatherType weather;

    private WindType wind;

    private WindDirection windDirection;

}
