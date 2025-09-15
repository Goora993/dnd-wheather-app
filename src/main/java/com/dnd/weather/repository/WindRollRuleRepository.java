package com.dnd.weather.repository;

import com.dnd.weather.model.entity.WindRollRule;
import com.dnd.weather.model.enumeration.WindType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WindRollRuleRepository extends JpaRepository<WindRollRule, Long> {

    @Query("SELECT r.resultWind FROM WindRollRule r WHERE r.currentWind = :currentWind AND :roll BETWEEN r.roll_from AND r.roll_to")
    WindType findResultWind(WindType currentWind, int roll);

}
