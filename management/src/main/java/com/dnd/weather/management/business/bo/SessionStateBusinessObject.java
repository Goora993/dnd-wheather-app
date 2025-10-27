package com.dnd.weather.management.business.bo;

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
public class SessionStateBusinessObject {
    private final Long id;
    private final LocalDateTime creationDate;
    private final LocalDateTime modificationDate;
    private final Long version;
    private final Integer hour;
    private final Integer minute;
    private final WeatherType weather;
    private final WindType wind;
    private final WindDirection windDirection;

    public SessionStateBusinessObject withWeather(WeatherType weatherType) {
        return this.toBuilder()
                .weather(weatherType)
                .build();
    }

    public SessionStateBusinessObject withWind(WindType windType) {
        return this.toBuilder()
                .wind(windType)
                .build();
    }

    public SessionStateBusinessObject withWindDirection(WindDirection windDirection) {
        return this.toBuilder()
                .windDirection(windDirection)
                .build();
    }

    public SessionStateBusinessObject withHour(Integer hour) {
        return this.toBuilder()
                .hour(hour)
                .build();
    }

    public SessionStateBusinessObject withMinute(Integer minute) {
        return this.toBuilder()
                .minute(minute)
                .build();
    }

}
