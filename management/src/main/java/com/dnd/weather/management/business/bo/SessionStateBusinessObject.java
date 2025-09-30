package com.dnd.weather.management.business.bo;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
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
}
