package com.dnd.weather.dao;

import com.dnd.weather.domain.entity.WeatherRollRule;
import com.dnd.weather.domain.enumeration.WeatherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRollRuleDao extends JpaRepository<WeatherRollRule, Long> {

    @Query("SELECT r.resultWeather FROM WeatherRollRule r WHERE r.currentWeather = :currentWeather AND :roll BETWEEN r.roll_from AND r.roll_to")
    WeatherType findResultWeather(WeatherType currentWeather, int roll);

}
