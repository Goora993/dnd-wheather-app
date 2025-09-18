package com.dnd.weather.domain.entity;

import com.dnd.weather.domain.enumeration.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ROLE")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole name;

}
