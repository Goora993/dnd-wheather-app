package com.dnd.weather.model.entity;

import com.dnd.weather.model.enumeration.WeatherType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "weather_roll_rule")
public class WeatherRollRule extends AbstractEntity {

    private int roll_from;

    private int roll_to;

    @Enumerated(EnumType.STRING)
    private WeatherType currentWeather;

    @Enumerated(EnumType.STRING)
    private WeatherType resultWeather;

}
