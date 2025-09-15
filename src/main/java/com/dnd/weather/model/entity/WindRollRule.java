package com.dnd.weather.model.entity;

import com.dnd.weather.model.enumeration.WindType;
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
@Table(name = "wind_roll_rule")
public class WindRollRule extends AbstractEntity {

    private int roll_from;

    private int roll_to;

    @Enumerated(EnumType.STRING)
    private WindType currentWind;

    @Enumerated(EnumType.STRING)
    private WindType resultWind;

}
