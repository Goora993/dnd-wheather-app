package com.dnd.weather.management.business.bo;

import com.dnd.weather.domain.entity.UserData;
import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class SessionBusinessObject {
    private final Long id;
    private final LocalDateTime creationDate;
    private final LocalDateTime modificationDate;
    private final Long version;
    private final String name;
    private final UserData userData; // TODO: Change to DTO
    private final SessionStateBusinessObject stateBusinessObject;

    public void changeWeather(WeatherType weatherType) {
        this.toBuilder()
                .stateBusinessObject(this.stateBusinessObject.withWeather(weatherType))
                .build();
    }

    public void changeWind(WindType windType) {
        this.toBuilder()
                .stateBusinessObject(this.stateBusinessObject.withWind(windType))
                .build();
    }

    public void changeWindDirection(WindDirection windDirection) {
        this.toBuilder()
                .stateBusinessObject(this.stateBusinessObject.withWindDirection(windDirection))
                .build();
    }

    public SessionBusinessObject changeHour(Integer hour) {
        return this.toBuilder()
                .stateBusinessObject(this.stateBusinessObject.withHour(hour))
                .build();
    }

    public SessionBusinessObject changeMinute(Integer minute) {
        return this.toBuilder()
                .stateBusinessObject(this.stateBusinessObject.withMinute(minute))
                .build();
    }

    public WeatherType getCurrentWeather() {
        return this.stateBusinessObject.getWeather();
    }

    public WindType getCurrentWind() {
        return this.stateBusinessObject.getWind();
    }

    public WindDirection getCurrentWindDirection() {
        return this.stateBusinessObject.getWindDirection();
    }

    public Integer getCurrentDuration() {
        return this.stateBusinessObject.getHour(); //TODO: Duration has to be implemented, check if hour:minute concept is useful
    }

}
