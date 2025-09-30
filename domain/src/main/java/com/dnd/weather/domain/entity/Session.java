package com.dnd.weather.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "session")
public class Session extends AbstractEntity {

    @ManyToOne
    private UserData userData;

    private String name;

    @OneToOne(mappedBy = "session", cascade = CascadeType.PERSIST)
    private SessionState sessionState;

}
