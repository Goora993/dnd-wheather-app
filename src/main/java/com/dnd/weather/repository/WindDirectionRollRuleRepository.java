package com.dnd.weather.repository;

import com.dnd.weather.model.entity.WindDirectionRollRule;
import com.dnd.weather.model.enumeration.WindDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WindDirectionRollRuleRepository extends JpaRepository<WindDirectionRollRule, Long> {

    @Query("SELECT r.resultWindDirection FROM WindDirectionRollRule r WHERE r.currentWindDirection = :currentWindDirection AND :roll BETWEEN r.roll_from AND r.roll_to")
    WindDirection findResultWindDirection(WindDirection currentWindDirection, int roll);

}
