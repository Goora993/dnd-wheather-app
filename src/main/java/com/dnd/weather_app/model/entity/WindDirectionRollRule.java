package com.dnd.weather_app.model.entity;

import com.dnd.weather_app.model.enumeration.WeatherType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wind_direction_roll_rule")
public class WindDirectionRollRule extends AbstractEntity {

    private int roll_from;

    private int roll_to;

    private WeatherType currentWindDirection;

    private WeatherType resultWindDirection;

}
