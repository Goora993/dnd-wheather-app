package com.dnd.weather.repository;

import com.dnd.weather.model.entity.WeatherRollRule;
import com.dnd.weather.model.enumeration.WeatherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRollRuleRepository extends JpaRepository<WeatherRollRule, Long> {

    @Query("SELECT r.resultWeather FROM WeatherRollRule r WHERE r.currentWeather = :currentWeather AND :roll BETWEEN r.roll_from AND r.roll_to")
    WeatherType findResultWeather(WeatherType currentWeather, int roll);

}
